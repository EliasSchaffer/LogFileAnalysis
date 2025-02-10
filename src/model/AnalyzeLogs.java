package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeLogs {

    private String filePath;
    private Map<String, Integer> summary;

    public AnalyzeLogs(String filePath) {
        this.filePath = filePath;
        summary = new HashMap<>();
    }

    public ArrayList<Log> analyzeLogs() {
        File file = new File(filePath);
        ArrayList<String> stringLogs = new ArrayList<>();
        ArrayList<Log> logs = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                reader.lines().forEach(stringLogs::add);


            } catch (Exception e) {
                e.printStackTrace(); // Log any errors
            }
        }

        Pattern pattern = Pattern.compile("\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})] \\[(\\w+)] (.+)");


        for (String stringLog : stringLogs) {
            if (!stringLog.isEmpty()) {
                Matcher matcher = pattern.matcher(stringLog);
                if (matcher.matches()) {
                    Log parsedLog = new Log(parseDate(matcher.group(1)), matcher.group(2), matcher.group(3));
                    logs.add(parsedLog);
                } else {
                    logs.add(new Log(LocalDateTime.of(1, 1, 1, 0, 0, 0), stringLog, "Invalid Format"));

                }
            }
        }

        for (Log log : logs) {
            summary.put(log.getLevel(), summary.getOrDefault(log.getLevel(), 0) + 1);
        }
        return logs;
    }

    private LocalDateTime parseDate(String line) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})");
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            return LocalDateTime.of(
                    Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6))
            );
        } else {
            return LocalDateTime.of(1, 1, 1, 0, 0, 0);
        }
    }

    public Map<String, Integer> getSummary() {
        return summary;
    }
}
