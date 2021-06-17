package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class FirstNameMatcher implements Matcher {
	private static final Pattern FIRST_NAME_PATTERN = Pattern.compile("[a-zA-Z-]+");
	
	@Override
	public boolean matches(String input) {
		return FIRST_NAME_PATTERN.matcher(input).matches();
	}

}
