package DAL.record;

import DAL.record.dao.IRecordDao;
import DAL.record.dao.impl.RecordDaoImpl;

import java.util.Date;
import java.util.List;

public class RecordImpl implements IRecord {
    private IRecordDao recordDao = new RecordDaoImpl();

    @Override
    public List<MedicalRecord> viewRecord(String id) {
        return recordDao.selectByUsername(id);
    }

    @Override
    public List<MedicalRecord> viewRecordByDate(String username, Date statTime, Date endTime) {
        return recordDao.select(username, new java.sql.Date(statTime.getTime()), new java.sql.Date(endTime.getTime()));
    }

    @Override
    public void updateRecord(MedicalRecord medicalRecord) {
        recordDao.updateRecord(medicalRecord);
    }

    @Override
    public void newRecord(MedicalRecord medicalRecord) {
        recordDao.insertRecord(medicalRecord);
    }
}
