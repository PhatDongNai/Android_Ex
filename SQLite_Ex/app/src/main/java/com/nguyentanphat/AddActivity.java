package com.nguyentanphat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nguyentanphat.sqlite_ex.MainActivity;
import com.nguyentanphat.sqlite_ex.R;
import com.nguyentanphat.sqlite_ex.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert data
                ContentValues contentValues = new ContentValues();
                contentValues.put(MainActivity.COL_NAME,binding.edtName.getText().toString());
                contentValues.put(MainActivity.COL_PRICE,Double.parseDouble(binding.edtPrice.getText().toString()));

                long numberOfRows = MainActivity.db.insert(MainActivity.TBL_NAME,null,contentValues);

                if(numberOfRows > 0){
                    Toast.makeText(AddActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddActivity.this,"Fail",Toast.LENGTH_SHORT).show();
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
}