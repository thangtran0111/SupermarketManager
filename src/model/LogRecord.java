package model;

import java.time.LocalDateTime;

public class LogRecord {
    private int logID;
    private String logMessage;
    private String username;
    private LocalDateTime logTime;

    public LogRecord(String logMessage, String username) {
        this.logMessage = logMessage;
        this.username = username;
        this.logTime = LocalDateTime.now();
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }
}
