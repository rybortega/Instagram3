package com.example.parsagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.parsagram.R;
import com.example.parsagram.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostDetailsActivity extends AppCompatActivity {
    public TextView tvUsername;
    public TextView tvDescription;
    public ImageView ivPostImage;
    public TextView tvTimestamp;
    public Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        ivPostImage = findViewById(R.id.ivPostImage);
        tvTimestamp = findViewById(R.id.tvTimestamp);

        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());

        String time = post.getCreatedAt().toString();
        tvTimestamp.setText(getRelativeTimeAgo(time));
        ParseFile image = post.getImage();
        if(image != null){
            Glide.with(this)
                    .load(image.getUrl())
                    .into(ivPostImage);
        }

    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}