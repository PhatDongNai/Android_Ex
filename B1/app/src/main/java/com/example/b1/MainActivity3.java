package com.example.b1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.b1.databinding.ActivityMain2Binding;
import com.example.b1.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents(){
        binding.btnChangeImage.setOnClickListener(view -> {
            Toast.makeText(MainActivity3.this,"da clicke",Toast.LENGTH_LONG).show();
            if(binding.imvPhoto.getTag()==null || binding.imvPhoto.getTag() == "microsoft"){
                binding.imvPhoto.setImageResource(R.drawable.googleicon);
                binding.imvPhoto.setTag("google");
            } else if(binding.imvPhoto.getTag() == "google"){
                binding.imvPhoto.setImageResource(R.drawable.microsofticon);
                binding.imvPhoto.setTag("microsoft");
            }
        });

        binding.btnClose.setOnClickListener(view -> {

        });
    }
}