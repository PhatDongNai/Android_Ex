package com.nguyentanphat.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.nguyentanphat.test.adapters.ProductAdapter;
import com.nguyentanphat.test.databinding.ActivityMain2Binding;
import com.nguyentanphat.test.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;

    ProductAdapter adapter;

    ArrayList<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        loadData();
    }

    private void loadData() {
        productList = new ArrayList<>();
        String json;
//        try {
//            InputStream is = getAssets().open("data.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//            JSONArray jsonArray = new JSONArray(json);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject categoryObject = jsonArray.getJSONObject(i);
//                JSONArray productArray = categoryObject.getJSONArray(categoryObject.keys().next());
//                for (int j = 0; j < productArray.length(); j++) {
//                    JSONObject itemObject = productArray.getJSONObject(j);
//                    String placeName = itemObject.getString("placeName");
//                    String dishName = itemObject.getString("dishName");
//                    String photo = itemObject.getString("photo");
//                    double ratingValue = itemObject.getDouble("ratingValue");
//                    String ratingCount = itemObject.getString("ratingCount");
//                    String address = itemObject.getString("address");
//                    productList.add(new Product(placeName, dishName, getResources().getIdentifier(photo, "drawable", getPackageName()), (float) ratingValue, ratingCount, address));
//                }
//            }
//            adapter.notifyDataSetChanged();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }

        productList.add(new Product("Trống đồng","Bún đậu",R.drawable.noodle_bun_dau_mam_tom,4.9f,"499+",null));

        adapter = new ProductAdapter(MainActivity2.this, R.layout.item_lv, productList);
        binding.lvProducts.setAdapter(adapter);
    }
}