package com.takehome.activity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private TestCaseReader testCaseReader;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init(){
		String cntinue="y";
		while(cntinue.equalsIgnoreCase("y")){
			try {
				testCaseReader.readInput();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Do you want to continue?");
			Scanner scan = new Scanner(System.in);
			cntinue = scan.next();
			System.out.println(cntinue);
		}
		System.exit(0);
	}


}
