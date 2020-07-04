package BLL;

import BLL.chat.ChatClient;
import DAL.acoount.User;
import DAL.create.CreateTableImpl;
import DAL.create.ICreateTableImpl;
import DAL.create.dao.impl.CreateTableDao;
import DAL.reservation.Doctor;
import DAL.reservation.Schedule;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;


public class Client {
    private static Socket socket;
    private int port;
    private String host;

    /**
     * @param host
     * @param port
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            System.out.println("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param data
     */
    public String sendData(Object data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            InputStream in = socket.getInputStream();

            byte[] bytes = new byte[1024];
            int length = -1;
            out.writeObject(data);
            length = in.read(bytes);
            return new String(bytes, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "please try again";
    }

    public static void main(String[] args) throws ParseException {
        Client send = new Client("localhost", 7780);
        System.out.println(send.sendData(new Doctor("test7", "mayifan","plastic surgery",2)));

    }
}
