package com.nguyentanphat.assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.nguyentanphat.assets.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFonts();
        addEvents();
    }

    private void addEvents() {
        binding.lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/" + adapter.getItem(position));
                binding.txtContent.setTypeface(tf);
                playSound();
            }
        });
    }

    private void playSound() {
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("musics/ting.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            descriptor.close();
            player.prepare();
            player.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFonts() {
        AssetManager manager = getAssets();
        try {
            String[] fontList = manager.list("fonts");
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,fontList);
            binding.lvFonts.setAdapter(adapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}