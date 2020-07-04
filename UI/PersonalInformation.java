package UI;

import BLL.Client;
import DAL.acoount.User;
import DAL.information.Person_info;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * author：Chang Dong
 * data：03/13/2020
 */
// login page
class PersonalInformation extends JFrame {
    private Client send = new Client("localhost", 7780);
    private User user;
    private JPanel contentPane;
    private Person_info person_info;

    /**
     * Launch the application.
     */
    //public static void main(String[] args) {
     //   EventQueue.invokeLater(new Runnable() {
        //    public void run() {
            //    try {
              //      PersonalInformation  frame = new  PersonalInformation();//add the object
              //      frame.setVisible(true);
              //  } catch (Exception e) {
              //      e.printStackTrace();
              //  }
            //}
       // });
   // }

    /**
     * create frame
     */
    public PersonalInformation(User user,Client send,Person_info person_info) {
        this.user = user;
        this.send = send;
        this.person_info = person_info;
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Personal Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel personalPanel = new JPanel();
        personalPanel.setLayout(null);
        personalPanel.setBorder(new TitledBorder(null, "Personal Information：", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        personalPanel.setBounds(15, 15, 380, 550);
        contentPane.add(personalPanel);

        // display first name
        JLabel lblFirstname = new JLabel("First Name：");
        lblFirstname .setBounds(21, 40, 134, 17);
        JTextField sjtFirstname=new JTextField(20);
        sjtFirstname.setText(person_info.getFirst_name());
        sjtFirstname.setBounds(184,43,120,20);
        personalPanel.add(lblFirstname);
        personalPanel.add(sjtFirstname);

        // display last name
        JLabel lblLastname = new JLabel("Last Name：");
        lblLastname .setBounds(21, 70, 134, 17);
        JTextField sjtLastname=new JTextField(20);
        sjtLastname.setText(person_info.getLast_name());
        sjtLastname.setBounds(184,73,120,20);
        personalPanel.add(lblLastname);
        personalPanel.add(sjtLastname);

        //Display the gender
        JLabel lblGender = new JLabel("Gender：");
        lblGender .setBounds(21, 100, 134, 17);
        personalPanel.add(lblGender );
        //Display the gender chosen
        JComboBox<String> genderField = new JComboBox<String>();
        String[] gender = new String[]{ "", "Male","Female "};
        int genderIndex = 0;
        genderField.setModel(new DefaultComboBoxModel<String>(gender));
        for (int i = 0; i < gender.length; i++) {
            if(person_info.getGender().equals(gender[i]))
                genderIndex = i;
        }
        genderField.setSelectedIndex(genderIndex);
        genderField.setBounds(184, 103, 134, 20);
        personalPanel.add(genderField);

        //Display the blood type
        JLabel lblBlood = new JLabel("Blood type：");
        lblBlood .setBounds(21, 130, 134, 17);
        personalPanel.add(lblBlood );
        // Display the blood type chosen
        JComboBox<String> bloodField = new JComboBox<String>();
        String[] blood = new String[]{ "", "A","B","O","AB"};
        int bloodIndex = 0;
        bloodField.setModel(new DefaultComboBoxModel<String>(blood));// add the doctor's name(how?)
        for (int i = 0; i < blood.length; i++) {
            if(person_info.getBlood_group().equals(blood[i]))
                bloodIndex = i;
        }
        bloodField.setSelectedIndex(bloodIndex);

        bloodField.setBounds(184, 133, 134, 20);
         personalPanel.add(bloodField);

        //Display the Allergy History
        JLabel lblAllergyHistory = new JLabel("Allergy history ：");
        lblAllergyHistory.setBounds(21, 160, 134, 17);
        personalPanel.add(lblAllergyHistory );
        //Display the text field of Allergy History
        JTextArea tAllergy = new JTextArea();
        tAllergy.setText(person_info.getDrug_allergy_history());
        tAllergy.setFont(new Font("Serif", Font.ITALIC, 16));
        tAllergy.setBounds(185,163,134,35);
        tAllergy.setLineWrap(true);
        tAllergy.setWrapStyleWord(true);
        personalPanel.add(tAllergy);

        // display phone number
        JLabel lblPhonenumber = new JLabel("Phone Number：");
        lblPhonenumber .setBounds(21, 210, 134, 17);
        JTextField sjtPhonenumber=new JTextField(20);
        sjtPhonenumber.setText(person_info.getPhone_number());
        sjtPhonenumber.setBounds(185,213,134,20);
        personalPanel.add(lblPhonenumber);
        personalPanel.add(sjtPhonenumber);

        // display email
        JLabel lblEmail = new JLabel("Email：");
        lblEmail .setBounds(21, 240, 134, 17);
        JTextField sjtEmail=new JTextField(20);
        sjtEmail.setText(person_info.getE_mail());
        sjtEmail.setBounds(185,243,134,20);
        personalPanel.add(lblEmail);
        personalPanel.add(sjtEmail);

        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              mainPage1 patient = new mainPage1(user,send);
                                           patient.setBounds(500, 200, 600, 400);
                                           patient.setVisible(true);
                                           dispose();
                                           setLocationRelativeTo(null);
            }
        });
        btnBack.setBounds(200, 290, 89, 23);
        personalPanel.add(btnBack);
        setLocationRelativeTo(null);

//        // mode == 2 进行更新操作
//        JButton Save = new JButton("Save");
//        // TO BE FINISHED
//        Save.setBounds(180, 290, 89, 23);
//       personalPanel.add(Save);

       JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String str1=sjtFirstname.getText();
                String str2=sjtLastname.getText();
                String id= user.getUsername();
                // exsits possible problem
                String str3=genderField.getPrototypeDisplayValue();
                String str4=bloodField.getPrototypeDisplayValue();
                String str5=tAllergy.getText();
                String str6=sjtEmail.getText();
                String str7=sjtPhonenumber.getText();
                int mode = 2;
                Person_info person_info = new Person_info(str1,str2,id,str3,str4,str5,str6,str7,mode);
                String string=send.sendData(person_info);
                JOptionPane.showMessageDialog(null, "the personal information has been updated successfully");//如果注册成功，弹窗显示注册成功
                dispose();  //填写完信息后关闭个人信息页面
            }
        });
        btnUpdate.setBounds(70, 290, 89, 23);
        personalPanel.add(btnUpdate );
        setLocationRelativeTo(null);



    }


}




