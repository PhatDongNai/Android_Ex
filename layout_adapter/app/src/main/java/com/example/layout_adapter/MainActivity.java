package com.example.layout_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.layout_adapter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.lvDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this,"Item - click" + adapter.getItem(i),Toast.LENGTH_SHORT).show();
            }
        });
        binding.lvDrinks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this,"Item -long click" + adapter.getItem(i),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void loadData() {
//        String[] drinks = {"Coca","Pepsi","Sting","Tiger","Heineken","Milk tea","Warrior","Red-bull","C2","Beer 33","Lavie","Coca","Pepsi","Sting","Tiger","Heineken","Milk tea","Warrior","Red-bull","C2","Beer 33","Lavie"};
        String[] foods = getResources().getStringArray(R.array.foodList);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,foods);

        binding.lvDrinks.setAdapter(adapter);
    }
}