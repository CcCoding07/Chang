package DAL.record;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicalRecord implements Serializable {
    private String sickness = null;
    private String username = null;
    private String drug = null;
    private String description = null;
    private String viewDate = null;
    private int id;
    //mode == 1 lookup medical record according to username，mode == 2 lookup medical record according to date mode == 3 update medical record（id）mode == 4 insert medical record according to username
    private int mode;
    private Date date = null;
    private Date startTime = null;
    private Date endTime = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 5L;

    public MedicalRecord() {
        super();
    }

    //lookup medical record
    public MedicalRecord(String username, int mode) {
        this.username = username;
        this.mode = mode;
    }

    //lookup medical record according to specific date
    public MedicalRecord(String username, String startTime, String endTime, int mode) throws ParseException {
        this.username = username;
        this.startTime = sdf.parse(startTime);
        this.endTime = sdf.parse(endTime);
        this.mode = mode;
    }

    //insert medical record
    public MedicalRecord(String username, String date, int mode) throws ParseException {
        this.username = username;
        this.date = sdf.parse(date);
        this.mode = mode;
    }

    //update medical record
    public MedicalRecord(String sickness, String drug, String description, int id, int mode){
        this.sickness = sickness;
        this.drug = drug;
        this.description = description;
        this.id = id;
        this.mode = mode;
    }

    public static List<MedicalRecord> valueOf(String string) {
        List<MedicalRecord> list = new ArrayList<>();
        String[] strings = string.split("&");
        for (String s : strings) {
            String[] str = s.split(",");
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setSickness(str[0]);
            medicalRecord.setDrug(str[1]);
            medicalRecord.setDescription(str[2]);
            medicalRecord.setUsername(str[3]);
            medicalRecord.setViewDate(str[4]);
            medicalRecord.setId(Integer.valueOf(str[5]));
            list.add(medicalRecord);
        }
        return list;
    }

    @Override
    public String toString() {
        return String.join(",", sickness, drug, description, username,viewDate,String.valueOf(id));
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
