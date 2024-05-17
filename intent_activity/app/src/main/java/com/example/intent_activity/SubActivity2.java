package com.example.intent_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intent_activity.databinding.ActivitySub2Binding;
public class SubActivity2 extends AppCompatActivity {

    ActivitySub2Binding binding;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySub2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numb = Integer.parseInt(binding.txtNumber.getText().toString());
                double pow = Math.pow(numb,2);
                intent.putExtra("result", pow);
                setResult(RESULT_OK,intent);
                finish();// close activity
            }
        });
    }

    private void getData() {
        intent = getIntent();
        binding.txtNumber.setText(intent.getStringExtra("numb"));

    }
}