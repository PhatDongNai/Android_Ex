package com.example.gridview.layout_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gridview.R;
import com.example.gridview.adapters.BeerAdapter;
import com.example.gridview.databinding.ActivityMainBinding;
import com.example.gridview.models.Beer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BeerAdapter adapter;

    ArrayList<Beer> beers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        addEvents();
    }

    private void loadData() {
        //init
        beers = new ArrayList<>();
        beers.add(new Beer(R.drawable.heineken, "Heineken", 1900000));
        beers.add(new Beer(R.drawable.tiger, "Tiger", 800000));
        beers.add(new Beer(R.drawable.hanoi, "Ha Noi", 900000));
        beers.add(new Beer(R.drawable.beer333, "333", 200000));
        beers.add(new Beer(R.drawable.sapporo, "Saporo", 500000));
        beers.add(new Beer(R.drawable.saigon, "Sai Gon", 100000));
        beers.add(new Beer(R.drawable.larue, "Larue", 200000));

        adapter = new BeerAdapter(MainActivity.this,R.layout.gridview_item,beers);
        binding.gvBeer.setAdapter(adapter);
    }

    private void addEvents() {
        binding.gvBeer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.detail);

                Beer selectedBeer = (Beer) adapter.getItem(position);
                ImageView imvPhoto = dialog.findViewById(R.id.imvThumb);
                imvPhoto.setImageResource(selectedBeer.getBeerThumb());

                TextView txtName = dialog.findViewById(R.id.txtName);
                txtName.setText(selectedBeer.getName());

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }
}