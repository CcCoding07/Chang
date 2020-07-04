package DAL.reservation.dao.impl;

import DAL.reservation.Doctor;
import DAL.reservation.Schedule;
import DAL.reservation.dao.IReservationDao;
import DAL.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * reservation的持久层方法
 */
public class ReservationDaoImpl implements IReservationDao {
    /**
     * 该方法用于更新预约
     *
     * @param schedule
     */
    @Override
    public void updateSchedule(Schedule schedule) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "update calendar set ten=?,eleven=?, thirteen=?,fourteen=?,fifteen=? where id=? and week=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, schedule.getTen());
            ps.setString(2, schedule.getEleven());
            ps.setString(3, schedule.getThirteen());
            ps.setString(4, schedule.getFourteen());
            ps.setString(5, schedule.getFifteen());
            ps.setString(6, schedule.getId());
            ps.setString(7, schedule.getWeek());
            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    /**
     * 该方法用于查询预约情况
     *
     * @param id
     * @param week
     * @return
     */
    @Override
    public Schedule selectSchedule(String id, String week) {
        Schedule schedule = null;
        Connection conn = null;
        PreparedStatement ps = null;

        // 结果集对象 用于封装数据库的查询结果
        ResultSet rs = null;
        String sql = "select id,week,ten,eleven,thirteen,fourteen,fifteen from calendar where id=? and week=?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, week);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                schedule = new Schedule();
                schedule.setId(rs.getString("id"));
                schedule.setWeek(rs.getString("week"));
                schedule.setTen(rs.getString("ten"));
                schedule.setEleven(rs.getString("eleven"));
                schedule.setThirteen(rs.getString("thirteen"));
                schedule.setFourteen(rs.getString("fourteen"));
                schedule.setFifteen(rs.getString("fifteen"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return schedule;
    }

    /**
     * 该方法用于返回医生的id
     *
     * @param name
     * @param department
     * @return
     */
    @Override
    public Doctor selectDoctor(String name, String department) {
        Doctor doctor = null;
        Connection conn = null;
        PreparedStatement ps = null;

        // 结果集对象 用于封装数据库的查询结果
        ResultSet rs = null;
        String sql = "select * from doctor where name=? and department=?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, department);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                doctor = new Doctor();
                doctor.setId(rs.getString("id"));
                doctor.setName(rs.getString("name"));
                doctor.setDepartment(rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return doctor;
    }

    @Override
    public ArrayList<Doctor> selectDoctorByDepartment(String department) {
        ArrayList<Doctor> list = new ArrayList<>();
        Doctor doctor = null;
        Connection conn = null;
        PreparedStatement ps = null;

        // 结果集对象 用于封装数据库的查询结果
        ResultSet rs = null;
        String sql = "select name from doctor where department=?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, department);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                doctor = new Doctor();
                doctor.setName(rs.getString("name"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return list;
    }

    /**
     * 该方法用于返回该医生一周内预约情况
     *
     * @param id
     * @return
     */
    @Override
    public ArrayList<Schedule> selectAllSchedule(String id) {
        ArrayList<Schedule> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";

        try {
            conn = DBUtil.getConn();
            sql = "SELECT * FROM calendar where id = ? ORDER BY week";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getString("id"));
                schedule.setWeek(rs.getString("week"));
                schedule.setTen(rs.getString("ten"));
                schedule.setEleven(rs.getString("eleven"));
                schedule.setThirteen(rs.getString("thirteen"));
                schedule.setFourteen(rs.getString("fourteen"));
                schedule.setFifteen(rs.getString("fifteen"));
                schedule.setTime((rs.getInt("time")));
                list.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return list;
    }

    /**
     * 当新建医生账号时 插入doctor表 及calendar表
     */
    public void insertDoctor(String id, String name, String department) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "insert into doctor(id,name,department) values(?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, department);

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    /**
     * 当新建医生账号时 新建calendar表
     */
    public void insertCalendar(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            String sql = "insert into calendar(id,week) values(?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2,"1");
            ps.addBatch();

            ps.setString(1, id);
            ps.setString(2,"2");
            ps.addBatch();

            ps.setString(1, id);
            ps.setString(2,"3");
            ps.addBatch();

            ps.setString(1, id);
            ps.setString(2,"4");
            ps.addBatch();

            ps.setString(1, id);
            ps.setString(2,"5");
            ps.addBatch();

            int[] counts = ps.executeBatch(); //执行Batch中的全部语句
            conn.commit();                      //提交到数据库
            for (int i : counts) {
                if (i == 0) {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Doctor selectDoctorById(String id){
        Doctor doctor = null;
        Connection conn = null;
        PreparedStatement ps = null;

        // 结果集对象 用于封装数据库的查询结果
        ResultSet rs = null;
        String sql = "select * from doctor where id=?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                doctor = new Doctor();
                doctor.setName(rs.getString("name"));
                doctor.setId(rs.getString("id"));
                doctor.setDepartment(rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return doctor;
    }
}
