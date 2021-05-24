package com.example.demo.controller;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable {

    private int stringNumber;

    private String streetName;

    private Date effectiveDate;

    public int getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(int stringNumber) {
        this.stringNumber = stringNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "Address{" +
                "stringNumber=" + stringNumber +
                ", streetName='" + streetName + '\'' +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
