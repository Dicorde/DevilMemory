package com.example.diego.diploma.devilmemory.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.diego.diploma.devilmemory.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText nombreUsuario, editcorreo, editcontraseña, editidjuego;
    private Button registro;
    private ProgressDialog progressDialog;
    private Switch typeUser;

    private String name = "";
    private String email = "";
    private String password = "";
    private String idjuego = "";
    private Boolean type = false;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        nombreUsuario = (EditText) findViewById(R.id.reg_name);
        editcorreo = (EditText) findViewById(R.id.reg_correo);
        editcontraseña = (EditText) findViewById(R.id.reg_contra);
        editidjuego = (EditText) findViewById(R.id.reg_id_jugador);
        registro = (Button) findViewById(R.id.enviar_registro);
        progressDialog = new ProgressDialog(this);
        typeUser = (Switch) findViewById(R.id.typeUser);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombreUsuario.getText().toString();
                email = editcorreo.getText().toString();
                password = editcontraseña.getText().toString();
                idjuego = editidjuego.getText().toString();
                type = typeUser.isChecked();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !idjuego.isEmpty()) {
                    if (password.length() >= 6) {
                        registerUser();
                    } else {
                        Toast.makeText(Registro.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Registro.this, "Debe completar los demas campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    FirebaseUser user = mAuth.getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String email = user.getEmail();
                    String idUsu = user.getUid();



                    Map<String,Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("uid", idUsu);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("idjuego", idjuego);
                    map.put("imagenUsu", "");
                    map.put("type", type);

                    db.collection("Users").document(user.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                startActivity(new Intent(Registro.this, Login.class));
                                finish();
                            } else {
                                Toast.makeText(Registro.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(Registro.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
