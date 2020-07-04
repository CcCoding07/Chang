package DAL.record.dao.impl;

import DAL.record.MedicalRecord;
import DAL.record.dao.IRecordDao;
import DAL.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements IRecordDao {
    /**
     *insert new medical record
     * need date and username
     *
     * @param medicalRecord
     */
    @Override
    public void insertRecord(MedicalRecord medicalRecord) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "insert into medicalrecord(username,date) values(?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, medicalRecord.getUsername());
            ps.setDate(2, new java.sql.Date(medicalRecord.getDate().getTime()));

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    /**
     *lookup medical record which need username
     * @param username
     * @return
     */
    @Override
    public List<MedicalRecord> selectByUsername(String username) {
        List<MedicalRecord> list = new ArrayList<>();
        MedicalRecord medicalRecord = null;
        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "select * from medicalrecord where username = ?";
        try {

            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                medicalRecord = new MedicalRecord();
                medicalRecord.setSickness(rs.getString("sickness"));
                medicalRecord.setUsername(rs.getString("username"));
                medicalRecord.setDrug(rs.getString("drug"));
                medicalRecord.setDescription(rs.getString("description"));
                list.add(medicalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return list;
    }

    /**
     *lookup medical record for specific date
     *
     * @param username
     * @return
     */
    @Override
    public List<MedicalRecord> select(String username, Date startTime, Date endTime) {
        List<MedicalRecord> list = new ArrayList<>();
        MedicalRecord medicalRecord = null;
        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "select * from medicalrecord where username = ? and date between ? and ?";
        try {

            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setDate(2, startTime);
            ps.setDate(3, endTime);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                medicalRecord = new MedicalRecord();
                medicalRecord.setSickness(rs.getString("sickness"));
                medicalRecord.setUsername(rs.getString("username"));
                medicalRecord.setDrug(rs.getString("drug"));
                medicalRecord.setDescription(rs.getString("description"));
                medicalRecord.setViewDate(rs.getString("date"));
                medicalRecord.setId(rs.getInt("id"));
                list.add(medicalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return list;
    }

    /**
     * update medical record information
     * id
     *
     * @param medicalRecord
     */
    @Override
    public void updateRecord(MedicalRecord medicalRecord) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "update medicalrecord set sickness=?,drug=?,description=? where id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, medicalRecord.getSickness());
            ps.setString(2, medicalRecord.getDrug());
            ps.setString(3, medicalRecord.getDescription());
            ps.setInt(4, medicalRecord.getId());
            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

}
