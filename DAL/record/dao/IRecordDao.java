package DAL.record.dao;

import DAL.record.MedicalRecord;

import java.sql.Date;
import java.util.List;

public interface IRecordDao {
    void insertRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> selectByUsername(String id);

    void updateRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> select(String username, Date startTime, Date endTime);

}
