package com.example.registrationapp;

import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrationapp.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(this);
        Log.d(TAG, "RegistrationActivity created");
    }
    private void clearFields() {
        Log.i(TAG, "Fields were cleared");
        binding.email.setText("");
        binding.password.setText("");
    }
    private String getTextValue(EditText binding) {
        return binding.getText().toString();
    }
    private void logIn() {
        Log.v(TAG, binding.email + " " + binding.password);
        Log.d(TAG, "Opening MainActivity");
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.putExtra("name", getTextValue(binding.email));
        startActivity(intent);
        finish();
    }
    private void clearErrors() {
        Log.i(TAG, "Error field has been cleared");
        binding.error.setText("");
        binding.errorExtended.setText("");
    }
    @Override
    public void onClick(View view) {
        Log.i(Constants.TAG, "Input button has been pressed");
        clearErrors();
        if (getTextValue(binding.email).equals("rickroll@gmail.com") && getTextValue(binding.password).equals("RickAstley")) {
            logIn();
        }
        else if (!(getTextValue(binding.email).contains("@")) || !(getTextValue(binding.email).contains("."))) {
            Log.i(TAG, "Wrong email format");
            binding.error.setText("Неверный формат почты");
            clearFields();
        }
        else if (getTextValue(binding.password).length() < 9) {
            Log.i(TAG, "Wrong password format");
            binding.error.setText("Пароль должен быть больше");
            binding.errorExtended.setText("восьми символов");
            binding.password.setText("");
        }
        else if (getTextValue(binding.email).equals("rickroll@gmail.com") && !(getTextValue(binding.password).equals("RickAstley"))) {
            Log.i(TAG, "Wrong password");
            binding.error.setText("Неверный пароль");
            binding.password.setText("");
        }
        else {
            logIn();
        }
    }
}