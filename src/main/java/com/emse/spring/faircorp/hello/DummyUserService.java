package com.emse.spring.faircorp.hello;

import java.util.List;
import java.util.Collections;
import org.springframework.stereotype.Service;
import java.util.ArrayList; // import the ArrayList class



@Service
public class DummyUserService implements UserService {

	List<String> strings = new ArrayList<String>();

	public void setStrings(List<String> strings) {
		List<String> string = new ArrayList<String>();
		string.add("Elodie");
		string.add("Charles");
		this.strings = string;
		System.out.println(strings);

	}

	ConsoleGreetingService cgs = new ConsoleGreetingService();

	@Override
	public void greetAll(){

		setStrings(strings);

		for(int i=0; i<strings.size() ; i++){
			cgs.greet(strings.get(i));
		}
	}
}