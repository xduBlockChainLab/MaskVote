package com.maskvote.maskvotecounter;

import com.maskvote.maskvotecounter.SocketNet.BlockVoter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MaskvotecounterApplication {

    public static void main(String[] args) throws IOException {
        BlockVoter pro = new BlockVoter();
        pro.start();
        SpringApplication.run(MaskvotecounterApplication.class, args);
    }

}
