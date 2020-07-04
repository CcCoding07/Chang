package test.infoTest;

import BLL.Client;
import DAL.information.IInfo;
import DAL.information.InfoImpl;
import DAL.information.Person_info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoImplTest {
    private Client client = new Client("172.22.231.47", 7780);
    private IInfo info = new InfoImpl();
    @Test
    //First check an existing record
    void infoTest1(){
        Person_info person_info = new Person_info("1",1);
        String actual = client.sendData(person_info);
        String expect = "YIFAN,MA,male,O,NAN,07529208661,m672832006@gmail.com";
        assertEquals(expect, actual);

    }
    @Test
        //Then update another record
    void infoTest2(){
        Person_info person_info = new Person_info("yair","ma","yair","male","AB","Alcohol allergy","18248785896","672832006@qq.com",2);
        String actual = client.sendData(person_info);
        String expect = "update completed";
        assertEquals(expect, actual);

    }
}