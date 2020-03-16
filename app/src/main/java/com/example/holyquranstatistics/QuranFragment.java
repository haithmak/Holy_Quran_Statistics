package com.example.holyquranstatistics;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class QuranFragment extends Fragment {
    TextView tx ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);
        tx = (TextView) v.findViewById(R.id.multiAutoCompleteTextView) ;

       tx.setText(readAyh());

        return v;
    }


    public String readAyh( ) {
        AssetManager  am = getActivity().getAssets();
        Workbook workbook = null;
        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(0);
            int row = s.getRows();
            int cols = s.getColumns();
            String xx = "";

            for (int i = 1; i < row -1; i++) {

                for (int c = 2; c < 3; c++) {
                    Cell z = s.getCell(c, i);
                    Cell ayahC = s.getCell(c + 1, i);
                    xx = xx + z.getContents() + convert_number_of_ayah(ayahC.getContents());


                }
                xx = xx + " ";
            }

            return xx;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        return null;
    }

    String convert_number_of_ayah (String n)
    {


        String cnu , fnu="" ;
        for(int i=0 ; i < n.length(); i++)
        {
            cnu = n.substring(i, i+1) ;
            switch (cnu)
            {
                case "0" :
                    fnu = "\u0660" + fnu;
                    break;
                case "1" :
                    fnu = "\u0661" + fnu;
                    break;
                case "2" :
                    fnu = "\u0662"+ fnu;
                    break;
                case "3" :
                    fnu = "\u0663" + fnu;
                    break;
                case "4" :
                    fnu = "\u0664" + fnu;
                    break;
                case "5" :
                    fnu = "\u0665" + fnu;
                    break;
                case "6" :
                    fnu = "\u0666" + fnu;
                    break;
                case "7" :
                    fnu = "\u0667" + fnu;
                    break;
                case "8" :
                    fnu = "\u0668" + fnu;
                    break;
                case "9" :
                    fnu = "\u0669" + fnu;
                    break;
            }
        }




        return fnu;

    }





    }




