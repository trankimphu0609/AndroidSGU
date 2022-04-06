package com.trankimphu.lab04_uiclasses;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToDoItem {

    public enum Priority {
        LOW, MED, HIGH
    };

    public enum Status {
        NOTDONE, DONE
    };

    public static final String ITEM_SEP = System.getProperty("line.separator");
    public final static String TITLE = "title";
    public final static String PRIORITY = "priority";
    public final static String STATUS = "status";
    public final static String DATE = "date";

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.US);

    private String title = new String();
    private Priority priority = Priority.LOW;
    private Status status = Status.NOTDONE;
    private Date date = new Date();

    public ToDoItem(String title, Priority priority, Status status, Date date) {
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Create a new ToDoItem from data packaged in an Intent
    public ToDoItem(Intent intent) {

        title = intent.getStringExtra(ToDoItem.TITLE);
        priority = Priority.valueOf(intent.getStringExtra(ToDoItem.PRIORITY));
        status = Status.valueOf(intent.getStringExtra(ToDoItem.STATUS));
        try {
            date = ToDoItem.FORMAT.parse(intent.getStringExtra(ToDoItem.DATE));
        }
        catch (ParseException e) {
            date = new Date();
        }
    }

    // Take a set of String data values and package them for transport in an Intent
    public static void packageIntent(Intent intent, String title, Priority priority, Status status, String date) {
        intent.putExtra(ToDoItem.TITLE, title);
        intent.putExtra(ToDoItem.PRIORITY, priority.toString());
        intent.putExtra(ToDoItem.STATUS, status.toString());
        intent.putExtra(ToDoItem.DATE, date);
    }

    public String toString() {
        return title + ITEM_SEP + priority + ITEM_SEP + status + ITEM_SEP
                + FORMAT.format(date);
    }

    public String toLog() {
        return "Title:" + title + ITEM_SEP + "Priority:" + priority
                + ITEM_SEP + "Status:" + status + ITEM_SEP + "Date:"
                + FORMAT.format(date) + "\n";
    }
}
