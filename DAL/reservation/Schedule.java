package DAL.reservation;

import DAL.acoount.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class describe the calendar table in database
 *
 */
public class Schedule implements Serializable {
    private User user = null;
    private String id;
    private String week;
    //0 means available for appointment
    private String ten;
    private String eleven;
    private String thirteen;
    private String fourteen;
    private String fifteen;
    private int time = 0;
    private static final long serialVersionUID = 2L;

    public Schedule() {
        super();
    }

    public Schedule(String id, String week) {
        this.id = id;
        this.week = week;
        this.ten = "0";
        this.eleven = "0";
        this.thirteen = "0";
        this.fourteen = "0";
        this.fifteen = "0";
    }

    //time used to specific appoint time

    public Schedule(User user,String id, String week,int time) {
        this.user = user;
        this.id = id;
        this.week = week;
        this.time = time;
        this.ten = "0";
        this.eleven = "0";
        this.thirteen = "0";
        this.fourteen = "0";
        this.fifteen = "0";
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static List<String> getSchedule(Schedule schedule){
        List<String> list = new ArrayList<>();
        list.add(schedule.getTen());
        list.add(schedule.getEleven());
        list.add(schedule.getThirteen());
        list.add(schedule.getFourteen());
        list.add(schedule.getFifteen());
        return list;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", week='" + week + '\'' +
                ", ten=" + ten +
                ", eleven=" + eleven +
                ", thirteen=" + thirteen +
                ", fourteen=" + fourteen +
                ", fifteen=" + fifteen +
                ", time=" + time +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEleven() {
        return eleven;
    }

    public void setEleven(String eleven) {
        this.eleven = eleven;
    }

    public String getThirteen() {
        return thirteen;
    }

    public void setThirteen(String thirteen) {
        this.thirteen = thirteen;
    }

    public String getFourteen() {
        return fourteen;
    }

    public void setFourteen(String fourteen) {
        this.fourteen = fourteen;
    }

    public String getFifteen() {
        return fifteen;
    }

    public void setFifteen(String fifteen) {
        this.fifteen = fifteen;
    }
}
