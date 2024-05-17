package com.nguyentanphat.sqlite_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nguyentanphat.AddActivity;
import com.nguyentanphat.models.Product;
import com.nguyentanphat.sqlite_ex.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static  final String DB_NAME = "product_db.db";
    public static final String DB_FOLDER = "databases";

    public static final String TBL_NAME = "Product";
    public static final String COL_CODE = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";


    public static SQLiteDatabase db;

    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;

    Product seletedProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        copyDB();
        openDB();
        addEvents();
        registerForContextMenu(binding.lvProducts);
    }

    private void addEvents() {
        binding.lvProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                seletedProduct = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }

    private void openDB() {
        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
    }

    private void loadData() {
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,getDataFromDB());
        binding.lvProducts.setAdapter(adapter);
    }

    private ArrayList<Product> getDataFromDB() {
        products = new ArrayList<>();
        //Select data

        //method #1 using rawQuery
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_CODE + ">?",new String[]{"2"});
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_NAME + " LIKE ?",new String[]{"%H%"});

        //method #1 using query()
        Cursor cursor = db.query(TBL_NAME,null,null,null,null,null,null);
//        Cursor cursor = db.query(TBL_NAME,null,COL_NAME + " LIKE ?",new String[]{"%h%"},null,null,null);
        while(cursor.moveToNext()){
            products.add(new Product(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();
        return products;
    }

    private void copyDB() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()) {
            if(processCopy())
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean processCopy() {
        String dbPath = getApplicationInfo().dataDir + "/"+DB_FOLDER+"/" + DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + "/"+DB_FOLDER+"/");
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush(); outputStream.close(); inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //==================Menu=================//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnEdit){
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            if(seletedProduct != null){
                intent.putExtra("data",seletedProduct);
                startActivity(intent);
            }

        } else if (item.getItemId() == R.id.mnDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Xác nhận xoá");
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setMessage("Bạn có chắc chắn muốn xoá sản phẩm '" + seletedProduct.getProductName() + "'?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Delete Product
                    int deleteRows = db.delete(TBL_NAME,COL_CODE + "=?", new String[]{String.valueOf(seletedProduct.getProductCode())});
                    if(deleteRows > 0){
                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        loadData();
                    }else{
                        Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }
}