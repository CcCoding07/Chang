package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.information.Person_info;
import DAL.reservation.Doctor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chang Dong
 * @version 03-13-2020
 */
public class mainPage1 extends JFrame {
    private JPanel contentPane;
    private User user;
    private Client send;
    private Doctor doctor;
    //private Client send = new Client("localhost", 7780);

    /**
     * create frame
     */
    public mainPage1(User user,Client send) {
        this.user = user;
        this.send = send;
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBorder(new TitledBorder(null, "Welcome to login，please choose the services below：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        bookPanel.setBounds(15, 15, 380, 250);
        contentPane.add(bookPanel);

        JButton btnPersonal = new JButton("Personal Information");
        btnPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Person_info person_info = Person_info.valueOf(send.sendData(new Person_info(user.getUsername(), 1)));

                PersonalInformation pi=new PersonalInformation(user,send,person_info);
                pi.setBounds(500,200,600,400);
                pi.setVisible(true);
                dispose();

            }
        });
        btnPersonal.setBounds(30, 60, 200, 23);
        bookPanel.add(btnPersonal);
        setLocationRelativeTo(null);

        JButton btnBook = new JButton("Book an appointment");
        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BookAppt mp=new BookAppt();
                mp.setBounds(500,200,600,400);
                mp.setVisible(true);
                dispose();
            }
        });
        btnBook.setBounds(30, 90, 200, 23);
        bookPanel.add(btnBook);
        setLocationRelativeTo(null);



        JButton btnCheck = new JButton("Check the record");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               PatientCheckRecord check=new PatientCheckRecord(user,doctor,send);
                 check.setBounds(500, 200, 600, 400);
                check.setVisible(true);
               dispose();
               setLocationRelativeTo(null);
            }
        });
        btnCheck.setBounds(30, 120, 200, 23);
        bookPanel.add(btnCheck);}


}


/**
 * Launch the application.
 */

//class mainP1 {
    //    public static void main(String[] args) {
    //public mainP1(){
       // EventQueue.invokeLater(new Runnable() {
           // public void run() {
              //  try {
               //     mainPage1 frame = new mainPage1("cxd999","");//add the object
                //    frame.setSize(600,400);
                  //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 //   frame.setVisible(true);
               // } catch (Exception e) {
                   // e.printStackTrace();
              //  }
           // }
       // });
    //}

//}