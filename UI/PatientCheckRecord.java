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
import java.text.ParseException;
import java.util.List;

/**
 * @author Chang Dong
 * @version 2020-03-17
 * the class for patients to check their record
 */
class PatientCheckRecord extends JFrame {
    private JPanel contentPane;
    private User user ;
    private Client send;
    private Doctor doctor;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    PatientCheckRecord frame = new PatientCheckRecord();//add the object
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
    public PatientCheckRecord(User user,Doctor doctor,Client send) {
        this.user = user;
        this.doctor = doctor;
        this.send = send;
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Check the Record");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel checkRecord = new JPanel();
        checkRecord.setLayout(null);
        checkRecord.setBorder(new TitledBorder(null, "Check the Record：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        checkRecord.setBounds(15, 15, 580, 350);
        contentPane.add(checkRecord);

        //Display the initial date
        JLabel lblInitialdate = new JLabel("Initial Date：");
        lblInitialdate.setBounds(21, 50, 200, 17);
        checkRecord.add(lblInitialdate);
        //input field
        JTextField initialDate = new JTextField();
        initialDate.setBounds(184, 53, 200, 20);

        checkRecord.add(initialDate);
        //Display tips
        JLabel lblTips = new JLabel("(Format：2020-01-01)");
        lblTips.setBounds(390, 53, 200, 17);
        checkRecord.add(lblTips);

        //Display the end date
        JLabel lblEndDate = new JLabel("End Date：");
        lblEndDate.setBounds(21, 80, 134, 17);
        checkRecord.add(lblEndDate);
        //Display tips
        JLabel lblTips1 = new JLabel("(Format：2020-01-01)");
        lblTips1.setBounds(390, 83, 200, 17);
        checkRecord.add(lblTips1);

        //input field
        JTextField endDate = new JTextField();
        endDate.setBounds(184, 83, 200, 20);
        checkRecord.add(endDate);

        JButton addAppBtn = new JButton("Search");
        //to be finished
        addAppBtn.setBounds(200, 130, 100, 23);
        checkRecord.add(addAppBtn);
        addAppBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iTime;
                String eTime;
                if(initialDate.getText().equals("")){
                    iTime = "1970-01-01";
                    System.out.println(1);
                } else{
                    iTime = initialDate.getText();
                    System.out.println(2);
                }
                if(endDate.getText().equals("")){
                    eTime = "2070-01-01";
                    System.out.println(3);
                } else{
                    eTime = endDate.getText();
                    System.out.println(4);
                }
                try {
                    String string = send.sendData(new MedicalRecord(user.getUsername(), iTime, eTime, 2));
                    System.out.println(string);
                    List<MedicalRecord> list = MedicalRecord.valueOf(string);
                    dispose();
                    PatientCheckRecord2 test = new PatientCheckRecord2(list,user,doctor,send);
                    test.setSize(600, 600);
                    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    test.setVisible(true);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });
        setLocationRelativeTo(null);

        JButton addBack = new JButton("Back");
        addBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
      mainPage1 main1 = new mainPage1(user,send);

        main1.setBounds(500, 200, 600, 400);
       main1.setVisible(true);
        dispose();
        setLocationRelativeTo(null);
        checkRecord.add(addBack);

    }

});}}




