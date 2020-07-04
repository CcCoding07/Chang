package DAL.reservation;

import DAL.acoount.User;
import DAL.reservation.dao.IReservationDao;
import DAL.reservation.dao.impl.ReservationDaoImpl;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ReservationImpl implements IReservation {
    private Schedule schedule = null;
    private IReservationDao reservationDaoImpl = new ReservationDaoImpl();

    /**
     * time 表示需要预约哪个时间段 1表示ten，2表示eleven，3表示thirteen，4表示fourteen，5表示fifteen
     * 0：修改成功
     * 1：该时间已经有预约
     * 2：没有指定修改时间 预约失败
     * 3：数据库没有更新 预约失败
     * 4: 选择时间不在范围内 预约失败
     *
     * @param id
     * @param week
     * @param time
     * @return
     */
    @Override
    public synchronized int booking(User user, String id, String week, int time) {

        //1 获取预约前schedule对象

//        ReservationDao reservationDao = new ReservationDao();
        Schedule booking = reservationDaoImpl.selectSchedule(id, week);
//        System.out.println(booking);
        List<String> string = Schedule.getSchedule(booking);
//        System.out.println("修改前"+string);

        //2 预约（set修改对象状态）

        switch (time) {
            case 0:
                //表示没有指定预约时间，预约失败
                return 2;
            case 1:
                if (booking.getTen().equals("0")) {
                    booking.setTen(user.getUsername());
                    break;
                } else {
                    return 1;//预约失败
                }
            case 2:
                if (booking.getEleven().equals("0")) {
                    booking.setEleven(user.getUsername());
                    break;//预约失败
                } else {
                    return 1;//预约失败
                }
            case 3:
                if (booking.getThirteen().equals("0")) {
                    booking.setThirteen(user.getUsername());
                    break;
                } else {
                    return 1;
                }
            case 4:
                if (booking.getFourteen().equals("0")) {
                    booking.setFourteen(user.getUsername());
                    break;
                } else {
                    return 1;
                }
            case 5:
                if (booking.getFifteen().equals("0")) {
                    booking.setFifteen(user.getUsername());
                    break;
                } else {
                    return 1;
                }
            default:
                return 4; //不再范围内 预约失败
        }
        //3 更新对象值

        reservationDaoImpl.updateSchedule(booking);

        //4 检查是否更新成功

        List<String> test = Schedule.getSchedule(reservationDaoImpl.selectSchedule(id, week));
//        System.out.println(reservationDao.selectSchedule(id, week));
//        System.out.println("修改后"+test);
        if (string.equals(test)) {
            return 3;// 该时间段被其他人预约，预约失败
        } else {
            return 0; //预约成功
        }
    }

    /**
     * 该方法用于查询医生一周内预约信息
     *
     * @param name
     * @param department
     * @return
     */
    @Override
    public List<Schedule> selectCalendar(String name, String department) {
        //1 根据提供的name 和 department 返回该医生的id信息
        // 该信息根据由复选框提供，一定存在
        Doctor doctor = reservationDaoImpl.selectDoctor(name, department);
        //2 根据医生id 返回该医生一周的预约状况
        // 该信息一定存在
        List<Schedule> list = new ArrayList<>();
        list = reservationDaoImpl.selectAllSchedule(doctor.getId());

        return list;
    }

    @Override
    public String returnName(String department) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors = reservationDaoImpl.selectDoctorByDepartment(department);
        StringBuffer stringBuffer = new StringBuffer();
        for (Doctor e : doctors) {
            stringBuffer.append(e.getName());
            stringBuffer.append(",");
        }
        return stringBuffer.toString();
    }

    /**
     * 这个方法用于医生新建账号时的插入表操作
     */
    public void insertTable(Doctor doctor) {
        reservationDaoImpl.insertDoctor(doctor.getId(), doctor.getName(), doctor.getDepartment());
        reservationDaoImpl.insertCalendar(doctor.getId());
    }

    /**
     * 用于登录时查找doctor表
     */
    public String returnDoctor(String id) {
        Doctor doctor = reservationDaoImpl.selectDoctorById(id);
        return doctor.toString();

    }
}
