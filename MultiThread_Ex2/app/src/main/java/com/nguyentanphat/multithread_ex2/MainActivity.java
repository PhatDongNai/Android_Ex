package com.nguyentanphat.multithread_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nguyentanphat.multithread_ex2.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int percent;
    int randNumb;

    int count = 0;
    int flag;
    LinearLayout currentRow;

    Random random = new Random();
    Handler handler = new Handler();

    //Main/UI -Thread
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            //Update UI
            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15,15,15,15);

            Button button = new Button(MainActivity.this);
            button.setLayoutParams(params);
            button.setText(String.valueOf(randNumb));
            button.setTextSize(24);
            button.setTextColor(Color.WHITE);

            if(randNumb % 2 == 0){
                button.setBackgroundColor(Color.rgb(0,150,136));
            }else {
                button.setBackgroundColor(Color.rgb(156,0,20));
            }

            if(flag == 0){
                //Câu 1
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenWidth = displayMetrics.widthPixels;

                int halfScreenWidth = screenWidth / 2;
                if(count % 2 == 0){
                    params.gravity = Gravity.START;
                    params.width =  halfScreenWidth - 40;
                }else{
                    params.gravity = Gravity.END;
                    params.width =  halfScreenWidth - 40;
                }
//                count++;
                binding.containerLayout.addView(button);
            }else if (flag == 1){
                //Câu 2
                if (count % 2 == 0) {
                    currentRow = new LinearLayout(MainActivity.this);
                    currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    currentRow.setOrientation(LinearLayout.HORIZONTAL);
                    binding.containerLayout.addView(currentRow);
                }
                if ((count / 2) % 2 == 0) { // Even rows (0, 2, 4, ...)
                    if (count % 2 == 0) {
                        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
                    } else {
                        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
                    }
                } else { // Odd rows (1, 3, 5, ...)
                    if (count % 2 == 0) {
                        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
                    } else {
                        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
                    }
                }
                params.setMargins(15, 15, 15, 15);
                button.setLayoutParams(params);

                currentRow.addView(button);
//                count++;
            }else{
                binding.containerLayout.addView(button);
            }

            if (percent == 100){
                binding.txtPercent.setText("Done");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                binding.containerLayout.removeAllViews();
                execBackgroundThread();
            }
        });

        binding.btnDrawOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                binding.containerLayout.removeAllViews();
                execBackgroundThread();
            }
        });

        binding.btnDrawOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                binding.containerLayout.removeAllViews();
                execBackgroundThread();
            }
        });
    }

    private void execBackgroundThread() {
        //Background/Worker - Thread
        int numbOfViews = Integer.parseInt(binding.edtNumb.getText().toString());
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=numbOfViews; i++){
                    count = i - 1;
                    percent = i*100/numbOfViews;//percent
                    randNumb = random.nextInt(100);//random number
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }
}