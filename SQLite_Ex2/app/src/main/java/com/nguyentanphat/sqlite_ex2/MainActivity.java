package com.nguyentanphat.sqlite_ex2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyentanphat.adapters.ProductAdapter;
import com.nguyentanphat.models.Product;
import com.nguyentanphat.sqlite_ex2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Databases db;

    ProductAdapter adapter;

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        prepareDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_add);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPrice = dialog.findViewById(R.id.edtPrice);
                Button btnSave = dialog.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Insert data
                        String name = edtName.getText().toString();
                        double price = Double.parseDouble(edtPrice.getText().toString());
                        db.execSql("INSERT INTO " + db.TBL_NAME + " VALUES(null,'"+name +"',"+ price +")");
                        loadData();
                        dialog.dismiss();
                    }
                });
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    private void loadData() {
        adapter = new ProductAdapter(MainActivity.this,R.layout.item_list,getDataFromDb());
        binding.lvProduct.setAdapter(adapter);
    }

    private List<Product> getDataFromDb() {
        products = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + Databases.TBL_NAME);
        while(cursor.moveToNext()){
            products.add(new Product(cursor.getInt(0), cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();
        return  products;
    }

    private void prepareDb() {
        db = new Databases(this);
        db.createSampleData();
    }

    public void openEditDialog(Product p){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit);

        EditText edtName = dialog.findViewById(R.id.edtName);
        edtName.setText(p.getProductName());

        EditText edtPrice = dialog.findViewById(R.id.edtPrice);
        edtPrice.setText(String.valueOf(p.getProductPrice()));

        Button btnSave = dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());
                db.execSql("UPDATE " + Databases.TBL_NAME + " SET " + Databases.COL_NAME + "='" + name + "' , " + Databases.COL_PRICE + "=" + price + " WHERE " + Databases.COL_CODE + "=" + p.getProductCode());

                //Update Product SET ProductName = 'Heineken', ProductPrice = 19000 WHERE ProductCode = 1

                loadData();
                dialog.dismiss();
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        dialog.show();
    }

    public void openDeleteConfirmDialog(Product p){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xoá");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn có chắc chắn muốn xoá sản phẩm '" + p.getProductName() + "'?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSql("DELETE FROM " + Databases.TBL_NAME + " WHERE " + Databases.COL_CODE + "= " + p.getProductCode());
                loadData();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    //========Menu===========

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add);

            EditText edtName = dialog.findViewById(R.id.edtName);
            EditText edtPrice = dialog.findViewById(R.id.edtPrice);
            Button btnSave = dialog.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Insert data
                    String name = edtName.getText().toString();
                    double price = Double.parseDouble(edtPrice.getText().toString());
                    db.execSql("INSERT INTO " + db.TBL_NAME + " VALUES(null,'"+name +"',"+ price +")");
                    loadData();
                    dialog.dismiss();
                }
            });
            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}