package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }


    private void addEvents(){
        binding.btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show toast
                Toast.makeText(MainActivity.this, "this is a message", Toast.LENGTH_LONG).show();
            }
        });

        binding.btnShowDefaultDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show Default Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận thoát ứng dụng");
                builder.setMessage("Bạn có chắc chắn muốn thoát?");
                builder.setIcon(android.R.drawable.ic_dialog_info);

                // action dialog
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // close app
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close dialog
                        dialogInterface.dismiss();
                    }
                });

                Dialog dialog =  builder.create();
                // Ngăn không cho người dùng click vào bên ngoài dialog
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        binding.btnShowCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.custom_dialog);

                ImageView imageVOk = dialog.findViewById(R.id.imgvOk);

                imageVOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

                ImageView imageVCancel = dialog.findViewById(R.id.imgvCancel);

                imageVCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);

                dialog.show();
            }
        });

        binding.btnShowBottomSheet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.bottom_sheet_layout);


                LinearLayout bsCamera = dialog.findViewById(R.id.Camera);
                bsCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "this is a bsCamera", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                LinearLayout bsGal = dialog.findViewById(R.id.Gallery);
                bsGal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "this is a bsGal", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

            }
        });
    }
}