package com.maskvote;

import com.maskvote.Test.ReadBase;
import com.maskvote.Test.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        BigInteger[] arr = ReadBase.readGroupBase();
//        RegisterServer.server(arr);
        Test s = new Test();
        s.Server(arr);
    }

}
