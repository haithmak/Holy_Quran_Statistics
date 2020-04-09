package com.example.holyquranstatistics;

import java.util.UUID;

public class Quran {

    private UUID mId;
    private String surhNumber;
    private String surhName;
    private String surhayhNumbers;
    private String ayhNumber;
    private String surhtype;

    private String surhwordsCount ;
    private String surhJoumal ;
    private String jzaNumber;

    private int surhPageNumber ;
    private int surhStart ;
    private int surhEnd ;

    public String getSurhwordsCount() {
        return surhwordsCount;
    }

    public void setSurhwordsCount(String surhwordsCount) {
        this.surhwordsCount = surhwordsCount;
    }

    public String getSurhJoumal() {
        return surhJoumal;
    }

    public void setSurhJoumal(String surhJoumal) {
        this.surhJoumal = surhJoumal;
    }






    public int getSurhPageNumber() {
        return surhPageNumber;
    }

    public void setSurhPageNumber(int surhPageNumber) {
        this.surhPageNumber = surhPageNumber;
    }



    public Quran(String surhNumber , String surhName ,String surhayhNumbers , String surhtype , String surhwordsCount ,String surhJoumal){
        mId = UUID.randomUUID();
        this.surhNumber = surhNumber ;
        this.surhName=surhName;
        this.surhayhNumbers = surhayhNumbers;
        this.surhtype = surhtype;
        this.surhwordsCount =surhwordsCount;
        this.surhJoumal =surhJoumal;

    }

    public Quran(String surhNumber , String surhName , int surhPageNumber ,String surhayhNumbers , String surhtype , String surhwordsCount ,String surhJoumal){
        mId = UUID.randomUUID();
        this.surhNumber = surhNumber ;
        this.surhName=surhName;
        this.surhPageNumber =surhPageNumber ;
        this.surhayhNumbers = surhayhNumbers;
        this.surhtype = surhtype;
        this.surhwordsCount =surhwordsCount;
        this.surhJoumal =surhJoumal;
    }


    public Quran(UUID mId ,String surhNumber , String surhName , int surhPageNumber ,int surhStart  , int surhEnd  ){
        this.mId = mId ;
        this.surhNumber = surhNumber ;
        this.surhName=surhName;
        this.surhPageNumber =surhPageNumber;
        this.surhStart = surhStart;
        this.surhEnd = surhEnd;

    }




    public Quran(){
        mId = UUID.randomUUID();
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
