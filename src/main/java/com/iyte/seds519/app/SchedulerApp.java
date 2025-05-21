package com.iyte.seds519.app;

import com.iyte.seds519.factory.ScheduleFactory;
import com.iyte.seds519.factory.ScheduleType;
import com.iyte.seds519.model.TimeSlot;
import com.iyte.seds519.schedule.Scheduler;
import com.iyte.seds519.strategy.SimpleAvailabilityStrategy;
import com.iyte.seds519.util.HtmlHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Coordinates reading inputs, scheduling, and writing output.
 */
public class SchedulerApp {

    private static final Logger LOG = Logger.getLogger(SchedulerApp.class.getName());

    private static final Path INPUT_DIR = Paths.get("inputs");
    private static final Path OUTPUT_DIR = Paths.get("output");
    private static final String BASE_HTML = "output.html";

    public void run() {
        System.out.println("\033[33m--------------------------------------------------\033[0m");

        try {
            if (Files.notExists(OUTPUT_DIR)) {
                Files.createDirectories(OUTPUT_DIR);
            }
            Path htmlPath = INPUT_DIR.resolve(BASE_HTML);
            Document doc = Jsoup.parse(htmlPath.toFile(), "UTF-8");

            Scheduler scheduler = ScheduleFactory.create(ScheduleType.UNDERGRADUATE,
                    new SimpleAvailabilityStrategy());

            // Read all preference files
            try (DirectoryStream<Path> prefFiles = Files.newDirectoryStream(INPUT_DIR, "*.txt")) {
                for (Path file : prefFiles) {
                    processPreferenceFile(file, scheduler, doc);
                }
            }

            // Write updated HTML
            Path outHtml = OUTPUT_DIR.resolve(BASE_HTML);
            Files.writeString(outHtml, doc.outerHtml());
            System.out.println();
            System.out.println("\033[33m--------------------------------------------------\033[0m");
            LOG.info("Updated schedule written to " + outHtml.toAbsolutePath());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error running scheduler", e);
        }
    }

    private void processPreferenceFile(Path prefPath, Scheduler scheduler, Document doc) throws IOException {
        List<String> lines = Files.readAllLines(prefPath);
        for (String line : lines) {

            String[] parts = line.split(";");
            if (parts.length != 4) {
                System.err.println("\033[31m" +
                        "WARNING: Invalid line in " + prefPath + ": " + line +
                        "\033[0m");
                continue;
            }

            String course = parts[0].trim();
            String dayStr = parts[1].trim();
            String timeStr = parts[2].trim();
            String lecture = parts[3].trim();
            String instructor = prefPath.getFileName().toString().replaceFirst("\\.txt$", "");

            DayOfWeek day = HtmlHelper.parseTurkishDay(dayStr);
            if (day == null) {

                System.err.println("\033[31m" +
                        "WARNING: Invalid day '" + dayStr + "' in " + prefPath +
                        "\033[0m");

                continue;
            }
            TimeSlot slot = new TimeSlot(day, timeStr);
            scheduler.placeCourse(doc, slot, course, lecture, instructor);
        }
    }
}
