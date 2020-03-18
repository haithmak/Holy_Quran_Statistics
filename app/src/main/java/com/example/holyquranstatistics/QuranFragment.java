package com.example.holyquranstatistics;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    Quran mQuran;
    int surhayhNumbersf ;
    int surhIdf  , st_Read;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);
        Bundle bundle = getActivity().getIntent().getExtras();


        String surhId = bundle.getString(MainActivity.EXTRA_SURH_ID);

        String  surhayhNumbers = bundle.getString(MainActivity.EXTRA_AYHT_COUNT);

        String  surhaStart = bundle.getString(MainActivity.EXTRA_AYH_START);



        surhIdf = Integer.parseInt(surhId) ;
        surhayhNumbersf= Integer.parseInt(surhayhNumbers) ;
        st_Read= Integer.parseInt(surhaStart)  ;
        Toast.makeText(getActivity(),  "Id= " + surhIdf + " Count = " + surhayhNumbersf , Toast.LENGTH_SHORT) .show();
      // mQuran = QuranFahras.get(getActivity()).getFahras(surhId);


        tx = (TextView) v.findViewById(R.id.multiAutoCompleteTextView) ;

       tx.setText(readAyh(surhId));

        return v;
    }


    public String readAyh( String surhId) {
        AssetManager  am = getActivity().getAssets();
        Workbook workbook = null;
        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(1);
            int row = s.getRows();
            int cols = s.getColumns();
            String xx = "";
            Cell sid =null;
            Cell sna =null;
            Cell sAyhCount =null;
            Cell ayht =null;
            Cell ayahC =null;

           //sid = s.getCell( 0, i);

            if (surhIdf==1) {
                st_Read =1;
            }
            else {
                st_Read+=surhIdf-2 ;
            }

            Toast.makeText(getActivity(),  "Str= " + st_Read + " Count = " +surhayhNumbersf , Toast.LENGTH_SHORT) .show();

            for (int j = st_Read ; j < surhayhNumbersf+st_Read ; j++){

                ayht= s.getCell( 2, j);
                ayahC=s.getCell( 3, j);

                xx = xx + ayht.getContents() + convert_number_of_ayah(ayahC.getContents()) + " ";



            }
            st_Read=0;
            surhIdf=0;

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




