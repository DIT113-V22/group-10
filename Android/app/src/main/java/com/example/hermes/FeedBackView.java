package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hermes.ui.home.HomeFragment;

public class FeedBackView extends AppCompatActivity {

    private static final String TAG = "feedBackView";

    private Button submit;
    private RatingBar ratingStars;
    private EditText textView;
    private Button back;

    private String comment;
    private int rateNo;

    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        Log.d(TAG, "onCreate: Starting.");


        submit = findViewById(R.id.submit);
        ratingStars = findViewById(R.id.ratingStars);
        textView = findViewById(R.id.commentText);
        back = findViewById(R.id.backButtonF);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(view.getContext(), HomeScreen.class);
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

        // and here we'll save the created feedback in our db.


        Intent home = new Intent(this, HomeFragment.class);
        startActivity(home);
    }
}