package BLL.chat;

import java.io.*;
import java.net.*;

public class ChatClient {
    Socket socket = null;

    public static void main(String[] args) {
        new ChatClient("172.22.231.47",8080);
    }

    public ChatClient(String host,int port) {

        try {
            socket = new Socket(host, port);
//            socket = new Socket("localhost", port);

            new Cthread().start();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            String msg1;
            while ((msg1 = br.readLine()) != null) {

                System.out.println(msg1);
            }
        } catch (Exception e) {
        }
    }

    class Cthread extends Thread {

        public void run() {
            try {

                BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                String msg2;

                while (true) {

                    msg2 = re.readLine();
                    pw.println(msg2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}