package com.ds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.dongqiudi.R;

/**
 * Created by aaa on 15-3-25.
 */
public class DataViewPagerfragment_secondary extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dataviewpagerfragment_secondary,container,false);
        return view;
    }
}
