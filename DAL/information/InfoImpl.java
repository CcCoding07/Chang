package DAL.information;

import DAL.information.dao.IInfoDao;
import DAL.information.dao.impl.InfoDaoImpl;

public class InfoImpl implements IInfo {
    private IInfoDao infoDaoImpl = new InfoDaoImpl();
    @Override
    public Person_info flash(String id) {
        return infoDaoImpl.selectById(id);
    }

    @Override
    public void updateInfo(Person_info person_info) {
        infoDaoImpl.updateInfo(person_info);
    }

    @Override
    public void autoInsert(Person_info person_info) {
        infoDaoImpl.insertInfo(person_info);
    }
}
