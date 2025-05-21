package com.iyte.seds519.strategy;

import com.iyte.seds519.model.TimeSlot;
import org.jsoup.nodes.Document;

public interface TimeSelectionStrategy {
    boolean isFree(Document doc, TimeSlot slot);
}
