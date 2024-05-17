package com.nguyentanphat.sqlite_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nguyentanphat.AddActivity;
import com.nguyentanphat.models.Product;
import com.nguyentanphat.sqlite_ex.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;
    Intent intent;

    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update data
                ContentValues values = new ContentValues();
                values.put(MainActivity.COL_NAME,binding.edtName.getText().toString());
                values.put(MainActivity.COL_PRICE,Double.parseDouble(binding.edtPrice.getText().toString()));

                int numberOfRows = MainActivity.db.update(MainActivity.TBL_NAME,values,MainActivity.COL_CODE + "=?",new String[]{String.valueOf(p.getProductCode())} );
                if(numberOfRows > 0){
                    Toast.makeText(EditActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        intent = getIntent();
        p = (Product) intent.getSerializableExtra("data");
        binding.edtName.setText(p.getProductName());
        binding.edtPrice.setText(String.valueOf(p.getGetProductPrice()));
    }
}