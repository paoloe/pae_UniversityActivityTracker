package com.example.universityactivitytracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Record {
    @PrimaryKey  (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "moduleCode")
    public String module;

    @ColumnInfo(name = "clockInStamp")
    public Date clockIn;

    @ColumnInfo(name = "clockOutStamp")
    public Date clockOut;

    @ColumnInfo(name = "note")
    public String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getClockIn() {
        return clockIn;
    }

    public void setClockIn(Date clockIn) {
        this.clockIn = clockIn;
    }

    public Date getClockOut() {
        return clockOut;
    }

    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }
}
