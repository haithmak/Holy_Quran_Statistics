package com.example.holyquranstatistics;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JoumalFahras {
    private List<SourhJoumal> mSourhJoumals;
    private static JoumalFahras sJoumalFahras ;




    public static JoumalFahras get(Context context , int s , int c) {
            sJoumalFahras=null;
            sJoumalFahras = new JoumalFahras(context  , s ,c);

        return sJoumalFahras;
    }

    public JoumalFahras(Context context , int sSourh , int eSourh) {

        mSourhJoumals  = new ArrayList<>();

        AssetManager am = context.getAssets();
        Workbook workbook = null;
        int i =0 , j= 0 ;

        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(1);

            int row = s.getRows();
            int cols = s.getColumns();

            Cell ayhNo =null;
            Cell ayhTxt =null;

            Cell ayhWordsC = null;
            Cell ayhCharC =null;

            Cell ayhJoumal =null;
            int pageAyhCount =0 ;


            SourhJoumal sourhJoumal = null;

            for ( i = sSourh; i < eSourh ; i++) {

                sourhJoumal = new SourhJoumal();

                ayhNo  = s.getCell( 3, i);
                ayhTxt = s.getCell(2, i);
                ayhWordsC= s.getCell( 5, i);
                ayhCharC= s.getCell( 6, i);
                ayhJoumal= s.getCell( 7, i);

                sourhJoumal.setAyhNumber(ayhNo.getContents());
                sourhJoumal.setAyhText(ayhTxt.getContents());
                sourhJoumal.setAyhWordsCount(ayhWordsC.getContents());
                sourhJoumal.setAyhCharCount(ayhCharC.getContents()) ;
                sourhJoumal.setAyhJoumal(ayhJoumal.getContents());


                mSourhJoumals.add(sourhJoumal);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }



    }

    public List<SourhJoumal> getFahras() {

        return mSourhJoumals;
    }



    public SourhJoumal getFahrass(String id) {
        for (SourhJoumal q : mSourhJoumals) {
            if (q.getSurhayhNumbers().equals(id)) {
                return q;
            }
        }

        return null;
    }








}



