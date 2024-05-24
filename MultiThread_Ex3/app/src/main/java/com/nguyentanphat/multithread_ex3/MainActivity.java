package com.nguyentanphat.multithread_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nguyentanphat.multithread_ex3.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        executorService = Executors.newSingleThreadExecutor();
        
        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                executeLongRunningTask();
            }
        });
    }

    private void executeLongRunningTask() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int numbOfViews = Integer.parseInt(binding.edtNumb.getText().toString());
                Random random = new Random();
                for (int i = 1; i <= numbOfViews; i++) {
//                    count = i - 1;
                    int percent = i * 100 / numbOfViews;//percent
                    int randNumb = random.nextInt(100);//random number
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Update UI
                            binding.txtPercent.setText(percent + " %");
                            binding.pbPercent.setProgress(percent);

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(15,15,15,15);

                            Button button = new Button(MainActivity.this);
                            button.setText(String.valueOf(randNumb));
                            button.setTextSize(24);
                            button.setTextColor(Color.WHITE);
                            if(randNumb % 2 == 0){
                                button.setBackgroundColor(Color.rgb(0,150,136));
                                params.gravity = Gravity.START;
                            }else {
                                button.setBackgroundColor(Color.rgb(156,0,20));
                                params.gravity = Gravity.END;
                            }
                            button.setLayoutParams(params);
                            binding.containerLayout.addView(button);
                            if (percent == 100){
                                binding.txtPercent.setText("Done");
                            }
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            });
        }


    @Override
    protected void onStop() {
        if(executorService != null){
            executorService.shutdownNow();
        }
        super.onStop();
    }
}