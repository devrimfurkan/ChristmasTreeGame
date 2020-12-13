package com.dfg.christmastreegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView badGuy;
    ImageView boxes1;
    ImageView socks1;
    ImageView cookie;
    ImageView noelDad;
    ImageView badGuy1;
    ImageView socks;
    ImageView cookie1;
    ImageView boxes2;

    Handler handler;
    Runnable runnable;
    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText=findViewById(R.id.scoreText);
        timeText=findViewById(R.id.timeText);

        badGuy=findViewById(R.id.badGuy);
        badGuy1=findViewById(R.id.badGuy1);
        boxes1=findViewById(R.id.boxes1);
        boxes2=findViewById(R.id.boxes2);
        socks=findViewById(R.id.socks);
        socks1=findViewById(R.id.socks1);
        cookie=findViewById(R.id.cookie);
        cookie1=findViewById(R.id.cookie1);
        noelDad=findViewById(R.id.noelDad);

        imageArray=new ImageView[]{badGuy,badGuy1,boxes1,boxes2,socks,socks1,cookie,cookie1,noelDad};

        hideImage();
        score=0;

        new CountDownTimer(15000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off!");
                handler.removeCallbacks(runnable);

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Do you want to play again?");
                alert.setMessage("Are you sure to play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG);
                    }
                });
                alert.show();
            }
        }.start();
    }

    private void hideImage() {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);

    }

    public void increaseScore(View view){
        if (imageArray!=null){
            score++;
            scoreText.setText("Score: "+score);
        }

    }
}