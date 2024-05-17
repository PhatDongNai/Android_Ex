package com.example.intent_activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.intent_activity.databinding.ActivityMainBinding;
import com.example.models.Product;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("MainActivity","onCreate");

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                double res = result.getData().getDoubleExtra("result",0.0f);
                binding.txtResult.setText(String.valueOf(res));
            }
        });
        addEvents();
    }

    private void addEvents() {
        binding.btnOpenAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Activity2
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);
            }
        });

        binding.btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity1.class);
                //attach data
                //M1 : Using putExtra(key,data)
//                intent.putExtra("numb",89);
//                intent.putExtra("grades",8.9f);
//                intent.putExtra("checked",true);
//                intent.putExtra("say","Hello");

                //M2: using Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("numb",68);
                bundle.putFloat("grades",9.0f);
                bundle.getBoolean("checked",true);
                bundle.putString("say","Welcome");

                Product p = new Product(1,"Heineken",190000);
                bundle.putSerializable("productInfo", p);

                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

        binding.btnSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity2.class);
                //Attach data
                intent.putExtra("numb",binding.edtNumber.getText().toString());
                //startActivity(intent);
                launcher.launch(intent);
            }
        });
    }
}