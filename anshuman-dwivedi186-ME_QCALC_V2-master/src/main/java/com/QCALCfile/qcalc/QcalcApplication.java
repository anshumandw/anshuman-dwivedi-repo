package com.QCALCfile.qcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QcalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(QcalcApplication.class, args);

		// StandardCalculator calc = new StandardCalculator();
		// // calc.add(Double.MAX_VALUE, 1.0);
		// calc.add(Double.MAX_VALUE, Double.MAX_VALUE);
		// calc.printResult();
	}

}
