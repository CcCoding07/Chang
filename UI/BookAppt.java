package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

/**
 * author：Chang Dong
 * data：03/12/2020
 */
// login page
class BookAppt extends JFrame {
    private JPanel contentPane;
    private String startTime;
    private String endTime;
    private int startTimeHours;
    private int startTimeMins;
    // private static int patientID;
    private String type;
    private String appointmentDate;
    private String appointment;
    private String department;
    private String doctor;
    private String calendar;
    private int time = 0;
    private User user = new User("ahah", "123", 1);
    private String[] times = new String[]{"", "10:00-11:00", "11:00-12:00", "13:00-14:00", "14:00-15:00", "15:00-16:00"};
    private Client send = new Client("localhost", 7780);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookAppt frame = new BookAppt();//add the object
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * create frame
     */
    public BookAppt() {
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Book Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBorder(new TitledBorder(null, "Book a New Appointment：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        bookPanel.setBounds(15, 15, 580, 350);
        contentPane.add(bookPanel);

        //Display the Department
        JLabel lblDepartment = new JLabel("Department：");
        lblDepartment.setBounds(21, 50, 200, 17);
        bookPanel.add(lblDepartment);
        //Display the department chosen

        JComboBox<String> departmentField = new JComboBox<String>();
        JComboBox<String> doctorField = new JComboBox<String>();
        JComboBox<String> dateField = new JComboBox<String>();
        JComboBox<String> timeField = new JComboBox<String>();

        departmentField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
                "internal medicine", "plastic surgery"}));// add the doctor's name(how?)
        departmentField.setBounds(184, 53, 200, 20);
        departmentField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        department = event.getItem().toString();
                        String[] strings = send.sendData(new Doctor(department, 1)).split(",");
                        doctorField.removeAllItems();
                        for (String s : strings) {
                            doctorField.addItem(s);
                        }
                        break;
                }
            }
        });
        bookPanel.add(departmentField);
        //Display the doctor
        JLabel lblDoctor = new JLabel("Doctor：");
        lblDoctor.setBounds(21, 80, 134, 17);
        bookPanel.add(lblDoctor);

        // Display the doctor chosen
        doctorField.setModel(new DefaultComboBoxModel<String>(new String[]{""}));// add the doctor's name(how?)
        doctorField.setBounds(184, 83, 200, 20);
        bookPanel.add(doctorField);

        //Display the Date
        JLabel lblDate = new JLabel("Date：");
        lblDate.setBounds(21, 110, 134, 17);
        dateField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        doctor = doctorField.getSelectedItem().toString();
                        String[] strings = send.sendData(new Doctor(doctor, department)).split("&");
                        int date = dateField.getSelectedIndex();
                        calendar = strings[date - 1];
                        timeField.removeAllItems();
                        List<String> list = Arrays.asList(calendar.split(","));
                        for (int i = 0; i < list.size(); i++) {
                            String s = list.get(i);
                            if (s.equals("0")) {
                                timeField.addItem(times[i + 1]);
                            }
                        }
                        break;
                }
            }
        });
        bookPanel.add(lblDate);
        // Display the date chosen
        dateField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}));// add the doctor's name(how?)
        dateField.setBounds(184, 113, 200, 20);
        bookPanel.add(dateField);

        //Display the time
        JLabel lblTime = new JLabel("Time：");
        lblTime.setBounds(21, 140, 134, 17);
        bookPanel.add(lblTime);
        // Display the date chosen
        timeField.setModel(new DefaultComboBoxModel<String>(new String[]{""}));// add the doctor's name(how?)
        timeField.setBounds(184, 143, 200, 20);
        timeField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        String s = timeField.getSelectedItem().toString();
                        for (int i = 0; i < times.length; i++) {
                            if (s.equals(times[i])) {
                                time = i;
                            }
                        }
                        break;
                }
            }
        });

        bookPanel.add(timeField);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               mainPage1 main1=new mainPage1(user,send);
                main1.setBounds(500, 200, 600, 400);
                main1.setVisible(true);
                dispose();
                setLocationRelativeTo(null);

            }
        });
        btnBack.setBounds(200, 190, 100, 23);
        bookPanel.add(btnBack);
        setLocationRelativeTo(null);

        JButton addAppBtn = new JButton("Book");
        addAppBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(time);
                String s = send.sendData(new Schedule(user, doctorField.getSelectedItem().toString(),
                        String.valueOf(dateField.getSelectedIndex()), time));
                if (s.equals("The appointment was successful")) {
                    JOptionPane.showMessageDialog(null, "The appointment was successful!");
                    dispose();
                } else if (s.equals("The time has been booked")) {
                    JOptionPane.showMessageDialog(null, "The time has been booked");
                    dispose();
                } else if (s.equals("Please specify appointment time")) {
                    JOptionPane.showMessageDialog(null, "Please specify appointment time");
                    dispose();
                } else if (s.equals("This time has been booked, please try again")) {
                    JOptionPane.showMessageDialog(null, "This time has been booked, please try again");
                    dispose();
                } else if (s.equals("Select time is not within the scope of the appointment, please try again")) {
                    JOptionPane.showMessageDialog(null, "Select time is not within the scope of the appointment, please try again");
                    dispose();
                }
            }
        });
        //to be finished

        addAppBtn.setBounds(90, 190, 100, 23);
        bookPanel.add(addAppBtn);
        setLocationRelativeTo(null);
    }

}




