package com.iyte.seds519.schedule;

import com.iyte.seds519.model.TimeSlot;
import org.jsoup.nodes.Document;

/**
 * Schedules a single course into the HTML document.
 */
public interface Scheduler {
    void placeCourse(Document doc, TimeSlot slot, String course, String lecture, String instructor);

}
