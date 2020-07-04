package DAL.reservation.dao;

import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import java.util.ArrayList;

public interface IReservationDao {
    void updateSchedule(Schedule schedule);
    Schedule selectSchedule(String id,String week);
    Doctor selectDoctor (String name, String department);
    ArrayList<Schedule> selectAllSchedule(String id);
    ArrayList<Doctor> selectDoctorByDepartment(String department);
    void insertCalendar(String id);
    void insertDoctor(String id, String name, String department);
    Doctor selectDoctorById(String id);

}
