package com.nguyentanphat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentanphat.models.Product;
import com.nguyentanphat.sqlite_ex2.MainActivity;
import com.nguyentanphat.sqlite_ex2.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    MainActivity context;
    int item_layout;
    List<Product> products;

    public ProductAdapter(MainActivity context, int item_layout, List<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    //constructor
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);

            //linking views
            viewHolder.txtInfo = convertView.findViewById(R.id.txtInfor);
            viewHolder.imvEdit = convertView.findViewById(R.id.imvEdit);
            viewHolder.imvDelete = convertView.findViewById(R.id.imvDelete);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Binding
        Product p = products.get(position);
        viewHolder.txtInfo.setText(p.getProductName() + " - " + String.format("%.0f đ", p.getProductPrice()));
        viewHolder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edit
                context.openEditDialog(p);
            }
        });
        viewHolder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete
                context.openDeleteConfirmDialog(p);
            }
        });
        return convertView;
    }

    public static class ViewHolder{
        TextView txtInfo;
        ImageView imvEdit, imvDelete;
    }
}
