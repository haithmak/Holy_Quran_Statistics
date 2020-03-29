package com.example.holyquranstatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JoumalFragment  extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private JoumalAyhAdapter mAdapter;

    private SourhJoumal mSourhJoumal;
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_SURH_START = "FROM";

    int surhStart;
    int surhEnd ;
    Intent intent ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         intent = getActivity().getIntent() ;


            if(intent.hasExtra(EXTRA_SURH_START)&&intent.hasExtra(EXTRA_AYHT_COUNT)){

                surhStart= Integer.parseInt(intent.getStringExtra(EXTRA_SURH_START)) ;
                surhEnd= Integer.parseInt(intent.getStringExtra(EXTRA_AYHT_COUNT))+1 ;


            }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_joumal_list, container, false);


//        getActivity().getActionBar().setTitle("yourString");
        mCrimeRecyclerView = v.findViewById(R.id.joumal_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI() ;


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("test");
    }

    private void updateUI() {

        JoumalFahras joumalFahras = JoumalFahras.get(getActivity(),surhStart,surhEnd);

        List<SourhJoumal> sourhJoumal = joumalFahras.getFahras();

       if (mAdapter == null) {
            mAdapter = new JoumalAyhAdapter(sourhJoumal);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }

    }



    private class JoumalAyhAdapter extends RecyclerView.Adapter<JoumalAyhHolder> {

        private List<SourhJoumal> mSourhJoumals;

        public JoumalAyhAdapter(List<SourhJoumal> joumals) {
            mSourhJoumals = joumals;
        }

        @NonNull
        @Override
        public JoumalAyhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JoumalAyhHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull JoumalAyhHolder holder, int position) {
            SourhJoumal joumal = mSourhJoumals.get(position);

            holder.bind(joumal);
        }

        @Override
        public int getItemCount() {
            return mSourhJoumals.size();
        }
    }


    private class JoumalAyhHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mAyhTxtTextView;
        private TextView mWords_ayh_countTextView;
        private TextView mChar_ayh_countTextView;
        private TextView mJoumal_ayh_countTextView;
        private TextView mAyh_idTextView;
        private TextView mSurhJoumalCounteTextView;
        private SourhJoumal mSourhJoumal;


        public JoumalAyhHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_ayh_joumal, parent, false));
            itemView.setOnClickListener(this);

            mAyhTxtTextView = (TextView) itemView.findViewById(R.id.ayh_text);
            mWords_ayh_countTextView = (TextView) itemView.findViewById(R.id.words_ayh_count) ;

            mChar_ayh_countTextView = (TextView) itemView.findViewById(R.id.char_ayh_count) ;

            mJoumal_ayh_countTextView =  (TextView) itemView.findViewById(R.id.joumal_ayh_count) ;

            mAyh_idTextView=  (TextView) itemView.findViewById(R.id.ayh_id) ;

        }

        public void bind(SourhJoumal quran) {
            mSourhJoumal = quran;
            mAyhTxtTextView.setText(mSourhJoumal.getAyhText());
            mWords_ayh_countTextView.setText("عدد كلمات الآية:"+"\n"+mSourhJoumal.getAyhWordsCount());
            mChar_ayh_countTextView.setText("عدد حروف الآية:"+"\n"+mSourhJoumal.getAyhCharCount());
            mJoumal_ayh_countTextView.setText("اجمالي جُمَّل الآية:"+"\n"+mSourhJoumal.getAyhJoumal());
            mAyh_idTextView.setText(convert_number_of_ayah(mSourhJoumal.getAyhNumber()));


        }
        @Override
        public void onClick(View v) {


            // Toast.makeText(getActivity(),  "  surah Id = " +readAyh(mQuran.getSurhNumber())  , Toast.LENGTH_SHORT).show();

          //  Intent intent = new Intent(getActivity() , JoumalFragment.class);

    //        startActivity(intent);

            //  Intent intent = MainActivity.newIntent(getActivity(), Integer.parseInt(mQuran.getSurhNumber()) , mQuran.getSurhName() , mQuran.getSurhayhNumbers() , startSurhFrom);

            //startActivity(intent);

            //  Intent intent = SurhPagerActivity.newIntent(getActivity(),mQuran.getId() , mQuran.getSurhNumber() , mQuran.getSurhName() , mQuran.getSurhayhNumbers() ,mQuran.getSurhStart() , mQuran.getSurhEnd() );
            // Intent intent = SurhPagerActivity.newIntent(getActivity(),mQuran.getId());
            //startActivity(intent);



        }
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
