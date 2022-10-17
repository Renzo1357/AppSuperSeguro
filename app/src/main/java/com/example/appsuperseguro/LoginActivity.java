package com.example.appsuperseguro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    AdminSQLiteOpenHelper db;
    private EditText etEmail, etPassword;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    public void Login(View view) {

        db = new AdminSQLiteOpenHelper(this);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(email.equals("")||password.equals(""))
            Toast.makeText(this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkUser = db.checkUser(email,password);
            if(checkUser == true){
                Toast.makeText(LoginActivity.this, "Inicio sesión con exito", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
            }
        }

    }
}