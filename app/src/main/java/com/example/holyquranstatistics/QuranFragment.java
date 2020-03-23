package com.example.holyquranstatistics;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class QuranFragment extends Fragment {

     TextView tx , txSurhTitle ;

    Quran mQuran;

    int surhId  =0 , startSurhFrom=0 ,surhayhCount =0 , finshSurh=0;
    FragmentManager fm ;

    public static final String EXTRA_ID = "SID";
    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_AYH_START = "FROM";


    public static QuranFragment newInstance(UUID Id ) {
            QuranFragment fragment = new QuranFragment();
            Bundle args = new Bundle();
            args.putSerializable(EXTRA_ID, Id);
            fragment.setArguments(args);
            return  fragment;
    }

    public static QuranFragment newInstance(UUID Id ,Quran nQuran) {
        QuranFragment fragment = new QuranFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ID, Id);
        args.putString(EXTRA_SURH_ID, nQuran.getSurhNumber());
        args.putString(EXTRA_SURH_NAME ,nQuran.getSurhName()); ;
        args.putString(EXTRA_AYHT_COUNT ,nQuran.getSurhayhNumbers());
        args.putInt(EXTRA_AYH_START,nQuran.getSurhStart());

        fragment.setArguments(args);

        return  fragment ;
    }



      @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments() ;
          if (args !=null)
          {
              UUID S_Id = (UUID) getArguments().getSerializable(EXTRA_ID);

               mQuran = QuranFahras.get(getActivity()).getFahrass(S_Id);

          }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);

        tx = (TextView) v.findViewById(R.id.multiAutoCompleteTextView) ;
        txSurhTitle= (TextView) v.findViewById(R.id.Surh_title) ;


       // bundle = getActivity().getIntent().getExtras();

        surhId = Integer.parseInt(mQuran.getSurhNumber());

        String surhName = mQuran.getSurhName() ;

        surhayhCount = Integer.parseInt(mQuran.getSurhayhNumbers());
      //  surhayhCount = Integer.parseInt(bundle.getString(SurhPagerActivity.EXTRA_AYHT_COUNT));

       // startSurhFrom =  Integer.parseInt(bundle.getString(SurhPagerActivity.EXTRA_AYH_START));
        startSurhFrom =  mQuran.getSurhStart() ;
        finshSurh =  mQuran.getSurhEnd(); ;


        tx.setText(readAyh(startSurhFrom ,finshSurh ));

        txSurhTitle.setText("سورة " + surhName);


     //    Toast.makeText(getActivity(),  " tx.getLineCount()= " +  tx.getText().length() , Toast.LENGTH_SHORT).show();



        return v;
    }


    public String readAyh( int sA , int eA) {

        AssetManager  am = getActivity().getAssets();
        Workbook workbook = null;
        try {
            InputStream is = am.open("DB.xls");
            workbook = Workbook.getWorkbook(is);

            Sheet s = workbook.getSheet(1);
            int row = s.getRows();
            int cols = s.getColumns();
            String xx = "";
           // Cell sid =null;
            //Cell sna =null;
            //Cell sAyhCount =null;
            Cell ayht =null;
            Cell ayahC =null;

           //sid = s.getCell( 0, i);
/*
            if (surhId==1) {
                sA =1;
            }
            else  if (surhId==2) {
                sA =8;
            }
            else
            {
                sA -=1 ;
            }*/

            for (int j = sA ; j <= eA; j++){

                ayht= s.getCell( 2, j);
                ayahC=s.getCell( 3, j);

                xx = xx + ayht.getContents() + convert_number_of_ayah(ayahC.getContents()) + " ";

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
                case "n" :
                    fnu =  fnu + "\n";
                    break;

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




