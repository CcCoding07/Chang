package BLL.impl;

import BLL.IHandler;
import DAL.acoount.IAccount;
import DAL.acoount.User;
import DAL.acoount.AccountImpl;
import DAL.information.IInfo;
import DAL.information.Person_info;
import DAL.information.InfoImpl;
import DAL.record.IRecord;
import DAL.record.MedicalRecord;
import DAL.record.RecordImpl;
import DAL.reservation.Doctor;
import DAL.reservation.IReservation;
import DAL.reservation.Schedule;
import DAL.reservation.ReservationImpl;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * process the requests from client
 */
public class HandlerImpl implements IHandler {
    private IAccount account = new AccountImpl();
    private IReservation registered = new ReservationImpl();
    private IInfo info = new InfoImpl();
    private IRecord record = new RecordImpl();

    @Override
/**
 * read the objects sent by client and assign them to different method
 */
    public synchronized void handle(InputStream inputStream, OutputStream outputStream) throws IOException, ClassNotFoundException {
        ArrayList<Object> objectList = new ArrayList();

        ObjectInputStream in = new ObjectInputStream(inputStream);
        Object object = in.readObject();
        objectList.add(object);
        System.out.println("receive a request");

        for (int i = 0; i < objectList.size(); i++) {
            Object obj = objectList.get(i);
            //if object is User，means login and registration
            if (obj instanceof User) {
                User user = (User) obj;
                handleAccount(user, outputStream);
            }
            // if object is Schedule，means appointment operation
            else if (obj instanceof Schedule) {
                Schedule schedule = (Schedule) obj;
                handleReservation(schedule, outputStream);
            }
            //if object is Doctor，means gain the information of appointment page
            else if (obj instanceof Doctor) {
                Doctor doctor = (Doctor) obj;
                handleDoctor(doctor, outputStream);
            }
            //if object is Person_info,means edit the personal information
            else if (obj instanceof Person_info) {
                Person_info person_info = (Person_info) obj;
                handlePersonInfo(person_info, outputStream);
            }
            //if object is MedicalRecord，means process the patient history
            else if (obj instanceof MedicalRecord) {
                MedicalRecord medicalRecord = (MedicalRecord) obj;
                hadleMedicalRecord(medicalRecord, outputStream);
            }

        }
    }

    /**
     * a method to process Account class
     * mode = 1 login
     * mode = 2 register
     * if success in registration, insert new piece of information
     *
     * @param user
     * @param outputStream
     * @throws IOException
     */
    @Override
    public synchronized void handleAccount(User user, OutputStream outputStream) throws IOException {
        //username of user is the id in person_info
        int mode = user.getMode();
        if (mode == 1) {
            int status = account.signUp(user);
            switch (status) {
                case 0:
                    outputStream.write("registration success".getBytes());
                    Person_info person_info = new Person_info(user.getUsername());
                    info.autoInsert(person_info);
                    break;
                case 1:
                    outputStream.write("Invalid username input".getBytes());
                    break;
                case 2:
                    outputStream.write("Invalid password input".getBytes());
                    break;
                case 3:
                    outputStream.write("Duplicate username, please re-enter".getBytes());
                    break;
            }
        } else if (mode == 2) {
            int status = account.logIn(user);
            switch (status) {
                case 0:
                    outputStream.write("login successful".getBytes());
                    break;
                case 1:
                    outputStream.write("Username input error".getBytes());
                    break;
                case 2:
                    outputStream.write("Password input error".getBytes());
                    break;
            }
        }// classify different accounts
        else if (mode == 3) {
            if (account.isExists(user.getUsername())) {
                String string = account.query(user);
                outputStream.write(string.getBytes());
            }
        }
    }

    /**
     * a method to process schedule class
     * @param schedule
     * @param outputStream
     * @throws IOException
     */
    @Override
    public synchronized void handleReservation(Schedule schedule, OutputStream outputStream) throws IOException {
        int status = registered.booking(schedule.getUser(), schedule.getId(), schedule.getWeek(), schedule.getTime());
        switch (status) {
            case 0:
                outputStream.write("The appointment was successful".getBytes());
                break;
            case 1:
                outputStream.write("The time has been booked".getBytes());
                break;
            case 2:
                outputStream.write("Please specify appointment time".getBytes());
                break;
            case 3:
                outputStream.write("This time has been booked, please try again".getBytes());
            default:
                outputStream.write("Select time is not within the scope of the appointment, please try again".getBytes());
        }
    }

    /**
     * a method to process Doctor object，which means gain the information of appointment page
     *
     * @param doctor
     * @param outputStream
     * @throws IOException
     */
    @Override
    public synchronized void handleDoctor(Doctor doctor, OutputStream outputStream) throws IOException {
        if (doctor.getMode() == 1) {
            outputStream.write(registered.returnName(doctor.getDepartment()).getBytes());
        } else if (doctor.getMode() == 2) {
            registered.insertTable(doctor);
            outputStream.write("Inserted successfully".getBytes());
        } else if (doctor.getMode() == 3) {

            outputStream.write(registered.returnDoctor(doctor.getUser().getUsername()).getBytes());
        } else {
            List<Schedule> list = new ArrayList<>();
            list = registered.selectCalendar(doctor.getName(), doctor.getDepartment());
//            List<List<String>> stringList = new ArrayList<>();
            StringBuffer stringBuffer = new StringBuffer();
            for (Schedule e : list) {
//                stringList.add(Schedule.getSchedule(e));
                stringBuffer.append(StringUtils.join(Schedule.getSchedule(e), ","));
                stringBuffer.append("&");
            }

            outputStream.write(stringBuffer.toString().getBytes());
        }
    }

    /**
     * a method to process Person_info class
     * mode = 1 means update
     * mode = 2 means update
     *
     * @param person_info
     * @param outputStream
     * @throws IOException
     */
    @Override
    public synchronized void handlePersonInfo(Person_info person_info, OutputStream outputStream) throws IOException {
        int mode = person_info.getMode();
        //mode = 1 means update info object
        if (mode == 1) {
            String string = info.flash(person_info.getId()).toString();
            outputStream.write(string.getBytes());
        }
        // mode == 2 edit personal information
        else if (mode == 2) {
            info.updateInfo(person_info);
            outputStream.write("update completed".getBytes());
        }
    }

    /**
     * a method to process Medical Record object
     * @param medicalRecord
     * @param outputStream
     * @throws IOException
     */
    @Override
    public synchronized void hadleMedicalRecord(MedicalRecord medicalRecord, OutputStream outputStream) throws IOException {
        int mode = medicalRecord.getMode();
        // mode == 1 look up patient history of a specific user name
        if (mode == 1) {
            // split patient history use "&"
            List<MedicalRecord> list = record.viewRecord(medicalRecord.getUsername());
            StringBuffer stringBuffer = new StringBuffer();
            for (MedicalRecord e : list) {
                stringBuffer.append(e.toString());
                stringBuffer.append("&");
            }
            outputStream.write(stringBuffer.toString().getBytes());
        }
        //mode == 2 look up patient history
        else if (mode == 2) {
            List<MedicalRecord> list = record.viewRecordByDate(medicalRecord.getUsername(), medicalRecord.getStartTime(), medicalRecord.getEndTime());
            StringBuffer stringBuffer = new StringBuffer();
            for (MedicalRecord e : list) {
                stringBuffer.append(e.toString());
                stringBuffer.append("&");
            }
            outputStream.write(stringBuffer.toString().getBytes());
        }
        //mode == 3 update patient history
        else if (mode == 3) {
            record.updateRecord(medicalRecord);
            outputStream.write("Successfully modified".getBytes());
        }
        //mode == 4 build new patient history
        else if (mode == 4) {
            record.newRecord(medicalRecord);
            outputStream.write("New medical record succeeded".getBytes());
        }

    }
}
