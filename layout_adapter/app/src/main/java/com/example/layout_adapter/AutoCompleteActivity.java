package com.example.layout_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.layout_adapter.databinding.ActivityAutoCompleteBinding;

import java.util.ArrayList;

public class AutoCompleteActivity extends AppCompatActivity {

    ActivityAutoCompleteBinding binding;

    ArrayAdapter<String> adapter ;
    ArrayList<String> Food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutoCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {
        adapter = new ArrayAdapter<>(AutoCompleteActivity.this, android.R.layout.simple_list_item_1);

        String[] dsMon = {"Cơm tấm","Phở","Bún thịt nướng","Chè"};
        adapter.addAll(dsMon);
        binding.txtMon.setAdapter(adapter);
    }
}