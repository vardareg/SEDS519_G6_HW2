package com.iyte.seds519.model;

import java.time.DayOfWeek;
import java.util.Objects;

/**
 * Represents a day/time label in the schedule.
 *
 * The label should match the 'HH:mm' start time as given in preference files, e.g. "09:45".
 */
public class TimeSlot {
    private final DayOfWeek day;
    private final String timeLabel;

    public TimeSlot(DayOfWeek day, String timeLabel) {
        this.day = day;
        this.timeLabel = timeLabel;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot that = (TimeSlot) o;
        return day == that.day && Objects.equals(timeLabel, that.timeLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, timeLabel);
    }

    @Override
    public String toString() {
        return day + " " + timeLabel;
    }
}
