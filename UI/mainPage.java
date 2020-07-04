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

/**
 * @author Chang Dong
 * @version 03-13-2020
 */
public class mainPage extends JFrame {
    private JPanel contentPane;
    private User user;
    private Doctor doctor;
    private Client send;
    //private Client send = new Client("localhost", 7780);

    /**
     * create frame
     */
    public mainPage(User user,Doctor doctor ,Client send) {
        this.user = user;
        this.doctor = doctor;
        this.send = send;
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 580, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBorder(new TitledBorder(null, "Hi,Doctor，please choose the services below：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        bookPanel.setBounds(15, 15, 580, 250);
        contentPane.add(bookPanel);

        JButton btnCheckap = new JButton("Check the appointment information");
        btnCheckap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               CheckPatientAppointment cp=new CheckPatientAppointment(user,doctor,send);
                cp.setSize(600,400);
                cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cp.setVisible(true);  //
                dispose();

            }
        });
        btnCheckap.setBounds(30, 90, 250, 23);
        bookPanel.add(btnCheckap);
        setLocationRelativeTo(null);

        JButton btnCheck = new JButton("Check the Record of the Patient");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Medical_record md=new Medical_record(user,doctor,send);
                md.setSize(600,400);
                md.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                md.setVisible(true);  //
                dispose();
            }
        });
        btnCheck.setBounds(300, 90, 250, 23);
        bookPanel.add(btnCheck);
        setLocationRelativeTo(null);
    }}


    /**
     * Launch the application.
     */

//    class mainP2 {
//        //    public static void main(String[] args) {
//        public mainP2() {
//            EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    try {
//                        mainPage frame = new mainPage();//add the object
//                        frame.setSize(600, 400);
//                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                        frame.setVisible(true);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//    }
//}