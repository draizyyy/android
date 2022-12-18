package com.draizyyy.fakevk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.draizyyy.fakevk.databinding.CommentsActivityBinding;


public class CommentsActivity extends AppCompatActivity {
    private CommentsActivityBinding binding;
    private void change_like_photo(Post post) {
        if (post.isLicked()) {
            binding.post.likePhoto.setImageResource(R.drawable.like_pressed);
        } else {
            binding.post.likePhoto.setImageResource(R.drawable.like_not_pressed);
        }
    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CommentsActivityBinding.inflate(getLayoutInflater());

        Post post = MainActivity.getPosts()[Integer.parseInt(getIntent().getStringExtra("position"))];

        change_like_photo(post);

        binding.post.postText.setText(post.getText());
        binding.post.postTime.setText(post.getTime());
        binding.post.publicName.setText(post.getName());

        binding.post.postLikes.setText(post.getLikes());
        binding.post.postComments.setText(post.getComments());
        binding.post.postReposts.setText(post.getReposts());
        binding.post.postPhoto.setImageResource(post.getImagePostPhotoId());


        binding.post.likes.setOnClickListener(view -> {
            post.like();
            binding.post.postLikes.setText(post.getLikes());
            change_like_photo(post);

        });
        binding.tryAgain.setOnClickListener(view -> {
            Intent intent = new Intent(CommentsActivity.this, CommentsActivity.class);
            intent.putExtra("position", String.valueOf(getIntent().getStringExtra("position")));
            startActivity(intent);
            finish();
        });
        binding.post.reposts.setOnClickListener(view -> {
            Intent intent = new Intent(CommentsActivity.this, RepostsActivity.class);
            startActivity(intent);
        });

        binding.post.postPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(CommentsActivity.this, PostPhotoActivity.class);
            intent.putExtra("position", String.valueOf(getIntent().getStringExtra("position")));
            startActivity(intent);
        });

        View view = binding.getRoot();
        setContentView(view);

    }


}
