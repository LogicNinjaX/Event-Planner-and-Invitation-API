package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(DemoApplication.class, args);
	}
}
