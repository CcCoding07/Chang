package DAL.reservation;

import DAL.acoount.User;
import DAL.record.MedicalRecord;

import javax.print.Doc;
import java.io.Serializable;

public class Doctor implements Serializable {
    private String id;
    private String name;
    private String department;
    private User user;
    //mode == 1 lookup doctor's name according to the mode
    private int mode;
    private static final long serialVersionUID = 3L;

    public Doctor() {
        super();
    }

    public Doctor(String name, String department) {
        this.id = "";
        this.name = name;
        this.department = department;
    }

    public Doctor(String id, String name, String department, int mode) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.mode = mode;
    }

    public Doctor(String department, int mode) {
        this.department = department;
        this.mode = mode;
    }

    public Doctor(User user, int mode) {
        this.user = user;
        this.mode = mode;
    }

    public static Doctor valueOf(String string) {
        Doctor doctor = new Doctor();
        String[] strings = string.split(",");
        doctor.setId(strings[0]);
        doctor.setName(strings[1]);
        doctor.setDepartment(strings[2]);
        return doctor;
    }

    @Override
    public String toString() {
        return String.join(",", id, name, department);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
