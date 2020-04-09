package com.example.holyquranstatistics;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class QueryUtilsList {


    private static QueryUtilsList sQueryUtilsList;

    private static List<Quran> quranList ;

    private static List<Quran> mquranList ;

    private static List<SourhJoumal> mSourhJoumals;


    public String sList;

    public static QueryUtilsList get(Context context) {
        if (sQueryUtilsList == null) {
            sQueryUtilsList = new QueryUtilsList(context);
        }
        return sQueryUtilsList;
    }

    private QueryUtilsList(Context context) {

        try {
            InputStream   is = context.getAssets().open("chapters.json");
            sList= readFromStream(is);
            mquranList = extractListFromJson(sList);

        } catch (IOException e) {
            e.printStackTrace();


        }

    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }



    private List<Quran> extractListFromJson(String listJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(listJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Surah Detials to

        try {

            JSONArray quranListJson = new JSONArray(listJSON);
            quranList = new ArrayList<>();
            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            int ayhend =0 , ayhstart =0;
            int newstart=0 , end=0;
            for (int i = 0; i < quranListJson.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentQuranList = quranListJson.getJSONObject(i);

                String id = currentQuranList.getString("id");

                String name = currentQuranList.getString("name_arabic");

                String verses_count = currentQuranList.getString("verses_count");

                String revelation_place = currentQuranList.getString("revelation_place");

                String words_count = currentQuranList.getString("words_count");

                String joumal = currentQuranList.getString("joumal");

                JSONArray surhPageStartList =  currentQuranList.getJSONArray("pages");

                int  surhPageStart= (int) surhPageStartList.get(1);



               // Quran quran = new Quran(id, name, verses_count, revelation_place);

             //   quranList.add(quran);


            //    Quran quran = new Quran(id, name, verses_count, revelation_place,words_count ,joumal );

              //  quranList.add(quran);

                if (currentQuranList.getJSONArray("pageayh")!=null) {

                    JSONArray pages = currentQuranList.getJSONArray("pageayh");

                    for (int j = 0; j < pages.length(); j++) {

                        JSONObject currentPagesQuranList = pages.getJSONObject(j);

                        int pagenum = currentPagesQuranList.getInt("pagenum");

                         ayhstart = currentPagesQuranList.getInt("start");
                         ayhend = currentPagesQuranList.getInt("end");



                      //  Quran quran = new Quran(id, name, verses_count, revelation_place,words_count ,joumal );
                        Quran quran = new Quran(id, name ,surhPageStart , verses_count, revelation_place,words_count ,joumal );
                        newstart = end + 1  ;

                        end = (ayhend - ayhstart) + newstart  ;

                        if(id.equals("1"))
                        {

                            quran.setSurhStart(ayhstart);
                            quran.setSurhEnd(ayhend);
                            quran.setSurhPageNumber(pagenum);
                        }
                        else
                        {


                            quran.setSurhStart(newstart);
                            quran.setSurhEnd(end);
                            quran.setSurhPageNumber(pagenum);



                        }





                       // quran = new Quran( quran.getId(), id, name, pagenum , ayhstart ,ayhend );
                        quranList.add(quran);

                    }

                }





                //  Quran quranPages = new Quran(quran.getId()  ,id, name, pagesCount , ) ;


            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return quranList;
    }




    public List<Quran> getFahrasList() {
        List<Quran> mQuranFahras= new ArrayList<Quran>() ;

        for (int i = 0; i < mquranList.size(); i++)
        {
            boolean isDistinct = false;
            for (int j = 0; j < i; j++)
            {
                if (mquranList.get( i ).getSurhNumber().equals(mquranList.get( j ).getSurhNumber()))
                {
                    isDistinct = true;
                    break;
                }
            }
            if (!isDistinct)
            {
                mQuranFahras.add(mquranList.get(i));
            }

        }
        return mQuranFahras;
    }





    public Quran getFahrass(UUID id) {
        for (Quran q : mquranList) {
            if (q.getId().equals(id)) {
                return q;
            }
        }

        return null;
    }



    public List<SourhJoumal> getJoumalFahras(Context context , int sSourh , int eSourh) {

        mSourhJoumals  = new ArrayList<>();

        AssetManager am = context.getAssets();
        Workbook workbook = null;
        int i =0 , j= 0 ;

        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(1);

            if(s.getRows() > 0 && s.getColumns() >0 )
            {

            }


            Cell ayhNo =null;
            Cell ayhTxt =null;

            Cell ayhWordsC = null;
            Cell ayhCharC =null;

            Cell ayhJoumal =null;

            SourhJoumal sourhJoumal = null;
            Toast.makeText(context,  "  surah end 2= " + eSourh  , Toast.LENGTH_SHORT).show();

            if(sSourh == 1)
            {
                eSourh =8 ;

            }else
            {
                eSourh+= sSourh;
            }

            for ( i = sSourh; i < eSourh  ; i++) {

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


            return mSourhJoumals ;
    }




    public List<SourhJoumal> getJoumalFahras() {
        return mSourhJoumals;
    }




    public List<Quran> getAllPages() {

        return mquranList;
    }






}
