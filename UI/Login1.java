package UI;
import BLL.Client;
import DAL.acoount.User;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * author：Chang Dong
 * data：03/12/2020
 * login page for patient
 */
// login page
class MyFrame3 extends JFrame
{
    private Client send = new Client("localhost", 7780);
    private User user;
    private Schedule schedule;
    private Container container;
    private JLabel systemLabel,userNameLabel,passwordLabel,newUserLabel;
    private JTextField jtUserName;
    private JPasswordField jtPassword;
    private JButton jbLogin,jbCancel,jbSignup;
    public MyFrame3()
    {
        super("Welcome");
        container=getContentPane();
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
        setLocation(400,300);
        setResizable(false);
    }

    public void createFace()
    {
        systemLabel = new JLabel("Hospital Online Appointment System");
        newUserLabel = new JLabel("New User");
        userNameLabel = new JLabel("User Name");
        passwordLabel = new JLabel("Password");

        jtUserName = new JTextField(20);
        jtPassword = new JPasswordField(20);

        jbLogin=new JButton("Login");
        jbCancel=new JButton("Cancel");
        jbSignup=new JButton("Sign up");
    }
    public void Locate()
    {
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
        jbSignup.setBounds(310,320,60,20);

    }
    public void addFace()
    {

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
    public void loginHandle()
    {
        jbLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String str1 = jtUserName.getText();
                String str2 = jtPassword.getText();
                int mode = 2;
                //user = new User(str1,str2,mode);
                user = new User(str1, str2, mode);
                String string = send.sendData(user);
                System.out.println(string);
                if (string.equals("login successful")) {
                    JOptionPane.showMessageDialog(null, "login successfully! ");
                    dispose();
                    mainPage1 mainpg = new mainPage1(user,send);
                    mainpg.setSize(600, 400);
                    mainpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainpg.setVisible(true);
                }
                else if (string.equals("Username input error")) {
                    JOptionPane.showMessageDialog(null, "user name is wrong, please try agian");
                    dispose();
                }
                else if (string.equals("Password input error")) {
                    JOptionPane.showMessageDialog(null, "password is wrong, please try agian");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have permission to access");
                }
            }
    });
        container.add(jbLogin);
    }
    public void signUpHandle()
    {
        ButtonHandler2 bh=new ButtonHandler2();
        jbSignup.addActionListener(bh);
    }
    public  class ButtonHandler2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            MyFrame2 myfra=new MyFrame2(send);
            myfra.setBounds(500,200,600,400);
            myfra.setVisible(true);
            dispose();
        }
    }
    public void cancleHandle()
    {
        jbCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
    }
}

//sign up page
class MyFrame4 extends JFrame
{
    private Client send;
    private Container container;
    private JLabel sSytemLbel,sUserName,sPassword1,sPassword2;
    private JTextField sjtfUserName;
    private JPasswordField sjtPassword1,sjtPassword2;
    private JButton jbsignup1,jbcancle2;
    private User user;
    public MyFrame4(Client send)
    {
        super("Sign Up");
        container=getContentPane();
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
    public void createFace()
    {
        sSytemLbel = new JLabel("User Sign Up");
        sUserName = new JLabel("User Name");
        sPassword1= new JLabel("Password");
        sPassword2 = new JLabel("Conform Password");
        sjtfUserName = new JTextField(20);
        sjtPassword1 = new JPasswordField(20);
        sjtPassword2 = new JPasswordField(20);

        jbsignup1=new JButton("Sign up");
        jbcancle2=new JButton("Cancel");
    }
    public void Locate()
    {
        sjtfUserName.setEditable(true);
        sjtPassword1.setEditable(true);
        sjtPassword2.setEditable(true);
        sSytemLbel.setBounds(230, 30, 120, 20);
        sUserName.setBounds(150, 70, 80, 40);
        sPassword1.setBounds(150, 110, 80, 40);
        sPassword2.setBounds(150, 150, 120, 40);
        sjtfUserName.setBounds(270, 80, 150, 20);
        sjtPassword1.setBounds(270, 120, 150, 20);
        sjtPassword2.setBounds(270, 160, 150, 20);
        jbsignup1.setBounds(270, 190, 60, 20);
        jbcancle2.setBounds(340, 190, 60, 20);
    }
    public void addFace()
    {
        container.add(sSytemLbel);
        container.add(sUserName);
        container.add(sjtfUserName);
        container.add(sPassword1);
        container.add(sPassword2);
        container.add(sjtPassword1);
        container.add(sjtPassword2);
        container.add(jbsignup1);
        container.add(jbcancle2);
    }
    public void signupHandle()
    {
        //process  button
        jbsignup1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(1);
                String str1=sjtfUserName.getText();
                String str2=sjtPassword1.getText();
                String str3=sjtPassword2.getText();
                int mode = 1;
                System.out.println(str3);
                user = new User(str1,str2,mode);
                //System.out.println(send.sendData(user));
                String string=send.sendData(user);
                if(string.equals("registration success")){
                    JOptionPane.showMessageDialog(null, "Sign up successfully!");
                    dispose();  
                    MyFrame1 myfra=new MyFrame1(user,new Doctor(),send);
                    myfra.setSize(600,400);
                    myfra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    myfra.setVisible(true);  
                }
                 if(string.equals("Invalid username input")){
                    JOptionPane.showMessageDialog(null, "The User Name can only contain letters and numbers,please try again ");
                    dispose();  
                }
                 if(string.equals("Invalid password input")){
                    JOptionPane.showMessageDialog(null, "The password can only contain letters and numbers, please try again ");
                    dispose();  
                }

                if(string.equals( "Duplicate username, please re-enter")){
                    JOptionPane.showMessageDialog(null, "The user name have already existed, please user other user name ");
                    dispose();  
                }
            }
        });
        container.add(jbsignup1);

    }
    public void cancleHandle()
    {
        jbcancle2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
}

class UserRegister2
{
    public static void main(String args[])
    {
        MyFrame3 myfra=new MyFrame3();
        myfra.setSize(600,400);
        myfra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myfra.setVisible(true);
    }
}