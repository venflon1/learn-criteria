package com.accenture.ra.criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.ra.criteria.dao.UserDao;
import com.accenture.ra.criteria.entity.User;

@SpringBootApplication
public class DemoJpaCriteriaApplication implements CommandLineRunner {

	@Autowired
	private UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaCriteriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.userDao.saveAll(
			User.builder()
				.firstname("Mario")
				.lastname("Rossi")
				.username("Marione1")
				.email("mario.rossi@gmail.com")
				.age(59)
				.build(),
			User.builder()
				.firstname("Mario")
				.lastname("Verdi")
				.username("Marione2")
				.email("mario.rossi@email.de")
				.age(39)
				.build(),
			User.builder()
				.firstname("Mario")
				.lastname("Rossi")
				.username("Centurione")
				.email("mario.gmail.com")
				.age(49)
				.build(),
			User.builder()
				.firstname("Mario")
				.lastname("Gialli")
				.username("Giallini")
				.email("mario.gialli@email.it")
				.age(29)
				.build(),
			User.builder()
				.firstname("Giuseppe")
				.lastname("Rossi")
				.username("Pinotto")
				.email("giuseppe.rossi@email.it")
				.age(19)
				.build(),
			User.builder()
				.firstname("Anita")
				.lastname("Viola")
				.username("Tanini")
				.email("anita.viola@gmail.com")
				.age(69)
				.build(),
			User.builder()
				.firstname("Vincenzino")
				.lastname("Rossi")
				.username("Rossini")
				.email("vincenzion.rossi@email.it")
				.age(79)
				.build()
		);
	}
}