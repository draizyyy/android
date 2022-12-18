package com.draizyyy.fakevk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.fakevk.databinding.PostPhotoActivityBinding;

public class PostPhotoActivity extends AppCompatActivity{
    private PostPhotoActivityBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PostPhotoActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Post post = MainActivity.getPosts()[Integer.parseInt(getIntent().getStringExtra("position"))];
        binding.ico.setImageResource(post.getImagePostPhotoId());
        setContentView(view);
    }
}
