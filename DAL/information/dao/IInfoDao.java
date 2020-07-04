package DAL.information.dao;

import DAL.information.Person_info;

public interface IInfoDao {
    void insertInfo(Person_info person_info);
    Person_info selectById(String id);
    void updateInfo(Person_info person_info);
}
