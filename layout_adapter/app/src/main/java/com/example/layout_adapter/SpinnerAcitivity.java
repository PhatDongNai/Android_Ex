package com.example.layout_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.example.layout_adapter.databinding.ActivitySpinnerAcitivityBinding;

import java.util.ArrayList;

public class SpinnerAcitivity extends AppCompatActivity {

    ActivitySpinnerAcitivityBinding binding;
    //Init data
    ArrayAdapter <String> adapter ;
    ArrayList<String> Food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerAcitivityBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {


        adapter = new ArrayAdapter<>(SpinnerAcitivity.this,android.R.layout.simple_spinner_dropdown_item);
        Food = new ArrayList<>();
        adapter.add("Bún Cá");
        adapter.add("Bún Cá");
        adapter.add("Bún Cá");
        adapter.add("Bún Cá");
        adapter.add("Bún Cua");

        binding.spMonAn.setAdapter((SpinnerAdapter) adapter);
    }
}