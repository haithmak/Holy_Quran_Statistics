package com.example.holyquranstatistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JoumalListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private  JoumalAdapter mAdapter;

    public static final String EXTRA_ID = "SID";
    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_SURH_START = "FROM";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_joumal_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.joumal_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI() ;
        return view;

    }



    private void updateUI() {



        List<Quran> mquranList=QueryUtilsList.get(getActivity()).getFahrasList() ;

        /*


        List<Quran> quranList = quranFahras.getFahrasJoumal();
     //   List<Quran> mquranList= quranFahras.getFahrasJoumal();

        for (int i =0 ; i <2 ;i++)
        {
           quranList.set(i, quranList.get(i)).setSurhwordsCount(Integer.parseInt(readAyh(quranList.get(i).getSurhStart(),8)));
           quranList.set(i, quranList.get(i)).setSurhJoumal(Integer.parseInt(readAyh(quranList.get(i).getSurhStart(),9)));

          //  quranList.set(i, quranList.get(i)).setSurhwordsCount(Integer.parseInt(readAyh(quranList.get(i).getSurhNumber())));
        }


         */
        if (mAdapter == null) {
            mAdapter = new JoumalAdapter(mquranList);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private class JoumalAdapter extends RecyclerView.Adapter<JoumalHolder>  {
        private List<Quran> mQurans;

        public JoumalAdapter(List<Quran> qurans)
        {
            mQurans = qurans;
        }

        @NonNull
        @Override
        public JoumalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JoumalHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull JoumalHolder holder, int position) {
            Quran quran = mQurans.get(position);
            holder.bind(quran) ;
        }



        @Override
        public int getItemCount() {
            return mQurans.size();
        }

    }
    private class JoumalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSurahNameTextView;
        private TextView mSurahIdTextView;
        private TextView mSurahAyhNoTextView;
        private TextView mSurahTypeTextView;
        private TextView mSurhwordsCounteTextView;
        private TextView mSurhJoumalCounteTextView;
        private Quran mQuran;


        public JoumalHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_joumal, parent, false));
            itemView.setOnClickListener(this);

            mSurahNameTextView = (TextView) itemView.findViewById(R.id.surah_name);
            mSurahIdTextView = (TextView) itemView.findViewById(R.id.surah_id) ;
            mSurahAyhNoTextView = (TextView) itemView.findViewById(R.id.ayh_count) ;
            mSurahTypeTextView =  (TextView) itemView.findViewById(R.id.surh_type) ;
            mSurhwordsCounteTextView=  (TextView) itemView.findViewById(R.id.sourh_count_words) ;
            mSurhJoumalCounteTextView=  (TextView) itemView.findViewById(R.id.sourh_joumal_count) ;
        }

        public void bind(Quran quran) {
            mQuran = quran;
            mSurahNameTextView.setText("سورة "+mQuran.getSurhName());
            mSurahIdTextView.setText(mQuran.getSurhNumber());
            mSurahAyhNoTextView.setText("عدد الآيات:"+"\n"+mQuran.getSurhayhNumbers());
            mSurahTypeTextView.setText(mQuran.getSurhtype());
            mSurhwordsCounteTextView.setText("عدد الكلمات:"+"\n"+mQuran.getSurhwordsCount());
            mSurhJoumalCounteTextView.setText("جُمَّل السورة:"+"\n"+mQuran.getSurhJoumal());


        }
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity() , JoumalListActivity.class);

            intent.putExtra(EXTRA_SURH_START ,String.valueOf(mQuran.getSurhStart())) ;
            intent.putExtra(EXTRA_AYHT_COUNT ,mQuran.getSurhayhNumbers()) ;
            intent.putExtra( EXTRA_SURH_ID , mQuran.getSurhNumber());
            intent.putExtra(EXTRA_SURH_NAME , mQuran.getSurhName());

             Toast.makeText(getActivity(),  "  surah Id = " +intent.getStringExtra(EXTRA_SURH_START)  , Toast.LENGTH_SHORT).show();
            startActivity(intent);




        }
    }

}
