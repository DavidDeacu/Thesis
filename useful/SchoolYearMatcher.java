package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class SchoolYearMatcher implements Matcher {
	private static final Pattern INTEGER_PATTERN = Pattern.compile("[0-9]+");

	@Override
	public boolean matches(String input) {
		if(INTEGER_PATTERN.matcher(input).matches() && Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 12)
			return true;	
		return false;
	}

}
