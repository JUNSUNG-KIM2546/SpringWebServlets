package com.exam.ex;

import org.springframework.stereotype.Component;

//@Component("msk")
public class MyServiceKo implements MyService {

	@Override
	public String getMessage() {
		return "안녕";
	}

}
