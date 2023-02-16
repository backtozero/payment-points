package com.anymindgroup;

import com.anymindgroup.service.PaymentServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new PaymentServiceGrpc.PaymentServiceImplBase() {
                    // your service implementation here
                })
                .build();
        server.start();
        server.awaitTermination();

        SpringApplication.run(Main.class, args);
    }
}