package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMatcher implements Matcher {
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9+]+");
	
	@Override
	public boolean matches(String input) {
		return PHONE_NUMBER_PATTERN.matcher(input).matches();
	}

}
