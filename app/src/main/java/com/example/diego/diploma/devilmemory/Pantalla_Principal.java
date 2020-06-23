package com.example.diego.diploma.devilmemory;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.diego.diploma.devilmemory.Activity.Login;
import com.example.diego.diploma.devilmemory.Fragmento.Dashboard;
import com.example.diego.diploma.devilmemory.Fragmento.EstadisticasFragment;
import com.example.diego.diploma.devilmemory.Fragmento.InformacionFragment;
import com.example.diego.diploma.devilmemory.Fragmento.OtrosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Pantalla_Principal extends AppCompatActivity {
    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__principal);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();


        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //opcione para inicar la navegacion inferior
        Dashboard fragment1 = new Dashboard();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content,fragment1,"");
        ft1.commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    // opcines para las navegaxion inferior
                    switch (menuItem.getItemId()) {
                        case R.id.nav_Home:

                            Dashboard fragment1 = new Dashboard();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content, fragment1, "");
                            ft1.commit();
                            return true;

                        case R.id.nav_esta:

                            EstadisticasFragment fragment2 = new EstadisticasFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.commit();
                            return true;
                        case  R.id.nav_info:

                            InformacionFragment fragment3 = new InformacionFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content, fragment3, "");
                            ft3.commit();
                            return true;
                        case  R.id.nav_date:

                            OtrosFragment fragment4 = new OtrosFragment();
                            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                            ft4.replace(R.id.content, fragment4, "");
                            ft4.commit();
                                return true;

                    }

                    return false;
                }

            };
    private  void  checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!= null) {
            // usuario ha iniciado sesión aquí


        }else{
            startActivity(new Intent(Pantalla_Principal.this, Login.class));
            finish();
        }
    }
    @Override
    protected  void onStart(){
        checkUserStatus();
        super.onStart();
    }
}


