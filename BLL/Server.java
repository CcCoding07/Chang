package BLL;

import BLL.chat.ChatServer;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static {
        /**
         * 建立ssh连接
         */
        String url = "mod-msc-sw1.cs.bham.ac.uk"; //远程PostgreSQL服务器
        String sshurl = "tinky-winky.cs.bham.ac.uk"; //SSH服务器
        String sshuser = "yxm810"; //SSH连接用户名
        String sshpassword = "672832006Myf"; //SSH连接密码
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(sshuser, sshurl, 22);
            session.setPassword(sshpassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息

            int assinged_port = session.setPortForwardingL(5432, url, 5432);//端口映射 转发  数据库服务器地址url

            System.out.println("localhost:" + assinged_port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该构造函数在一个死循环中，每次接收到客户端请求都会新建一个线程 专门处理该客户端请求
     */
    public Server(int port) {
        System.out.println("服务器启动");
        ExecutorService tasks = Executors.newFixedThreadPool(100);

        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("接收到一个客户端连接请求");
                tasks.execute(new ServerThread( client));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Server(7780);
    }
}
