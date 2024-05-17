package com.nguyentanphat.sharepreferences_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nguyentanphat.sharepreferences_ex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static  final  String PREFERENCES_NAME = "my_data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                //attach data
                editor.putInt("numb",89);
                editor.putFloat("price",19000);
                editor.putBoolean("checked",true);
                editor.putString("say","hello");
                editor.apply();
            }
        });

        binding.btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
                int numb = preferences.getInt("numb",0);
                float price = preferences.getFloat("price",0.0f);
                boolean checked = preferences.getBoolean("checked",false);
                String say = preferences.getString("say","");

                binding.txtContent.setText("");
                binding.txtContent.append("Number: " + numb + "\n");
                binding.txtContent.append("Price: " + price + "\n");
                binding.txtContent.append("Checked: " + checked + "\n");
                binding.txtContent.append("Say: " + say + "\n");
            }
        });
    }
}