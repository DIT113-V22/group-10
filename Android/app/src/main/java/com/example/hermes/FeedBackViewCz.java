package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class FeedBackViewCz extends AppCompatActivity {

    private static final String TAG = "feedBackView";

    private Button submit;
    private RatingBar ratingStars;
    private EditText textView;
    private Button back;

    private String comment;
    private int rateNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_view_cz);
    Log.d(TAG, "onCreate: Starting.");


    submit = findViewById(R.id.submit);
    ratingStars = findViewById(R.id.ratingStars);
    textView = findViewById(R.id.commentText);
    back = findViewById(R.id.backButtonF);


        back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent home = new Intent(view.getContext(), HomeScreenCz.class);
            startActivity(home);
        }
    });

        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            rateNo = (int) ratingBar.getRating();
        }
    });

        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submit();
        }
    });
}

    public void submit() {
        comment = textView.getText().toString();
        FeedBack mFeedBack = new FeedBack(comment, rateNo);

        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.storeFeedback(mFeedBack);

        Intent home = new Intent(this, HomeScreenCz.class);
        startActivity(home);
    }
}