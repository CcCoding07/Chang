package DAL.information;

public interface IInfo {
    //1 点开页面自动查询刷新信息
    Person_info flash(String id);
    //2 更改信息之后自动提交保存
    void updateInfo(Person_info person_info);
    //3 每次注册成功后，自动插入一条空记录
    void autoInsert(Person_info person_info);
}
