package com.iyte.seds519.decorator;

import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.schedule.Scheduler;
import org.jsoup.nodes.Document;

/**
 * Base decorator that delegates to another Scheduler.
 */
public abstract class SchedulerDecorator implements Scheduler {

    protected final Scheduler delegate;

    protected SchedulerDecorator(Scheduler delegate) {
        this.delegate = delegate;
    }

    @Override
    public void placeCourse(Document doc, TimeSlot slot, String course, String lecture, String instructor) {
        delegate.placeCourse(doc, slot, course, lecture, instructor);
    }

}
