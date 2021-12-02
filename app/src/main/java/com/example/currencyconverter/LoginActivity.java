package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    final String EMAIL = "alex@gmail.com";
    final String PASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeScreenComponents();
    }

    private void InitializeScreenComponents() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        ResetScreen();
    }

    private void ResetScreen() {
        editTextEmail.setText(null);
        editTextPassword.setText(null);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonLogin) {
            Login();
        }
    }

    private void Login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "The E-mail cannot be empty!", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "The Password cannot be empty!", Toast.LENGTH_LONG).show();
        } else {
            if (email.equals(EMAIL)) {
                if (password.equals(PASSWORD)) {
                    OpenConvertionPage();
                } else {
                    Toast.makeText(this, "The Password is not correct!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "The E-mail is not correct!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void OpenConvertionPage() {
        Intent intent = new Intent(this, ConvertionActivity.class);
        startActivity(intent);
    }
}