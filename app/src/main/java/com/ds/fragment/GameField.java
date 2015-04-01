package com.ds.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.dongqiudi.R;

/**
 * Created by aaa on 15-3-30.
 */
public class GameField extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gamedetail_chat,container,false);
        return view;
    }
}
