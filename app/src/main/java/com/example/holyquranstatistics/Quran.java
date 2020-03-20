package com.example.holyquranstatistics;

import java.util.UUID;

public class Quran {
    private UUID mId;
    private String surhName;
    private String surhNumber;
    private String surhayhNumbers;
    private String ayhNumber;
    private String surhtype;


    public Quran(){
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getSurhayhNumbers() {
        return surhayhNumbers;
    }

    public void setSurhayhNumbers(String surhayhNumbers) {
        this.surhayhNumbers = surhayhNumbers;
    }

    private String jzaNumber;




    public String getSurhName() {
        return surhName;
    }

    public void setSurhName(String surhName) {
        this.surhName = surhName;
    }

    public String getSurhNumber() {
        return surhNumber;
    }

    public void setSurhNumber(String surhNumber) {
        this.surhNumber = surhNumber;
    }

    public String getAyhNumber() {
        return ayhNumber;
    }

    public void setAyhNumber(String ayhNumber) {
        this.ayhNumber = ayhNumber;
    }

    public String getSurhtype() {
        return surhtype;
    }

    public void setSurhtype(String ayhtype) {
        this.surhtype = ayhtype;
    }

    public String getJzaNumber() {
        return jzaNumber;
    }

    public void setJzaNumber(String jzaNumber) {
        this.jzaNumber = jzaNumber;
    }
}
