package DAL.information;

import java.io.Serializable;

public class Person_info implements Serializable {
    private String first_name = null;
    private String last_name = null;
    private String id = null;
    private String gender = null;
    private String blood_group = null;
    private String drug_allergy_history = null;
    private String phone_number = null;
    private String e_mail = null;
    private int mode;
    private static final long serialVersionUID = 4L;

    public Person_info() {
        super();
    }

    public Person_info(String id) {
        this.id = id;
    }

    public Person_info(String id, int mode) {
        this.id = id;
        this.mode = mode;
    }

    public Person_info(String first_name, String last_name, String id, String gender, String blood_group, String drug_allergy_history, String phone_number, String e_mail, int mode) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = id;
        this.gender = gender;
        this.blood_group = blood_group;
        this.drug_allergy_history = drug_allergy_history;
        this.phone_number = phone_number;
        this.e_mail = e_mail;
        this.mode = mode;
    }

    @Override
    public String toString() {
        return String.join(",", first_name,last_name,gender,blood_group,drug_allergy_history,phone_number,e_mail);
    }
    public static Person_info valueOf(String string){
        Person_info person_info = new Person_info();
        String[] strings = string.split(",");
        person_info.setFirst_name(strings[0]);
        person_info.setLast_name(strings[1]);
        person_info.setGender(strings[2]);
        person_info.setBlood_group(strings[3]);
        person_info.setDrug_allergy_history(strings[4]);
        person_info.setPhone_number(strings[5]);
        person_info.setE_mail(strings[6]);
        return person_info;

    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getDrug_allergy_history() {
        return drug_allergy_history;
    }

    public void setDrug_allergy_history(String drug_allergy_history) {
        this.drug_allergy_history = drug_allergy_history;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
