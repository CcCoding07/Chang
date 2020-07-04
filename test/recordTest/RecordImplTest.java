package test.recordTest;

import BLL.Client;
import DAL.record.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class RecordImplTest {
    private Client client = new Client("172.22.231.47", 7780);

    @Test
        //Three pieces of medical record information exist in the database
        //The first step is to retrieve the medical records according to the user name.
    void viewRecord1() {
        MedicalRecord medicalRecord = new MedicalRecord("test1", 1);
        String actual = client.sendData(medicalRecord);
        String expect = "111,111,111,test1&222,222,222,test1&";
        assertEquals(expect, actual);
    }

    @Test
        //In the second part, we take out the medical records according to the date range.
    void viewRecord2() throws ParseException {
        MedicalRecord medicalRecord = new MedicalRecord("test1", "2020-03-01", "2020-03-03", 2);
        String actual = client.sendData(medicalRecord);
        String expect = "222,222,222,test1&";
        assertEquals(expect, actual);
    }

    @Test
        //The third part clarifies the operation of inserting new medical records with username and date
    void newRecord3() throws ParseException {
        MedicalRecord medicalRecord = new MedicalRecord("test3", "2019-08-19", 4);
        String actual = client.sendData(medicalRecord);
        String expect = "New medical record succeeded";
        assertEquals(expect, actual);
    }

    @Test
        //The last step, specifying the id can implement the operation of modifying the medical record
    void updateRecord4() throws ParseException {
        MedicalRecord medicalRecord = new MedicalRecord("no Sickness","no drug","no description",3,3);
        String actual = client.sendData(medicalRecord);
        String expect = "Successfully modified";
        assertEquals(expect, actual);
    }
}