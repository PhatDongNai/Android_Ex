package com.example.b1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.b1.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Bạn chọn: ";
//                if (binding.chkFilm.isChecked())
//                    s += binding.chkFilm.getText().toString() + ", ";
//                if (binding.chkFPT.isChecked())
//                    s += binding.chkFPT.getText().toString() + ", ";
//                if (binding.chkClip.isChecked())
//                    s += binding.chkClip.getText().toString() + ", ";

                binding.txtVote.setText(s.substring(0, s.length()-2));
            }
        });
    }
}