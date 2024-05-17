package com.example.noodleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.noodleapp.adapter.NoodleAdapter;
import com.example.noodleapp.models.Noodle;

import java.util.ArrayList;

public class NoodleActivity extends AppCompatActivity {
    ListView lv_noodle;
    ImageButton img_btn_back;
    NoodleAdapter noodleAdapter;
    ArrayList<Noodle> noodles;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noodle);

        img_btn_back=findViewById(R.id.img_btn_back);
        lv_noodle= findViewById(R.id.lv_noodle);
        loadData();
        addEvent();

        img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addEvent(){
        lv_noodle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(NoodleActivity.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.custom_dialog);
                ImageView img_thumb = dialog.findViewById(R.id.img_thumb);
                TextView txt_infor = dialog.findViewById(R.id.txt_infor);
                TextView txt_star = dialog.findViewById(R.id.txt_star);
                TextView txt_distance = dialog.findViewById(R.id.txt_distance);

                //TRUYỀN TÊN BIA VÀ ẢNH VÀO DIALOG
                NoodleAdapter.ViewHolder b = (NoodleAdapter.ViewHolder) view.getTag();
                txt_infor.setText(b.getTxt_subtitle().getText());
                txt_star.setText(b.getTxt_star().getText());
                txt_distance.setText(b.getTxt_distance().getText());
                img_thumb.setImageDrawable(b.getImvThumb().getDrawable());

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                //dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
    }

    private void loadData() {

        noodles = new ArrayList<>();
        noodles.add(new Noodle(R.drawable.bundau, "449 Lê Văn Việt", "Bún đậu mẹt", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bundaumet, "449 Lê Văn Việt", "Bún đậu mẹt", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bunquay, "449 Lê Văn Việt", "Bún quậy", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bunthitnuong, "449 Lê Văn Việt", "Bún thịt nướng", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bunca, "449 Lê Văn Việt", "Bún cá", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.buncha, "449 Lê Văn Việt", "Bún chả", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bundaumet, "449 Lê Văn Việt", "Bún đậu ngon", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.bunmoc, "449 Lê Văn Việt", "Bún mọc", "4.9", "1.2km"));
        noodles.add(new Noodle(R.drawable.buncha, "449 Lê Văn Việt", "Bún chả Hà Nội", "4.9", "1.2km"));
        noodleAdapter = new NoodleAdapter(NoodleActivity.this, R.layout.lv_layout_item, noodles);

        lv_noodle.setAdapter(noodleAdapter);
    }

}