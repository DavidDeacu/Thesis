package com.school.platform.useful;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailMatcher implements Matcher {
	public static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean matches(String input) {
		return EMAIL_PATTERN.matcher(input).matches();
	}

}
