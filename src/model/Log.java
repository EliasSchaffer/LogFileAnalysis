package model;

import java.time.LocalDateTime;

public class Log {
    private LocalDateTime date;
    private String level;
    private String message;

    public Log(LocalDateTime date, String level, String message) {
        this.date = date;
        this.message = message;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
