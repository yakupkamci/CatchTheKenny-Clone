package com.yakupkamci.catchthekenny;

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
    TextView time;
    TextView score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        time = findViewById(R.id.textView);
        score = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        sc=0;

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        gosterme();
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Oyunu Tekrar Oynuycan mı?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Siktir Git :)",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

    }


    public void arttir(View view){
        sc++;
        score.setText("Score: "+ sc);
    }

    public void gosterme(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random rdm =new Random();
                int i = rdm.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }
}