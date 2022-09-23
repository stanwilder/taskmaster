package com.stanley.taskmaster.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stanley.taskmaster.R;


public class FragView extends Fragment {



    public FragView() {
        // Required empty public constructor
    }

        public static FragView newInstance(String p1, String p2){
            FragView frag = new FragView();
            return frag;
        }

        @Override
        public void onCreate(Bundle saveState){
            super.onCreate(saveState);
        }
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState){
            return inflater.inflate(R.layout.fragment_frag_view, container, false);
        }
    }
