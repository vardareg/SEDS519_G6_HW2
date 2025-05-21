package com.iyte.seds519.decorator;

import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.schedule.Scheduler;
import com.iyte.seds519.util.HtmlHelper;

import org.jsoup.nodes.Document;

public class LoggingDecorator extends SchedulerDecorator {

    public LoggingDecorator(Scheduler delegate) {
        super(delegate);
    }

    @Override
    public void placeCourse(Document doc, TimeSlot slot, String course, String lecture, String instructor) {
        System.out.println();
        System.out.println("\033[33m--------------------------------------------------\033[0m");
        System.out.println("Placing " + course + " " + lecture + " (" + instructor + ") at "
                + HtmlHelper.toTurkishDay(slot.getDay()));

        super.placeCourse(doc, slot, course, lecture, instructor);
    }

}
