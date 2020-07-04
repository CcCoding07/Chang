package DAL.record;

import java.util.Date;
import java.util.List;

public interface IRecord {
    //1 lookup medical record according to username
    List<MedicalRecord> viewRecord(String username);

    //2  lookup medical record according to username and date
    List<MedicalRecord> viewRecordByDate(String username, Date statTime,Date endTime);

    //3 for doctor to edit medical record
    void updateRecord(MedicalRecord medicalRecord);

    //4 for doctor to add new medical record to the system
    void newRecord(MedicalRecord medicalRecord);
}
