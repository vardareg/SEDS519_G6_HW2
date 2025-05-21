package com.iyte.seds519.util;

import com.iyte.seds519.model.TimeSlot;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HtmlHelper {

    private static final Map<String, DayOfWeek> TR_DAY_MAP = new HashMap<>();
    static {
        TR_DAY_MAP.put("Pazartesi", DayOfWeek.MONDAY);
        TR_DAY_MAP.put("Sali", DayOfWeek.TUESDAY);
        TR_DAY_MAP.put("Salı", DayOfWeek.TUESDAY);
        TR_DAY_MAP.put("Carsamba", DayOfWeek.WEDNESDAY);
        TR_DAY_MAP.put("Çarşamba", DayOfWeek.WEDNESDAY);
        TR_DAY_MAP.put("Persembe", DayOfWeek.THURSDAY);
        TR_DAY_MAP.put("Perşembe", DayOfWeek.THURSDAY);
        TR_DAY_MAP.put("Cuma", DayOfWeek.FRIDAY);
    }

    /**
     * Find the cell (
     * <td>) matching the given slot in every table of the document.
     * Returns the first match or null.
     */
    public static Element findCell(Document doc, TimeSlot slot) {
        Elements tables = doc.select("table");
        for (Element table : tables) {
            Element cell = findCellInTable(table, slot);
            if (cell != null)
                return cell;
        }
        return null;
    }

    private static Element findCellInTable(Element table, TimeSlot slot) {
        Elements rows = table.select("tr");
        if (rows.isEmpty())
            return null;

        // Header row
        Elements headers = rows.get(0).select("th");
        int dayIdx = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (parseTurkishDay(headers.get(i).text()) == slot.getDay()) {
                dayIdx = i;
                break;
            }
        }
        if (dayIdx == -1)
            return null;

        String expectedPrefix = slot.getTimeLabel();
        for (int r = 1; r < rows.size(); r++) {
            Element timeCell = rows.get(r).selectFirst("td");
            if (timeCell == null)
                continue;
            if (timeCell.text().startsWith(expectedPrefix)) {
                Elements cols = rows.get(r).select("td");
                if (dayIdx < cols.size()) {
                    return cols.get(dayIdx);
                }
            }
        }
        return null;
    }

    public static DayOfWeek parseTurkishDay(String dayTr) {
        return TR_DAY_MAP.getOrDefault(dayTr, null);
    }

    public static String toTurkishDay(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return "Pazartesi";
            case TUESDAY:
                return "Sali";
            case WEDNESDAY:
                return "Carsamba";
            case THURSDAY:
                return "Persembe";
            case FRIDAY:
                return "Cuma";
            default:
                return day.toString(); // fallback
        }
    }

}
