package com.example.holyquranstatistics;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class QuranFahras {

    private static QuranFahras sQuranFahras ;

    private List<Quran> mQurans;

    public static QuranFahras get(Context context) {
        if (sQuranFahras == null) {
                sQuranFahras = new QuranFahras(context);
        }
        return sQuranFahras;
    }

    private QuranFahras(Context context) {

        mQurans = new ArrayList<>();

        AssetManager am = context.getAssets();
        Workbook workbook = null;
        int i =0 , j= 0 ;

        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(0);
            int row = s.getRows();
            int cols = s.getColumns();
            Cell sid =null;
            Cell sna =null;
            Cell sAyhCount =null , sAyhCountL;
            Cell sType =null;
            Cell surhStartC , surhEndC ;
            int pageAyhCount =0 ;


            Quran quran = null;

            for ( i = 1; i < 50 ; i++) {
                 quran = new Quran();


                     sid  = s.getCell( 0, i);
                     sna = s.getCell(1, i);

                     sAyhCount= s.getCell( 2, i);
                     sType= s.getCell( 3, i);

                    quran.setSurhNumber(sid.getContents());
                    quran.setSurhName(sna.getContents());

                    quran.setSurhayhNumbers(sAyhCount.getContents());



                    quran.setSurhtype(sType.getContents());

                    surhStartC= s.getCell( 4, i);
                    surhEndC= s.getCell( 5, i);

                     quran.setSurhStart(Integer.parseInt(surhStartC.getContents()));
                     quran.setSurhEnd(Integer.parseInt(surhEndC.getContents()));


                    mQurans.add(quran);

            }


/*
            for ( i = 0; i < mQurans.size(); i++)
            {
                boolean isDistinct = false;
                for ( j = 0; j < i; j++)
                {
                    if (mQurans.get( i ).getSurhNumber().equals(mQurans.get( j ).getSurhNumber()))
                    {
                        isDistinct = true;

                        break;
                    }
                }
                if (!isDistinct)
                {
                    mQuranFahras.add(mQurans.get(i));
                                    }
              //  Toast.makeText(context,  "  surah Id = " + isDistinct , Toast.LENGTH_SHORT).show();
            }*/


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



    public List<Quran> getFahras() {
        return mQurans;
    }

    public Quran getFahrass(UUID id) {
        for (Quran q : mQurans) {
            if (q.getId().equals(id)) {
                return q;
            }
        }

        return null;
    }




}
