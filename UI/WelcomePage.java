package UI;
import BLL.Client;
import DAL.acoount.User;
import DAL.reservation.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * author：Chang Dong
 * data：03/14/2020
 * This is the welcome page of the system, the page contains two button, doctor 
 * login and patient login, user can click the button to enter the login and 
 * sign up page. Different users can only entered the corresponding page.
 */

class WelcomePage extends JFrame
{
    private Client send = new Client("localhost", 7780);
    private User user;
    private Doctor doctor;
    private Container container;
    private JLabel systemLabel;
    private JButton jbDoctor,jbPatient;
    public static void main(String args[])
    {
        WelcomePage wlp=new WelcomePage();
        wlp.setSize(600,400);
        wlp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wlp.setVisible(true);
    }
    public WelcomePage()
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
        doctorHandle();
        // process sign up
        patientHandle();
        // process cancel
        //cancleHandle();
        setLocation(400,300);
        setResizable(false);
    }

    /**
     * create the face for buttons
     */

    public void createFace()
    {
        systemLabel = new JLabel("Hospital  Online  Appointment  System");
        Font f = new Font("Big Caslon",Font.PLAIN,18);
        systemLabel.setFont(f);
        systemLabel.setForeground(Color.DARK_GRAY);
        jbDoctor=new JButton("Doctor User");
        jbPatient=new JButton("Patient User");
    }

    /**
     * locate the buttons and labels
     */
    public void Locate()
    {
        systemLabel.setBounds(150, 100, 350, 20);
        jbDoctor.setBounds(180, 160, 100, 20);
        jbPatient.setBounds(320, 160, 100, 20);

    }

    /**
     * add the button to container
     */
    public void addFace()
    {
        container.add(systemLabel);
        container.add(jbDoctor);
        container.add(jbPatient);
    }

    /**
     * add action to doctor user button， when user click this button，the page will jump to the doctor login page
     */
    public void doctorHandle()
    {
        jbDoctor.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                  MyFrame1 doct=new MyFrame1(user,doctor,send);
                    doct.setSize(600,400);
                    doct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    doct.setVisible(true);
                    dispose();


            }
        });
        container.add(jbDoctor);
    }

    /**
     * add action to patient user button， when user click this button，the page will jump to the patient login page
     */
    public void patientHandle()
    {
        jbPatient.addActionListener(new ActionListener()
                                   {
                                       public void actionPerformed(ActionEvent e)
                                       {
            MyFrame3 patient=new MyFrame3();
            patient.setBounds(500,200,600,400);
            patient.setVisible(true);
            dispose();
        }
    });
        container.add(jbPatient);
    }

}


