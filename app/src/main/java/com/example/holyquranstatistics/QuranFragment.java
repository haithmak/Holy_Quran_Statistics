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

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class QuranFragment extends Fragment {
    TextView tx , txSurhTitle ;

    Quran mQuran;

    int surhId  , startSurhFrom=0 ,surhayhCount =0 , finshSurh=0;
    Bundle bundle ;
    private static final String ARG_SURH_ID = "surh_Id";
    public static final String EXTRA_ID = "SID";


    public static QuranFragment newInstance(UUID Id ) {
        QuranFragment fragment = new QuranFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ID, Id);
        fragment.setArguments(args);
        return  fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments() ;
        UUID S_Id = (UUID) getArguments().getSerializable(EXTRA_ID);

        mQuran = QuranFahras.get(getActivity()).getFahrass(S_Id);


        if(S_Id != null)
        {
            Toast.makeText(getActivity(), "SNO "+ S_Id, Toast.LENGTH_SHORT).show();
         //   S_Id = (String) getArguments().getSerializable(ARG_SURH_ID);


        }





      //  String surh_Id = (String) getArguments().getSerializable(ARG_SURH_ID);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);

        bundle = getActivity().getIntent().getExtras();

        surhId = Integer.parseInt(mQuran.getSurhNumber());

        String surhName = mQuran.getSurhName() ;

        surhayhCount = Integer.parseInt(mQuran.getSurhayhNumbers());

        startSurhFrom =  Integer.parseInt(bundle.getString(SurhPagerActivity.EXTRA_AYH_START));

        finshSurh= surhayhCount + startSurhFrom ;


       // Toast.makeText(getActivity(),  "Id= " + surhId + " Count = " + finshSurh , Toast.LENGTH_SHORT) .show();
      // mQuran = QuranFahras.get(getActivity()).getFahras(surhId);


        tx = (TextView) v.findViewById(R.id.multiAutoCompleteTextView) ;
        txSurhTitle= (TextView) v.findViewById(R.id.Surh_title) ;


        tx.setText(readAyh(surhId));
        txSurhTitle.setText("سورة " + surhName);


         Toast.makeText(getActivity(),  " tx.getLineCount()= " +  tx.getText().length() , Toast.LENGTH_SHORT) .show();


        return v;
    }


    public String readAyh( int id) {


        int surhId = (int) id ;
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

            if (surhId==1) {
                startSurhFrom =1;
            }
            else {
                startSurhFrom+=surhId-2 ;
            }

            //Toast.makeText(getActivity(),  "Str= " + startSurhFrom + " Count = " +finshSurh , Toast.LENGTH_SHORT) .show();

            for (int j = startSurhFrom ; j < finshSurh ; j++){

                ayht= s.getCell( 2, j);
                ayahC=s.getCell( 3, j);


                xx = xx + ayht.getContents() + convert_number_of_ayah(ayahC.getContents()) + " ";

            }
            startSurhFrom=0;
            surhId=0;

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




