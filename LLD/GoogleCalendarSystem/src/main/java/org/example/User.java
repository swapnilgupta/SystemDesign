package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {
	private String userId;
	private String userName;
	private String email;
	private Map<String, Calendar> calendars;

	public User(String userId, String userName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.calendars = new HashMap<>();
		// Creating a default calendar for each user.
		calendars.put("primary", new Calendar("primary", userId));
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public Map<String, Calendar> getCalendars() {
		return calendars;
	}

	public Calendar getPrimaryCalendar() {
		return calendars.get("primary");
	}

	@Override
	public String toString() {
		return "User{" +
			"userId='" + userId + '\'' +
			", userName='" + userName + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
