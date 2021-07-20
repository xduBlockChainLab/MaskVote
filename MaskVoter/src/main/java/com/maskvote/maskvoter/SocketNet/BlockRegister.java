package com.maskvote.maskvoter.SocketNet;

import com.maskvote.maskvoter.Controller.IndexController;

import java.io.*;
import java.net.Socket;

public class BlockRegister {
    Socket socket;

    public BlockRegister() throws IOException {
        socket = new Socket("localhost",60000);
    }

    public void start() throws IOException {
        //准备输入输出，然后启动线程进行通信

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        PrintWriter pw = new PrintWriter(osw,true);

        //开启线程
        SocketHandler handler = new SocketHandler(br,pw);
        Thread t = new Thread(handler);
        t.start();


    }

    class SocketHandler implements Runnable{
        BufferedReader br;
        PrintWriter pw;

        SocketHandler(BufferedReader br, PrintWriter pw){
            this.br = br;
            this.pw = pw;
        }

        public void run(){
            System.out.println("进程线程运行");
            try {
                while (true) {
                    pw.println("quest for flag");

                    if (br.readLine().equals("true")) {
                        IndexController.flagOfRegister = true;
                        break;
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
