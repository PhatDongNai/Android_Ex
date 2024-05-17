package com.nguyentanphat.broadcastreceiver_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nguyentanphat.broadcastreceiver_ex2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}