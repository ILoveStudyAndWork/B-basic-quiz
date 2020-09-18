package com.thoughtworks.gtb.bbasicquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// GTB: - 在 CLI 通过 gradle 构建时会有 warning
@SpringBootApplication
public class BBasicQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(BBasicQuizApplication.class, args);
	}

}
