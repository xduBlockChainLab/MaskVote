package com.maskvote.maskvotecounter.SocketNet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockVoter {
    public static boolean flagOfRegister = false;
    ServerSocket ss;

    public BlockVoter() throws IOException {
        ss = new ServerSocket(60000);
    }

    public void start() throws IOException {
        //准备输入输出
        //启动新的线程

        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);


        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        PrintWriter pw = new PrintWriter(osw,true);

        //准备线程并且启动
        SocketHandler handler = new SocketHandler(pw,br);
        Thread t = new Thread(handler);
        t.start();
    }

    class SocketHandler implements Runnable{
        PrintWriter pw;
        BufferedReader br;

        public  SocketHandler(PrintWriter pw,BufferedReader br){
            this.pw = pw;
            this.br = br;
        }


        @Override
        public void run(){
            try {
                //如果客户端发送过来东西，我们就返回flag
                System.out.println("服务器线程运行");
                while (true) {
                    if (br.readLine() != null && br.readLine() != "") {
                        pw.println(flagOfRegister);
                    }
                    if (flagOfRegister) break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
