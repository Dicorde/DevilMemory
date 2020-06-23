package com.example.diego.diploma.devilmemory.Fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diego.diploma.devilmemory.Activity.Dm_DuelosActivity;
import com.example.diego.diploma.devilmemory.Activity.Dm_GirlsActivity;
import com.example.diego.diploma.devilmemory.Activity.Dm_LigasActivity;
import com.example.diego.diploma.devilmemory.Activity.Dm_VersusActivity;
import com.example.diego.diploma.devilmemory.Activity.EventosActivity;
import com.example.diego.diploma.devilmemory.Activity.MainActivity;
import com.example.diego.diploma.devilmemory.Activity.NoticiaActivity;
import com.example.diego.diploma.devilmemory.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    ImageView dm_girls, dm_duelo,dm_liga,dm_versu,noticia, evento;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        dm_girls = view.findViewById(R.id.dm_girls);
        dm_duelo = view.findViewById(R.id.dm_duelos);
        dm_liga = view.findViewById(R.id.dm_ligas);
        dm_versu = view.findViewById(R.id.dm_versus);
        noticia = view.findViewById(R.id.noticia);
        evento = view.findViewById(R.id.evento);


        dm_girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dm_GirlsActivity.class);
                startActivity(intent);
            }
        });

        dm_duelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dm_DuelosActivity.class);
                startActivity(intent);
            }
        });

        dm_liga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dm_LigasActivity.class);
                startActivity(intent);
            }
        });

        dm_versu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dm_VersusActivity.class);
                startActivity(intent);
            }
        });

        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventosActivity.class);
                startActivity(intent);
            }
        });

        noticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticiaActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    public void onCreate(@NonNull Bundle savedInstanceState){
        setHasOptionsMenu(true); //para mostrar el menú
        super.onCreate(savedInstanceState);
    }

    //inflar opcion menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.action_logout:
                FirebaseAuth miAuth = FirebaseAuth.getInstance();
                miAuth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
