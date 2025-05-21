package com.iyte.seds519.schedule;

import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.strategy.TimeSelectionStrategy;
import com.iyte.seds519.util.HtmlHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BasicScheduler implements Scheduler {

    private final TimeSelectionStrategy strategy;

    public BasicScheduler(TimeSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void placeCourse(Document doc, TimeSlot slot, String course, String lecture, String instructor) {
        Element cell = HtmlHelper.findCell(doc, slot);
        if (strategy.isFree(doc, slot)) {
            cell.append("<span style=\"color:red\">" + course + " " + lecture + " (" + instructor + ")</span><br/>");
        } else {
            // Get current occupant's info (plain text in cell)
            String occupant = cell.text();
            System.err.println("\033[31m" +
                    "WARNING: Time slot already occupied for " + HtmlHelper.toTurkishDay(slot.getDay())
                    +
                    " by " + occupant +
                    " : cannot schedule " + course + " " + lecture + " (" + instructor + ")" +
                    "\033[0m");

            return;
        }

    }
}
