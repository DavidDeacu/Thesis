package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class LetterIntegerMatcher implements Matcher {
	private static final Pattern LETTER_INTEGER_PATTERN = Pattern.compile("[a-zA-Z0-9]+");
	
	@Override
	public boolean matches(String input) {
		return LETTER_INTEGER_PATTERN.matcher(input).matches();
	}
	

}
