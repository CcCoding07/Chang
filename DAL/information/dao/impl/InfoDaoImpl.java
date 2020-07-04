package DAL.information.dao.impl;

import DAL.information.Person_info;
import DAL.information.dao.IInfoDao;
import DAL.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDaoImpl implements IInfoDao {
    /**
     * 注册后自动插入空记录
     *
     * @param person_info
     */
    @Override
    public void insertInfo(Person_info person_info) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "insert into person_info(id) values(?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, person_info.getId());

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    /**
     * 点击查询时自动刷新
     *
     * @param id
     * @return
     */
    @Override
    public Person_info selectById(String id) {
        Person_info person_info = null;
        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "select * from person_info where id = ?";
        try {

            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                person_info = new Person_info();

                person_info.setId(rs.getString("id"));
                person_info.setFirst_name(rs.getString("first_name"));
                person_info.setLast_name(rs.getString("last_name"));
                person_info.setGender(rs.getString("gender"));
                person_info.setBlood_group(rs.getString("blood_group"));
                person_info.setDrug_allergy_history(rs.getString("drug_allergy_history"));
                person_info.setPhone_number(rs.getString("phone_number"));
                person_info.setE_mail(rs.getString("e_mail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return person_info;
    }

    /**
     * 用户修改后提交修改内容
     * @param person_info
     */
    @Override
    public void updateInfo(Person_info person_info) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "update person_info set first_name = ?,last_name=?,gender=?,blood_group=?,drug_allergy_history=?,phone_number=?,e_mail=?where id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,person_info.getFirst_name());
            ps.setString(2,person_info.getLast_name());
            ps.setString(3,person_info.getGender());
            ps.setString(4,person_info.getBlood_group());
            ps.setString(5,person_info.getDrug_allergy_history());
            ps.setString(6,person_info.getPhone_number());
            ps.setString(7,person_info.getE_mail());
            ps.setString(8,person_info.getId());

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }
}
