package com.call.logs.models;

public class CallLogs {

    private String mobileNumber;
    private String dateTime;

    public CallLogs(String mobile, String date) {
        this.mobileNumber = mobile;
        this.dateTime = date;
    }

    public CallLogs() {

    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
