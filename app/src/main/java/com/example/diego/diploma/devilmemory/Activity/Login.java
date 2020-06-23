package com.example.diego.diploma.devilmemory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diego.diploma.devilmemory.Fragmento.Dashboard;
import com.example.diego.diploma.devilmemory.Pantalla_Principal;
import com.example.diego.diploma.devilmemory.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText editcorreo, editcontraseña;
    private Button mButtonLogin,ir_regitrarse;

    private String email="";
    private String password="";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editcorreo = (EditText) findViewById(R.id.reg_correo);
        editcontraseña = (EditText) findViewById(R.id.Ed_password);
        mButtonLogin = (Button) findViewById(R.id.iniciar_login);
        ir_regitrarse =(Button) findViewById(R.id.ir_regitrarse);


        ir_regitrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });




        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editcorreo.getText().toString();
                password = editcontraseña.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else {
                    Toast.makeText(Login.this, "Por favor complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void loginUser(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String email = user.getEmail();
                    String idUsu = user.getUid();
                    Map<String,Object> map = new HashMap<>();
                    map.put("name","");
                    map.put("uid", idUsu);
                    map.put("email", email);
                    map.put("password", "");
                    map.put("idjuego", "");
                    map.put("imagenUsu", "");
                    map.put("type", "");
                    startActivity(new Intent(Login.this, Dashboard.class));
                    finish();
                }else {
                    Toast.makeText(Login.this, "No se pudo iniciar sesion compruebe que los datos sean correctos", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private  void  checkUserStatus(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!= null) {
            // user is signed in stay here

            startActivity(new Intent(Login.this, Pantalla_Principal.class));
            finish();
        }else{

        }
    }
    protected  void onStart(){
        checkUserStatus();
        super.onStart();
    }
}
