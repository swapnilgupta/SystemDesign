package org.example;

import java.util.HashMap;
import java.util.Map;

public class Calendar {
	private String calendarId;
	private String ownerId;
	private Map<String, Event> events;
	private Map<String, String> permissions; // userId -> role (editor/viewer)

	public Calendar(String calendarId, String ownerId) {
		this.calendarId = calendarId;
		this.ownerId = ownerId;
		this.events = new HashMap<>();
		this.permissions = new HashMap<>();
	}

	public String getCalendarId() {
		return calendarId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public Map<String, Event> getEvents() {
		return events;
	}

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void addEvent(Event event) {
		events.put(event.getEventId(), event);
	}

	public void shareCalendar(String userId, String role) {
		permissions.put(userId, role);
	}

	public void showEvents() {
		for (Event event : events.values()) {
			System.out.println(event);
		}
	}

	@Override
	public String toString() {
		return "Calendar{" +
			"calendarId='" + calendarId + '\'' +
			", ownerId='" + ownerId + '\'' +
			", events=" + events +
			'}';
	}
}
