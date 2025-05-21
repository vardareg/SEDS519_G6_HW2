package com.iyte.seds519.decorator;

import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.schedule.Scheduler;
import com.iyte.seds519.util.HtmlHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ValidationDecorator extends SchedulerDecorator {

    public ValidationDecorator(Scheduler delegate) {
        super(delegate);
    }

    @Override
    public void placeCourse(Document doc, TimeSlot slot, String course, String lecture, String instructor) {
        Element cell = HtmlHelper.findCell(doc, slot);
            

            if (cell == null) {
                System.err.println("\033[31m" +
                        "WARNING: Validation failed – no cell for " + HtmlHelper.toTurkishDay(slot.getDay())
 +
                        "\033[0m");
                return;
            }

        
        super.placeCourse(doc, slot, course, lecture, instructor);
    }

}
