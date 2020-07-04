package DAL.reservation;

import DAL.acoount.User;

import java.util.List;

public interface IReservation {
     int booking(User user, String id, String week, int time);
     List<Schedule> selectCalendar (String name ,String department);
     String returnName(String department);
     void insertTable(Doctor doctor);
     String returnDoctor(String id);
}
