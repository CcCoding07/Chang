package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.record.MedicalRecord;
import DAL.reservation.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chang Dong
 * @version 2019-**-**
 */
public class PatientCheckRecord1 extends JFrame {
    private Container container;
    private JLabel systemLable, userName_lable, Date_lable, sickness_lable, drug_lable, description_lable, tips;
    private JTextField userName_text, date_text, sickness_text, drug_text, description_text;
    private JComboBox dateField;
    private JButton search, insert, back;
    private List<MedicalRecord> list;
    private User user;
    private Doctor doctor;
    private Client send;

    public PatientCheckRecord1(List<MedicalRecord> list,User user,Doctor doctor,Client send) {
        super("Medical Record");
        this.list = list;
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

        setLocation(600, 500);
        setResizable(false);
    }

    private void createFace() {
        userName_lable = new JLabel("PatientName");
        systemLable = new JLabel("Medical Record System");
        Date_lable = new JLabel("Date");
        sickness_lable = new JLabel("Sickness");
        drug_lable = new JLabel("Drug");
        description_lable = new JLabel("Description");
        tips = new JLabel("Format: 2020-01-01");

        userName_text = new JTextField(20);
        userName_text.setText(list.get(0).getUsername());

        sickness_text = new JTextField(50);
        drug_text = new JTextField(50);
        dateField = new JComboBox<String>();
        description_text = new JTextField(200);

        search = new JButton("Search");
        insert = new JButton("Insert");
        back = new JButton("Back");
    }

    private void Locate() {

        userName_text.setEditable(false);
        sickness_text.setEditable(false);
        drug_text.setEditable(false);
        description_text.setEditable(false);

        systemLable.setBounds(230, 30, 150, 20);

        Date_lable.setBounds(150, 100, 80, 20);
        tips.setBounds(400, 100, 200, 20);
        dateField.setBounds(220, 100, 150, 20);
        dateField.addItem("");
        for (MedicalRecord e : list) {
            dateField.addItem(e.getViewDate());
        }
        dateField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                //添加动作
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        for (int i = 0; i < list.size(); i++) {
                            if(dateField.getSelectedItem().equals(list.get(i).getViewDate())){
                                sickness_text.setText(list.get(i).getSickness());
                                drug_text.setText(list.get(i).getDrug());
                                description_text.setText(list.get(i).getDescription());
                            }
                        }
                        break;
                }

            }
        });

        sickness_lable.setBounds(150, 130, 80, 20);
        sickness_text.setBounds(220, 130, 150, 60);

        drug_lable.setBounds(150, 200, 80, 20);
        drug_text.setBounds(220, 200, 150, 40);

        description_lable.setBounds(150, 250, 80, 20);
        description_text.setBounds(220, 250, 300, 100);

        userName_lable.setBounds(150, 70, 80, 20);
        userName_text.setBounds(230, 70, 150, 20);

        back.setBounds(180, 400, 80, 20);
    }

    private void addFace() {
        container.add(userName_lable);
        container.add(systemLable);
        container.add(Date_lable);
        container.add(dateField);
        container.add(sickness_lable);
        container.add(sickness_text);
        container.add(drug_lable);
        container.add(drug_text);
        container.add(description_lable);
        container.add(description_text);
        container.add(userName_text);
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

    public void gobackHandle() {
        goback bh = new goback();
        back.addActionListener(bh);
    }

    class goback implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PatientCheckRecord1 myfra = new PatientCheckRecord1(list,user,doctor,send);
            myfra.setBounds(500, 200, 600, 400);
            myfra.setVisible(true);
            dispose();
        }
    }

//    public static void main(String args[]) {
//        PatientCheckRecord1 test = new PatientCheckRecord1(list);
//        test.setSize(600, 600);
//        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        test.setVisible(true);
//    }
}
