package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		// Create Users
		User user1 = new User("u1", "John Doe", "john.doe@gmail.com");
		User user2 = new User("u2", "Jane Smith", "jane.smith@gmail.com");
		User user3 = new User("u3", "Alice", "alice@gmail.com");

		// Create Recurrence for Weekly Event
		Recurrence weeklyRecurrence = new Recurrence("weekly", 1);

		// Create an Event
		Event event1 = new Event("e1", "Team Meeting", "Discuss project updates",
			"Conference Room 1", LocalDateTime.of(2024, 10, 2, 10, 0),
			LocalDateTime.of(2024, 10, 2, 11, 0),
			Arrays.asList(user1, user2), weeklyRecurrence);

		// Add Event to User's Calendar
		Calendar user1Calendar = user1.getPrimaryCalendar();
		user1Calendar.addEvent(event1);

		// Share Calendar with another user
		user1Calendar.shareCalendar(user2.getUserId(), "viewer");

		// Show Events in Calendar
		System.out.println("Events in John Doe's calendar:");
		user1Calendar.showEvents();

		// Create another Event
		Event event2 = new Event("e2", "Project Launch", "Launch new project",
			"Office Lobby", LocalDateTime.of(2024, 10, 3, 14, 0),
			LocalDateTime.of(2024, 10, 3, 16, 0),
			Arrays.asList(user1, user3), null);

		user1Calendar.addEvent(event2);

		// Show updated events
		System.out.println("\nUpdated events in John Doe's calendar:");
		user1Calendar.showEvents();

		// Demonstrating Sharing and Permissions
		System.out.println("\nPermissions for John Doe's calendar:");
		System.out.println(user1Calendar.getPermissions());

		// Propose time (hypothetical implementation) would go here.
		// For now, just outputting event details.

		System.out.println("\nTesting complete. Calendar system is working with basic functionality.");
	}
}