package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class IntegerMatcher implements Matcher {
	private static final Pattern INTEGER_PATTERN = Pattern.compile("[0-9]+");

	@Override
	public boolean matches(String input) {
		return INTEGER_PATTERN.matcher(input).matches();
	}

}
