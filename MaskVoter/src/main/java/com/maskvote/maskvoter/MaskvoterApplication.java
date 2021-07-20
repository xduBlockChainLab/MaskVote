package com.maskvote.maskvoter;

import com.maskvote.maskvoter.SocketNet.BlockRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MaskvoterApplication {

    public static void main(String[] args) throws IOException {
        BlockRegister pro = new BlockRegister();
        pro.start();
        SpringApplication.run(MaskvoterApplication.class, args);
    }

}
