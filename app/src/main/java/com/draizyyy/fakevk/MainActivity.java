package com.draizyyy.fakevk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.fakevk.databinding.CommentsActivityBinding;
import com.draizyyy.fakevk.databinding.ListItemBinding;

import com.draizyyy.fakevk.databinding.ActivityMainBinding;
import com.draizyyy.fakevk.databinding.ListItemBinding;
import com.draizyyy.fakevk.databinding.RepostsActivityBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<Post> adapter;



    private static final Post[] posts = new Post[6];
    public static int position;
    public static Post[] getPosts() {
        return posts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts[0] = new Post("Лицей 1523", "5 дек в 14:59", "⏰ Время сбора – с 9.00 до 9.30 на первом этаже (необходимо зарегистрироваться у стола на входе и узнать номер аудитории, к которой прикреплен обучающийся).", R.drawable._523, R.drawable.post1);
        posts[1] = new Post("Лицей 1523", "22 ноя в 13:17", "Уважаемые родители и абитуриенты!\n" +
                "\n" +
                "\uD83D\uDDD3 Напоминаем, что 24 ноября (четверг) состоится очередная встреча руководителя университетского лицея №1523 с родителями и обучающимися школ города Москвы.", R.drawable._523, R.drawable.post2);
        posts[2] = new Post("Лицей 1523", "9 ноя в 12:05", "ℹ С 12 ноября 2022 года на срок до 6 месяцев будет закрыт участок «Автозаводская» — «Орехово» Замоскворецкой линии метро (зеленая ветка).\n" +
                "\n" +
                "Будут запущены компенсационные маршруты автобусов \uD83D\uDE8C\n" +
                "\n" +
                "Пожалуйста, заранее продумайте маршрут до лицея, учитывая возможные затруднения!", R.drawable._523, R.drawable.post3);
        posts[3] = new Post("Brawl Stars", "5 мар в 17:50", "Друзья, хочу напомнить, что все игроки могут следить за играми любых регионов \uD83C\uDFC6Чемпионата Бравл Старс 2022 и зарабатывать месячные награды на сайте https://event.brawlstars.com/ru/\n" +
                "\n" +
                "Там же можно увидеть \uD83D\uDDD3расписание будущих финалов.", R.drawable.brawl_ico, R.drawable.post4);

        posts[4] = new Post("Лицей 1523", "вчера в 12:58", "Встреча с представителями ИЯФиТ НИЯУ МИФИ\n" +
                "\n" +
                "\uD83D\uDCC6 Когда: 20 декабря, вторник\n" +
                "⏰ Начало в 19:00\n" +
                "\n" +
                "\uD83D\uDC65 Для кого: 10-е и 11-е классы, родители.\n", R.drawable._523, R.drawable.post6);
        posts[5] = new Post("Мемзавод 1523", "26 ноя в 13:23", "По факту", R.drawable.memzavod_ico, R.drawable.post5);
        listView = findViewById(R.id.list_view);
        adapter = new MyPostAdapter(this, posts);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(adapter);
    }

    class MyPostAdapter extends ArrayAdapter<Post> {
        final Post[] posts;

        public MyPostAdapter(@NonNull Context context, Post[] posts) {
            super(context, R.layout.list_item);
            this.posts = posts;
        }

        private void change_like_photo(Post post, ListItemBinding binding) {
            if (post.isLicked()) {
                binding.likePhoto.setImageResource(R.drawable.like_pressed);
            } else {
                binding.likePhoto.setImageResource(R.drawable.like_not_pressed);
            }
        }


        @Override
        public int getCount() {
            return posts.length;
        }

        private void goToComments(int position) {
            Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
            intent.putExtra("position", String.valueOf(position));
            startActivity(intent);
        }
        @SuppressLint("ViewHolder")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Post post = posts[position];
            ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            change_like_photo(post, binding);

            binding.postPhoto.setImageResource(post.getImagePostPhotoId());
            binding.publicIco.setImageResource(post.getImageIcoId());

            binding.postText.setText(post.getText());
            binding.postTime.setText(post.getTime());
            binding.publicName.setText(post.getName());

            binding.postLikes.setText(post.getLikes());
            binding.postComments.setText(post.getComments());
            binding.postReposts.setText(post.getReposts());

            binding.likes.setOnClickListener(view -> {
                post.like();
                binding.postLikes.setText(post.getLikes());
                change_like_photo(post, binding);
                notifyDataSetInvalidated();
            });


            binding.comments.setOnClickListener(view -> {
                goToComments(position);
            });
            binding.postPhoto.setOnClickListener(view -> {
            });
            binding.post.setOnClickListener(view -> {
                goToComments(position);
            });

            binding.reposts.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, RepostsActivity.class);
                startActivity(intent);
            });
            binding.postPhoto.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, PostPhotoActivity.class);
                intent.putExtra("position", String.valueOf(position));
                startActivity(intent);
            });

            return binding.getRoot();
        }

    }
}