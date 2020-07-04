package BLL;

import BLL.impl.HandlerImpl;


import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket client;
    private IHandler handle = new HandlerImpl();

    public ServerThread(Socket client) {
        super();
        this.client = client;
    }

    /**
     * 当抛出EOFException时表示客户端连接已经关闭
     */
    @Override
    public void run() {
        InputStream inputStream;
        OutputStream outputStream;
        while (true) {
            try {
                inputStream = client.getInputStream();
                outputStream = client.getOutputStream();
                handle.handle(inputStream, outputStream);
                System.out.println("进入线程");
            } catch (EOFException e) {
                System.out.println("完成通信");
                break;
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

}

