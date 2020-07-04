package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * author：Chang Dong
 * date：03/12/2020
 * The login in page for the doctor
 */
// login page
class MyFrame1 extends JFrame {
    private Client send;
    private User user, user1;
    private Doctor doctor;
    private Schedule schedule;
    private Container container;
    private JLabel systemLabel, userNameLabel, passwordLabel, newUserLabel;
    private JTextField jtUserName;
    private JPasswordField jtPassword;
    private JButton jbLogin, jbCancel, jbSignup;

    public MyFrame1(User user, Doctor doctor, Client send) {
        super("Welcome");
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
        // process login
        loginHandle();
        // process sign up
        signUpHandle();
        // process cancel
        cancleHandle();
        setLocation(400, 300);
        setResizable(false);
    }

    public void createFace() {
        systemLabel = new JLabel("Hospital Online Appointment System");
        newUserLabel = new JLabel("New User");
        userNameLabel = new JLabel("User Name");
        passwordLabel = new JLabel("Password");

        jtUserName = new JTextField(20);
        jtPassword = new JPasswordField(20);

        jbLogin = new JButton("Login");
        jbCancel = new JButton("Cancel");
        jbSignup = new JButton("Sign up");
    }

    public void Locate() {
        jtUserName.setEditable(true);
        jtPassword.setEditable(true);

        systemLabel.setBounds(190, 40, 280, 20);
        userNameLabel.setBounds(150, 70, 80, 40);
        passwordLabel.setBounds(150, 110, 80, 40);
        jtUserName.setBounds(220, 80, 150, 20);
        jtPassword.setBounds(220, 120, 150, 20);
        jbLogin.setBounds(225, 160, 60, 20);
        jbCancel.setBounds(305, 160, 60, 20);
        newUserLabel.setBounds(220, 310, 100, 40);
        jbSignup.setBounds(310, 320, 60, 20);

    }

    public void addFace() {

        container.add(systemLabel);
        container.add(userNameLabel);
        container.add(jtUserName);
        container.add(passwordLabel);
        container.add(jtPassword);
        container.add(jbLogin);
        container.add(jbCancel);
        container.add(newUserLabel);
        container.add(jbSignup);
    }

    public void loginHandle() {
        jbLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s=sendData(new User,username,mode=3);s="0",s="1";when doctor registered， new User, username, password, classification, mode=1)
                String str1 = jtUserName.getText();
                String str2 = jtPassword.getText();
                int mode = 2;
                user1 = new User(str1, str2, 3);
                String s = send.sendData(user1);
                System.out.println(s);
                if (s.equals("0")) {
                    user = new User(str1, str2, mode);
                    String string = send.sendData(user);
                    doctor = Doctor.valueOf(send.sendData(new Doctor(user, 3)));
                    
                    if (string.equals("login successful")) {
                        JOptionPane.showMessageDialog(null, "login successfully! ");
                        dispose();
                        mainPage mainpg = new mainPage(user, doctor, send);
                        mainpg.setSize(600, 400);
                        mainpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainpg.setVisible(true);
                    }
                    
                    if (string.equals("Username input error")) {
                        JOptionPane.showMessageDialog(null, "user name is wrong, please try agian");
                        dispose();
                    }
                    
                    if (string.equals("Password input error")) {
                        JOptionPane.showMessageDialog(null, "password is wrong, please try agian");
                        dispose();
                    }
                } 
                    else {
                    JOptionPane.showMessageDialog(null, "You don't have permission to access");
                }

            }
        });
        container.add(jbLogin);
    }

    public void signUpHandle() {
        ButtonHandler2 bh = new ButtonHandler2();
        jbSignup.addActionListener(bh);
    }

    public class ButtonHandler2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MyFrame2 myfra = new MyFrame2(send);
            myfra.setBounds(500, 200, 600, 400);
            myfra.setVisible(true);
            dispose();
        }
    }

    public void cancleHandle() {
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

//sign up page
class MyFrame2 extends JFrame {
    private Client send;
    private User user;
    private Doctor doctor;
    private Container container;
    private JLabel sSytemLbel, Name, Department, sUserName, sPassword1, sPassword2;
    private JTextField sjtfUserName, sjtName;
    private JPasswordField sjtPassword1, sjtPassword2;
    private JButton jbsignup1, jbcancle2;
    private JComboBox<String> departmentField = new JComboBox<String>();

    public MyFrame2(Client send) {
        super("Sign Up");
        container = getContentPane();
        container.setLayout(null);
        this.send = send;
        // create text box of label button
        createFace();
        signupHandle();
        Locate();
        //add text box of label button
        addFace();
        // process cancel button
        cancleHandle();
    }

    public void createFace() {
        sSytemLbel = new JLabel("User Sign Up");
        Name = new JLabel("Name");
        Department = new JLabel("Department");
        sUserName = new JLabel("User Name");
        sPassword1 = new JLabel("Password");
        sPassword2 = new JLabel("Conform Password");
        sjtName = new JTextField(20);

        departmentField.setModel(new DefaultComboBoxModel<String>(new String[]{"",
                "internal medicine", "plastic surgery"}));
        departmentField.setBounds(270, 70, 150, 20);
        container.add(departmentField);
        sjtfUserName = new JTextField(20);
        sjtPassword1 = new JPasswordField(20);
        sjtPassword2 = new JPasswordField(20);

        jbsignup1 = new JButton("Sign up");
        jbcancle2 = new JButton("Cancel");
    }

    public void Locate() {
        Name.setVisible(true);
        Department.setVisible(true);
        sjtName.setEditable(true);
        sjtfUserName.setEditable(true);
        sjtPassword1.setEditable(true);
        sjtPassword2.setEditable(true);
        sSytemLbel.setBounds(230, 20, 120, 20);
        Name.setBounds(150, 50, 120, 20);
        Department.setBounds(150, 70, 120, 20);
        sUserName.setBounds(150, 90, 80, 40);
        sPassword1.setBounds(150, 110, 80, 40);
        sPassword2.setBounds(150, 130, 120, 40);
        sjtfUserName.setBounds(270, 100, 150, 20);
        sjtName.setBounds(270, 50, 150, 20);
        sjtPassword1.setBounds(270, 120, 150, 20);
        sjtPassword2.setBounds(270, 140, 150, 20);
        jbsignup1.setBounds(270, 190, 60, 20);
        jbcancle2.setBounds(340, 190, 60, 20);
    }

    public void addFace() {
        container.add(sSytemLbel);
        container.add(sUserName);
        container.add(Name);
        container.add(sjtName);
        container.add(Department);
        container.add(sjtfUserName);
        container.add(sPassword1);
        container.add(sPassword2);
        container.add(sjtPassword1);
        container.add(sjtPassword2);
        container.add(jbsignup1);
        container.add(jbcancle2);
    }

    public void signupHandle() {
        //process  button
        jbsignup1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(1);
                String str1 = sjtfUserName.getText();
                String str2 = sjtPassword1.getText();
                String str3 = sjtPassword2.getText();
                String str4 = sjtName.getText();
                String str5 = departmentField.getSelectedItem().toString();
                int mode = 1;
                user = new User(str1, str2, 0, mode);
                doctor = new Doctor(str1, str4, str5, 2);
                //System.out.println(send.sendData(user));
                String string = send.sendData(user);
                send.sendData(doctor);
                if (string.equals("registration success")) {
                    JOptionPane.showMessageDialog(null, "Sign up successfully!");//when sign up sucessfully, pop the window
                    dispose();  
                    MyFrame1 myfra = new MyFrame1(user, doctor, send);// when sign up successfully, return back the login page
                    myfra.setSize(600, 400);
                    myfra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    myfra.setVisible(true);  
                }
                if (string.equals("Invalid username input")) {
                    JOptionPane.showMessageDialog(null, "The User Name can only contain letters and numbers,please try again ");
                    dispose();  
                }
                if (string.equals("Invalid password input")) {
                    JOptionPane.showMessageDialog(null, "The password can only contain letters and numbers, please try again ");
                    dispose(); 
                }

                if (string.equals("Duplicate username, please re-enter")) {
                    JOptionPane.showMessageDialog(null, "The user name have already existed, please user other user name ");
                    dispose();  
                }
            }
        });
        container.add(jbsignup1);

    }

    public void cancleHandle() {
        jbcancle2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

// Test
//class UserRegister
//{
//    public static void main(String args[])
//    {
//        MyFrame1 myfra=new MyFrame1();
//        myfra.setSize(600,400);
//        myfra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        myfra.setVisible(true);
//    }
//}