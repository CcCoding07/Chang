package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.record.MedicalRecord;
import DAL.reservation.Doctor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * author：Chang Dong
 * data：03/12/2020
 */
// login page
class CheckPatientAppointment extends JFrame {
    private Client send;
    private User user;
    private Doctor doctor;
    private String[] times = new String[]{"", "10:00-11:00", "11:00-12:00", "13:00-14:00", "14:00-15:00", "15:00-16:00"};
    private String calendar;
    private JPanel contentPane;
    private String startTime;
    private String endTime;
    private String[] time = new String[2];
    //private String partner;
    private int startTimeHours;
    private int startTimeMins;
    // private static int patientID;
    private String type;
    private String appointmentDate;
    private String appointment;
    private List<MedicalRecord> list;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    CheckPatientAppointment frame = new CheckPatientAppointment(User user, Doctor doctor, Client send);//add the object
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * create frame
     */
    public CheckPatientAppointment(User user, Doctor doctor, Client send) {
        this.user = user;
        this.doctor = doctor;
        this.send = send;
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Check the Patients' Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBorder(new TitledBorder(null, "Check the Patients' Appointment：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        bookPanel.setBounds(15, 15, 580, 350);
        contentPane.add(bookPanel);

        //Display the Department
        JLabel lblDate = new JLabel("Week：");
        lblDate.setBounds(21, 30, 200, 17);
        bookPanel.add(lblDate);
        //Display the department chosen
        JComboBox<String> dateField = new JComboBox<String>();
        JComboBox<String> timeField = new JComboBox<String>();
        JComboBox<String> patientField = new JComboBox<String>();

        dateField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}));// to be finished
        dateField.setBounds(184, 33, 200, 20);
        dateField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        String[] strings = send.sendData(new Doctor(doctor.getName(), doctor.getDepartment())).split("&");
                        int date = dateField.getSelectedIndex();
                        System.out.println(date);
                        calendar = strings[date - 1];
                        System.out.println(calendar);
                        timeField.removeAllItems();
                        List<String> list = Arrays.asList(calendar.split(","));
                        for (int i = 0; i < list.size(); i++) {
                            String s = list.get(i);
                            if (!s.equals("0")) {
                                timeField.addItem(times[i + 1]);
                            }
                        }
                        break;
                }
            }
        });
        bookPanel.add(dateField);
        System.out.println(dateField.getSelectedItem().toString());

        //Display the time
        JLabel lblTime = new JLabel("Time：");
        lblTime.setBounds(21, 60, 134, 17);
        bookPanel.add(lblTime);
        // Display the date chosen

        timeField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
        }));// show all the booked time period
        timeField.setBounds(184, 63, 200, 20);
        timeField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        String s = timeField.getSelectedItem().toString();
                        String[] strings = calendar.split(",");
                        patientField.removeAllItems();
                        for (int i = 0; i < times.length; i++) {
                            if (s.equals(times[i])) {
                                patientField.addItem(strings[i - 1]);
                            }
                        }
                        break;
                }
            }
        });
        bookPanel.add(timeField);

        //Display the patients' information
        JLabel lblPatient = new JLabel("Patient：");
        lblPatient.setBounds(21, 90, 134, 17);
        bookPanel.add(lblPatient);
        // Display the date chosen

        patientField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
        }));// show all the booked patient's name
        patientField.setBounds(184, 93, 200, 20);

        bookPanel.add(patientField);

        JButton addCheck = new JButton("Check the Patient's Record");
        addCheck.setBounds(20, 133, 200, 23);
        addCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String string = null;
                try {
                    string = send.sendData(new MedicalRecord(patientField.getSelectedItem().toString(), "1970-01-01", "2070-01-01", 2));
                    System.out.println(string);
                    List<MedicalRecord> list = MedicalRecord.valueOf(string);

                    PatientCheckRecord1 patient = new PatientCheckRecord1(list, user, doctor, send);
                    patient.setBounds(500, 200, 600, 600);
                    patient.setVisible(true);
                    dispose();
                    bookPanel.add(addCheck);
                    setLocationRelativeTo(null);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        bookPanel.add(addCheck);
        setLocationRelativeTo(null);

        JButton addBack = new JButton("Back");
        addBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mainPage main = new mainPage(user, doctor, send);
                main.setBounds(500, 200, 600, 400);
                main.setVisible(true);
                dispose();
                bookPanel.add(addCheck);
                setLocationRelativeTo(null);

            }
        });
        addBack.setBounds(230, 133, 100, 23);
        bookPanel.add(addBack);
        setLocationRelativeTo(null);

    }
}





