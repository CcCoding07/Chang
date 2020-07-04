package DAL.create;

import DAL.create.dao.ICreateTableDao;
import DAL.create.dao.impl.CreateTableDao;

public class CreateTableImpl implements ICreateTableImpl{
    @Override
    public void createTable() {
        ICreateTableDao createTableDao = new CreateTableDao();
        createTableDao.createUserAccount();
        createTableDao.createCalendar();
        createTableDao.createDoctor();
        createTableDao.createMedicalRecord();
        createTableDao.createPersoninfo();
    }
}
