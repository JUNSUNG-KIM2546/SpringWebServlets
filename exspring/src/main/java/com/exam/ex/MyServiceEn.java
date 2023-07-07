package com.exam.ex;

import org.springframework.stereotype.Component;

@Component("mse")
public class MyServiceEn implements MyService {

	@Override
	public String getMessage() {
		return "Hello";
	}

}
