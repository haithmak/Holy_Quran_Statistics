package com.example.holyquranstatistics;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuranListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private QuranAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quran_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;

    }

    private void updateUI() {
        QuranFahras quranFahras = QuranFahras.get(getActivity());
      //  Toast.makeText(getActivity(), "the size is "+quranFahras.getFahras().size(), Toast.LENGTH_SHORT).show();
        List<Quran> quranList = quranFahras.getFahras();

        mAdapter = new QuranAdapter(quranList);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    private class QuranAdapter extends RecyclerView.Adapter<QuraneHolder>  {
        private List<Quran> mQurans;

        public QuranAdapter(List<Quran> qurans)
        {
            mQurans = qurans;
        }

        @NonNull
        @Override
        public QuraneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new QuraneHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuraneHolder holder, int position) {
            Quran quran = mQurans.get(position);
            holder.bind(quran) ;
        }

        @Override
        public int getItemCount() {
            return mQurans.size();
        }
    }


    private class QuraneHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSurahNameTextView;
        private TextView mSurahIdTextView;
        private TextView mSurahAyhNoTextView;
        private TextView mSurahTypeTextView;

        private Quran mQuran;

        public QuraneHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_quran, parent, false));
            itemView.setOnClickListener(this);

            mSurahNameTextView = (TextView) itemView.findViewById(R.id.surah_name);
            mSurahIdTextView = (TextView) itemView.findViewById(R.id.surah_id) ;
            mSurahAyhNoTextView = (TextView) itemView.findViewById(R.id.ayh_count) ;
            mSurahTypeTextView =  (TextView) itemView.findViewById(R.id.surh_type) ;
        }

        public void bind(Quran quran) {
            mQuran = quran;
            mSurahNameTextView.setText("سورة "+mQuran.getSurhName());
            mSurahIdTextView.setText(mQuran.getSurhNumber());
            mSurahAyhNoTextView.setText("عدد الآيات : "+mQuran.getSurhayhNumbers());
            mSurahTypeTextView.setText(mQuran.getSurhtype());

        }
        @Override
        public void onClick(View v) {

            Toast.makeText(getActivity(),  "Id= " + mQuran.getSurhNumber() + " Title = " + mQuran.getSurhName() + " clicked!", Toast.LENGTH_SHORT) .show();

        }
    }



}
