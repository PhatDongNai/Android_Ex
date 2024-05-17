package com.example.intent_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intent_activity.databinding.ActivitySub1Binding;
import com.example.models.Product;

public class SubActivity1 extends AppCompatActivity {

    ActivitySub1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySub1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData() {
        Intent intent = getIntent();

        //M1 :receiving data using get[Typedata]extra
//        int numb = intent.getIntExtra("numb",0);
//        float grades = intent.getFloatExtra("grades",0.0f);
//        boolean checked = intent.getBooleanExtra("checked",false);
//        String say = intent.getStringExtra("say");


        //M1 :receiving data using getBundle]extra

        Bundle bundle = intent.getBundleExtra("data");
        assert bundle !=null;
        int numb = bundle.getInt("numb",0);
        float grades = bundle.getFloat("grades",0.0f);
        boolean checked = bundle.getBoolean("checked",false);
        String say = bundle.getString("say");

        Product p = (Product) bundle.getSerializable("productInfo");

        //showing data
        binding.txtContent.setText("");
        binding.txtContent.append("Numb: " + numb + "\n");
        binding.txtContent.append("Grades: " + grades + "\n");
        binding.txtContent.append("Checked: " + checked + "\n");
        binding.txtContent.append("Say: " + say + "\n");
        assert p != null;
        binding.txtContent.append("Product: " + p.getProductPrice() + "-" + p.getProductName());
    }
}