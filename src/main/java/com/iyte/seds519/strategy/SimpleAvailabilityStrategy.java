package com.iyte.seds519.strategy;

import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.util.HtmlHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SimpleAvailabilityStrategy implements TimeSelectionStrategy {

    @Override
    public boolean isFree(Document doc, TimeSlot slot) {
        Element cell = HtmlHelper.findCell(doc, slot);
        return cell != null && cell.text().isBlank();
    }
}
