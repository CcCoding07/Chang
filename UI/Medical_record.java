package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.record.MedicalRecord;
import DAL.reservation.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class Medical_record extends JFrame {
    private Container container;
    private JLabel systemLable, userName_lable, initialDate_lable, endDate_lable, tips;
    private JTextField userName_text, initialDate_text, endDate_text;
    private JButton search, back;
    private Client send ;
    private User user;
    private Doctor doctor;

    public Medical_record(User user,Doctor doctor,Client send) {
        super("Medical Record");
        this.user = user;
        this.doctor = doctor;
        this.send = send;
        container = getContentPane();
        container.setLayout(null);
        // create text box of button label
        createFace();
        Locate();
        // add text box of button label
        addFace();
        action();

        setLocation(400, 300);
        setResizable(false);
    }

    private void createFace() {
        systemLable = new JLabel("Medical Record System");
        userName_lable = new JLabel("User name: ");
        initialDate_lable = new JLabel("Initial date: ");
        endDate_lable = new JLabel("End date: ");
        tips = new JLabel("Format of time: 2020-03-01");

        userName_text = new JTextField(20);
        initialDate_text = new JTextField(10);
        endDate_text = new JTextField(10);

        search = new JButton("Search");
        back = new JButton("Back");
    }

    private void Locate() {
        userName_text.setEditable(true);
        initialDate_text.setEditable(true);
        endDate_text.setEditable(true);

        systemLable.setBounds(190, 30, 280, 20);

        userName_lable.setBounds(150, 70, 80, 20);
        userName_text.setBounds(220, 70, 150, 20);

        initialDate_lable.setBounds(150, 105, 80, 20);
        initialDate_text.setBounds(220, 105, 120, 20);

        endDate_lable.setBounds(150, 130, 80, 20);
        endDate_text.setBounds(220, 130, 120, 20);

        tips.setBounds(360, 118, 200, 20);

        search.setBounds(200, 200, 80, 20);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iTime;
                String eTime;
                if(initialDate_text.getText().equals("")){
                   iTime = "1970-01-01";
                    System.out.println(1);
                } else{
                    iTime = initialDate_text.getText();
                    System.out.println(2);
                }
                if(endDate_text.getText().equals("")){
                    eTime = "2070-01-01";
                    System.out.println(3);
                } else{
                    eTime = endDate_text.getText();
                    System.out.println(4);
                }

                try {
                    String string = send.sendData(new MedicalRecord(userName_text.getText(), iTime, eTime, 2));
                    System.out.println(string);
                    List<MedicalRecord> list = MedicalRecord.valueOf(string);
                    dispose();
                    PatientCheckRecord1 test = new PatientCheckRecord1(list,user,doctor,send);
                    test.setSize(600, 600);
                    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    test.setVisible(true);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });
        back.setBounds(340, 200, 80, 20);
    }

    private void addFace() {
        container.add(systemLable);
        container.add(userName_lable);
        container.add(userName_text);
        container.add(initialDate_lable);
        container.add(initialDate_text);
        container.add(endDate_lable);
        container.add(endDate_text);
        container.add(tips);
        container.add(search);
        container.add(back);
    }

    private void action() {
        back.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       mainPage goBack = new mainPage(user,doctor,send);
                                       goBack.setSize(600, 400);
                                       goBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       goBack.setVisible(true);
                                       System.out.println(2);
                                       dispose();
                                       System.out.println(1);
                                   }
                               }

        );
        container.add(back);
    }

//    public static void main(String args[]) {
//        Medical_record test = new Medical_record();
//        test.setSize(600, 400);
//        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        test.setVisible(true);
//    }

}

class mode3 extends JFrame {
    private Container container;

    private JLabel systemLable, userName_lable, date_lable, tips;
    private JTextField userName_text, date_text;
    private JButton  insert, back;
    private Client send;
    private User user;
    private Doctor doctor;

    public mode3(User user,Doctor doctor,Client send) {
        super("Medical Record");
        this.user = user;
        this.doctor = doctor;
        this.send = send;
        container = getContentPane();
        container.setLayout(null);
        // create text box of button label
        createFace();
        Locate();
        // add text box of button label
        addFace();
        action();

        setLocation(400, 300);
        setResizable(false);
    }

    private void createFace() {
        systemLable = new JLabel("Medical Record System");
        userName_lable = new JLabel("User name: ");
        date_lable = new JLabel("Initial date: ");
        tips = new JLabel("Format of time: 2020-3-17");

        userName_text = new JTextField(20);
        date_text = new JTextField(10);

        insert = new JButton("Insert");
        back = new JButton("Back");
    }

    private void Locate() {
        userName_text.setEditable(true);
        date_text.setEditable(true);

        systemLable.setBounds(230, 30, 150, 20);

        userName_lable.setBounds(150, 70, 80, 20);
        userName_text.setBounds(220, 70, 150, 20);

        date_lable.setBounds(150, 130, 80, 20);
        date_text.setBounds(220, 130, 120, 20);

        insert.setBounds(220, 200, 80, 20);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = userName_text.getText();
                    String date = date_text.getText();
                    send.sendData(new MedicalRecord(username,date,4));
                    //做一个弹窗告诉修改成功
                    dispose();
                    mode4 test = new mode4(username,date,send);
                    test.setSize(600, 600);
                    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    test.setVisible(true);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        back.setBounds(340, 200, 80, 20);
    }

    private void addFace() {
        container.add(systemLable);
        container.add(userName_lable);
        container.add(userName_text);
        container.add(date_lable);
        container.add(date_text);
        container.add(insert);
        container.add(back);
    }

    private void action() {
        back.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       mainPage goBack = new mainPage(user,doctor,send);
                                       goBack.setSize(600, 400);
                                       goBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       goBack.setVisible(true);
                                       System.out.println(2);
                                       dispose();
                                       System.out.println(1);
                                   }
                               }

        );
        container.add(back);
    }

//    public static void main(String args[]) {
////        User user = new User();
//        mode3 test = new mode3();
//        test.setSize(600, 400);
//        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        test.setVisible(true);
//
//    }

}

class mode4 extends JFrame {
    private Container container;
    private JLabel systemLable, userName_lable, Date_lable, sickness_lable, drug_lable, description_lable, tips;
    private JTextField userName_text, date_text, sickness_text, drug_text, description_text;
    private JButton search, update, back;
    private String username,date;
    private Client send;
    private User user;
    private Doctor doctor;

    public mode4(String username,String date,Client send) {
        super("Medical Record");
        this.username = username;
        this.date = date;
        this.send = send;
        container = getContentPane();
        container.setLayout(null);
        // create text box of button label
        createFace();
        Locate();
        // add text box of button label
        addFace();
        action();

        setLocation(400, 300);
        setResizable(false);
    }

    private void createFace() {
        systemLable = new JLabel("Medical Record System");
        userName_lable = new JLabel("User name: ");
        Date_lable = new JLabel("Date");
        sickness_lable = new JLabel("Sickness");
        drug_lable = new JLabel("Drug");
        description_lable = new JLabel("Description");
        tips = new JLabel("Format: 2020-01-01");

        userName_text = new JTextField(20);

        date_text = new JTextField(10);

        sickness_text = new JTextField(50);
        drug_text = new JTextField(50);
        description_text = new JTextField(200);

        update = new JButton("Update");
        back = new JButton("Back");
    }

    private void Locate() {
        userName_text.setText(username);
        userName_text.setEditable(false);
        date_text.setText(date);
        date_text.setEditable(false);
        sickness_text.setEditable(true);
        drug_text.setEditable(true);
        description_text.setEditable(true);

        systemLable.setBounds(230, 30, 150, 20);

        userName_lable.setBounds(150, 70, 80, 20);
        userName_text.setBounds(220, 70, 150, 20);

        Date_lable.setBounds(150, 100, 80, 20);
        tips.setBounds(400, 100, 200, 20);
        date_text.setBounds(220, 100, 150, 20);

        sickness_lable.setBounds(150, 130, 80, 20);
        sickness_text.setBounds(220, 130, 150, 60);

        drug_lable.setBounds(150, 200, 80, 20);
        drug_text.setBounds(220, 200, 150, 40);

        description_lable.setBounds(150, 250, 80, 20);
        description_text.setBounds(220, 250, 300, 100);

        update.setBounds(240, 400, 80, 20);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = date_text.getText();
                String string = null;
                try {
                    string = send.sendData(new MedicalRecord(userName_text.getText(), time, time, 2));
                System.out.println(string);
                List<MedicalRecord> list = MedicalRecord.valueOf(string);
                MedicalRecord medicalRecord = list.get(0);
                send.sendData(new MedicalRecord(sickness_text.getText(),drug_text.getText(),description_text.getText(),medicalRecord.getId(),3));

                    //弹窗


                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        back.setBounds(360, 400, 80, 20);
    }

    private void addFace() {
        container.add(systemLable);
        container.add(userName_lable);
        container.add(userName_text);
        container.add(Date_lable);
        container.add(date_text);
        container.add(sickness_lable);
        container.add(sickness_text);
        container.add(drug_lable);
        container.add(drug_text);
        container.add(description_lable);
        container.add(description_text);
        container.add(update);
        container.add(back);
        container.add(tips);
    }

    private void action() {
        back.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       mainPage goBack = new mainPage(user,doctor,send);
                                       goBack.setSize(600, 400);
                                       goBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       goBack.setVisible(true);
                                       System.out.println(2);
                                       dispose();
                                       System.out.println(1);
                                   }
                               }

        );
        container.add(back);
    }

    public void signUpHandle() {
        mode4.goback bh = new mode4.goback();
        back.addActionListener(bh);
    }

    class goback implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mode4 myfra = new mode4(username,date,send);
            myfra.setBounds(500, 200, 600, 400);
            myfra.setVisible(true);
            dispose();
        }
    }

//    public static void main(String args[]) {
//        mode4 test = new mode4();
//        test.setSize(600, 600);
//        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        test.setVisible(true);
//    }
}


