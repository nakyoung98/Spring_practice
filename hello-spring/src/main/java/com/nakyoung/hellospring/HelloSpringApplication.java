package com.nakyoung.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		//springbootApplicaftion 실행
		//tomcat에서 띄움

		/** gradle 통해서 실행시 느려지는 경우가 있으므로
			settings에서 gradle 검색후, 실행방법을 gradle에서 intelliJ로 변경해줍시다
		 **/
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
