package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class LetterMatcher implements Matcher {
	private static final Pattern LETTER_PATTERN = Pattern.compile("[a-zA-Z]+");
	
	@Override
	public boolean matches(String input) {
		return LETTER_PATTERN.matcher(input).matches();
	}

}
