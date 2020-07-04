package test.ReservationTest;

import BLL.Client;
import DAL.acoount.User;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationImplTest {
    private Client client = new Client("localhost",7780);
    private User user = new User("yxm810", "123",1);
    @Test
    //appointment the ten to eleven time of the "test1" doctor
    //Appointment made
    void booking1() {
        Schedule schedule = new Schedule(user,"test1","1",1);
        String actual = client.sendData(schedule);
        String expect = "The appointment was successful";
        assertEquals(expect,actual);
    }
    @Test
        //Appointment at the same time will show "The time has been booked"
    void booking2() {
        Schedule schedule = new Schedule(user,"test1","1",1);
        String actual = client.sendData(schedule);
        String expect = "The time has been booked";
        assertEquals(expect,actual);
    }
    @Test
        //"Please specify appointment time" if no appointment time is specified
    void booking3() {
        Schedule schedule = new Schedule(user,"test1","1",0);
        String actual = client.sendData(schedule);
        String expect = "Please specify appointment time";
        assertEquals(expect,actual);
    }
    @Test
        //If the specified time exceeds the scope of the appointment,
        // "Select time is not within the scope of the appointment, please try again"
    void booking4() {
        Schedule schedule = new Schedule(user,"test1","1",9);
        String actual = client.sendData(schedule);
        String expect = "Select time is not within the scope of the appointment, please try again";
        assertEquals(expect,actual);
    }

    @Test
    //Specify the doctor's name and the work department can return the doctor's one-week appointment record
    void selectCalendar() {
        Doctor doctor = new Doctor("test2","plastic surgery");
        String actual = client.sendData(doctor);
        String expect = "01000,00100,00010,00001,10000,";
        assertEquals(expect, actual);
    }
}