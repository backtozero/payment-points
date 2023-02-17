package com.anymindgroup.configuration;

import com.anymindgroup.service.PaymentMethodServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;

@Configuration
@Slf4j
public class MainConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        PaymentMethodServiceGrpcImpl bean = event.getApplicationContext().getBean(PaymentMethodServiceGrpcImpl.class);
        startGrpcServer(bean);
    }

    private void startGrpcServer(PaymentMethodServiceGrpcImpl paymentMethodServiceGrpcImpl) {
        log.info("Starting gRPC server");
        new Thread(() -> {
            try {
                Server server = ServerBuilder.forPort(9090)
                        .addService(paymentMethodServiceGrpcImpl)
                        .build();
                server.start();
                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
