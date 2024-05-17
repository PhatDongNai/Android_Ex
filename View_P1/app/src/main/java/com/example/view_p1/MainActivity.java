package com.example.view_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.view_p1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Bạn chọn: ";
                if (binding.chkFilm.isChecked())
                    s += binding.chkFilm.getText().toString() + ", ";
                if (binding.chkFPT.isChecked())
                    s += binding.chkFPT.getText().toString() + ", ";
                if (binding.chkClip.isChecked())
                    s += binding.chkClip.getText().toString() + ", ";

                binding.txtVote.setText(s.substring(0, s.length()-2));
            }
        });
    }
}