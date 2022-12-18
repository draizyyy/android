package com.draizyyy.fakevk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.draizyyy.fakevk.databinding.RepostsActivityBinding;

public class RepostsActivity extends AppCompatActivity {
    private RepostsActivityBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RepostsActivityBinding.inflate(getLayoutInflater());
        binding.tryAgainRepost.setOnClickListener(view -> {
            Intent intent = new Intent(RepostsActivity.this, RepostsActivity.class);
            startActivity(intent);
            finish();
        });
        View view = binding.getRoot();
        setContentView(view);

    }


}
