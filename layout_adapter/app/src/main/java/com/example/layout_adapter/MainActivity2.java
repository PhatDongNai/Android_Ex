package com.example.layout_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.layout_adapter.databinding.ActivityMain2Binding;
import com.example.models.Product;

import java.util.jar.Attributes;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ArrayAdapter<Product> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        adapter = new ArrayAdapter<>(MainActivity2.this,android.R.layout.simple_list_item_1);
        binding.lvProduct.setAdapter(adapter);
    }

    private void addEvents() {
        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Init data
                String name = binding.edtName.getText().toString();
                double price = Double.parseDouble(binding.edtPrice.getText().toString());
                Product p = new Product(name,price);

                adapter.add(p);
            }
        });

        binding.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapter.getItem(position);
                Toast.makeText(MainActivity2.this, selectedProduct.getProductName() + " - " + selectedProduct.getProductPrice(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapter.getItem(position);
                adapter.remove(selectedProduct);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}