package com.example.registrationapp;

import static  com.example.registrationapp.Constants.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registrationapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.button.setOnClickListener(this);
        Log.d(TAG, "MainActivity created");
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "Open camera button has been pressed");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, OPEN_CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "request code = " + requestCode + " resultCode = " + resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == OPEN_CAMERA_REQUEST_CODE) {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap thePic = extras.getParcelable("data");
                    binding.image.setImageBitmap(thePic);
                    Log.d(TAG, "Photo was inserted");
                } else {
                    Log.wtf(TAG, "data == null");
                }
                Log.e(TAG, "onActivityResult: " + data);
            }
        }
    }
}