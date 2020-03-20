package com.example.holyquranstatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.read.biff.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    TextView tx ;
    String TAG ="main";

/*
    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";

    public static final String EXTRA_AYH_START = "FROM";


   // public static final int x = 0;

    public static Intent newIntent(Context packageContext, int surhID , String surhName,  String surhayhNumbers , int startSurhFrom) {

        Intent intent = new Intent(packageContext, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SURH_ID, surhID);
        bundle.putString(EXTRA_SURH_NAME, surhName);
        bundle.putString(EXTRA_AYHT_COUNT, surhayhNumbers);

        bundle.putInt(EXTRA_AYH_START, startSurhFrom);
        intent.putExtras(bundle);


        return intent;
    }
    */

    @Override
    protected Fragment createFragment() {






        return new QuranFragment();
    }




        /*
        tx = (TextView) findViewById(R.id.multiAutoCompleteTextView) ;
        AssetManager am = getAssets();
        Workbook workbook = null;
        try {
            InputStream is = am.open("DB.xls");
             workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(0);
            int row = s.getRows();
            int cols = s.getColumns();
            String xx = "";

            for (int i = 1; i < 8; i++) {

                for (int c = 2; c < 3; c++)
                {
                    Cell z = s.getCell(c, i);
                    Cell ayahC = s.getCell(c+1, i);
                    xx = xx + z.getContents() + convert_number_of_ayah(ayahC.getContents());


                }
                xx = xx + " " ;
            }

            tx.setText(xx);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }finally {
        if (workbook != null) {
            workbook.close();
        }
    }

         */
    }




/*
    public void readExcelFileFromAssets() {
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet
            myInput = assetManager.open("DB.xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno =0;
            tx.append("\n");
            while (rowIter.hasNext()) {
                Log.e(TAG, " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    String sno="", date="", det="";
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            sno = myCell.toString();
                        }else if (colno==1){
                            date = myCell.toString();
                        }else if (colno==2){
                            det = myCell.toString();
                        }
                        colno++;
                        Log.e(TAG, " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }
                    tx.append( sno + " -- "+ date+ "  -- "+ det+"\n");
                }
                rowno++;
            }
        } catch (Exception e) {
            Log.e(TAG, "error "+ e.toString());
        }
    }*/


