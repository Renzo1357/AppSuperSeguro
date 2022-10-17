package com.example.appsuperseguro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    AdminSQLiteOpenHelper db;
    EditText etName, etEmail, etPhone, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText)findViewById(R.id.etNameFull);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etPassword = (EditText)findViewById(R.id.etPassword);

    }

    public void ToReturn(View view) {
        finish();
    }

    public void Register(View view) {

        db = new AdminSQLiteOpenHelper(this);
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        if(name.equals("")||email.equals("")||phone.equals("")||password.equals(""))
            Toast.makeText(RegisterActivity.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkEmail = db.checkEmail(email);
            if(checkEmail == false){
                Boolean insert = db.insertData(name, email, phone, password);
                if(insert == true){
                    Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(RegisterActivity.this, "Correo electronico ya existe", Toast.LENGTH_SHORT).show();
            }
        }

    }
}