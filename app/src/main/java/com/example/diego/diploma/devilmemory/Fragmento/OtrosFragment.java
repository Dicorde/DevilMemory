package com.example.diego.diploma.devilmemory.Fragmento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.diego.diploma.devilmemory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtrosFragment extends Fragment {

    public OtrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otros, container, false);
    }
}
