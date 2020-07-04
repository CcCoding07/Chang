package BLL;

import DAL.acoount.User;
import DAL.information.Person_info;
import DAL.record.MedicalRecord;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IHandler {
    void handle(InputStream inputStream, OutputStream outputStream) throws IOException, ClassNotFoundException;

    void handleAccount(User user, OutputStream outputStream) throws IOException;

    void handleReservation(Schedule schedule, OutputStream outputStream) throws IOException;

    void handleDoctor(Doctor doctor, OutputStream outputStream) throws IOException;

    void handlePersonInfo(Person_info person_info, OutputStream outputStream) throws IOException;

    void hadleMedicalRecord(MedicalRecord medicalRecord,OutputStream outputStream) throws IOException;
}
