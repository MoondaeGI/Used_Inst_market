package com.example.Used_Inst_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@EnableJpaAuditing
@SpringBootApplication
public class UsedInstMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedInstMarketApplication.class, args);

		System.out.println("------------------------");
		System.out.println("중고 악기 판매 거래 사이트");
	}

}
