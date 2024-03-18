package com.mastercoding.bakalaurinis.view.kingdoms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastercoding.bakalaurinis.R;


public class KingdomViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int kingdomId = getArguments().getInt("kingdomId", -1);
        View view;
        switch (kingdomId) {
            default:
                view = inflater.inflate(R.layout.fragment_elfu_karalyste, container, false);
                break;
//            case 2:
//                view = inflater.inflate(R.layout.fragment_kingdom_view_elf_kingdom, container, false);
//                break;
//            default:
//                view = inflater.inflate(R.layout.fragment_kingdom_view_elf_kingdom, container, false);
//                break;

        }
        return view;
    }


}