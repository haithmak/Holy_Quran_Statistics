package com.example.holyquranstatistics;

import java.util.UUID;

public class Quran {
    private UUID mId;
    private String surhNumber;
    private String surhName;
    private String surhayhNumbers;
    private String ayhNumber;
    private String surhtype;
    private int surhStart ;
    private int surhEnd ;
    private int surhwordsCount ;
    private int surhJoumal ;


    private String jzaNumber;

    public int getSurhwordsCount() {
        return surhwordsCount;
    }

    public void setSurhwordsCount(int surhwordsCount) {
        this.surhwordsCount = surhwordsCount;
    }

    public int getSurhJoumal() {
        return surhJoumal;
    }

    public void setSurhJoumal(int surhJoumal) {
        this.surhJoumal = surhJoumal;
    }

    public int getSurhStart() {
        return surhStart;
    }

    public void setSurhStart(int surhStart) {
        this.surhStart = surhStart;
    }

    public int getSurhEnd() {
        return surhEnd;
    }

    public void setSurhEnd(int surhEnd) {
        this.surhEnd = surhEnd;
    }

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
