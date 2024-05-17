package com.nguyentanphat.sqlite_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.nguyentanphat.adaptes.WarrantyAdapter;
import com.nguyentanphat.databases.WarrantyDB;
import com.nguyentanphat.models.Warranty;
import com.nguyentanphat.sqlite_ex3.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    WarrantyAdapter adapter;
    ArrayList<Warranty> warranties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData() {
        adapter = new WarrantyAdapter(MainActivity2.this,R.layout.item_list, loadDataFromDB());
        binding.lvWarranty.setAdapter(adapter);
    }

    private List<Warranty> loadDataFromDB() {
        warranties = new ArrayList<>();
        Cursor cursor = MainActivity.db.getData("SELECT * FROM " + WarrantyDB.TBL_NAME);
        if(cursor != null){
            while (cursor.moveToNext()){
                warranties.add(new Warranty(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getBlob(3)));
            }
            cursor.close();
        }
        return warranties;
    }
}