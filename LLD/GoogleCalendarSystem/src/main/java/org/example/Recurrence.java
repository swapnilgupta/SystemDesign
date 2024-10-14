package org.example;

public class Recurrence {
	private String recurrenceType;
	private int interval;  // In days, weeks, months depending on type

	public Recurrence(String recurrenceType, int interval) {
		this.recurrenceType = recurrenceType;
		this.interval = interval;
	}

	public String getRecurrenceType() {
		return recurrenceType;
	}

	public int getInterval() {
		return interval;
	}

	@Override
	public String toString() {
		return "Recurrence{" +
			"recurrenceType='" + recurrenceType + '\'' +
			", interval=" + interval +
			'}';
	}
}
