package org.example;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
	private String eventId;
	private String eventName;
	private String description;
	private String location;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private List<User> attendees;
	private Recurrence recurrence;

	public Event(String eventId, String eventName, String description, String location,
		LocalDateTime startTime, LocalDateTime endTime, List<User> attendees, Recurrence recurrence) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.attendees = attendees;
		this.recurrence = recurrence;
	}

	public String getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	@Override
	public String toString() {
		return "Event{" +
			"eventId='" + eventId + '\'' +
			", eventName='" + eventName + '\'' +
			", description='" + description + '\'' +
			", location='" + location + '\'' +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", attendees=" + attendees +
			", recurrence=" + recurrence +
			'}';
	}
}