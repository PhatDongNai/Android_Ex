package com.nguyentanphat.sqlite_ex3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.nguyentanphat.databases.WarrantyDB;
import com.nguyentanphat.sqlite_ex3.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> launcher;

    public static WarrantyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == RESULT_OK && o.getData() != null){
                    Bitmap bitmap = (Bitmap) o.getData().getExtras().get("data");
                    binding.imvPhoto.setImageBitmap(bitmap);
                }
            }
        });

        db = new WarrantyDB(this);
        addEvents();
    }

    private void addEvents() {
        binding.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert
                String name =  binding.edtName.getText().toString();
                String des = binding.edtDesCription.getText().toString();

                boolean insert =  db.insertData(name, des, convertPhotot());

                if(insert){
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private byte[] convertPhotot() {
        BitmapDrawable drawable = (BitmapDrawable) binding.imvPhoto.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, outputStream);
        return  outputStream.toByteArray();
    }
}