package com.nguyentanphat.adaptes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentanphat.databases.WarrantyDB;
import com.nguyentanphat.models.Warranty;
import com.nguyentanphat.sqlite_ex3.MainActivity;
import com.nguyentanphat.sqlite_ex3.R;

import java.util.List;

public class WarrantyAdapter extends BaseAdapter {
    Context context;
    int item_layout;
    List<Warranty> warranties;

    //Constructor
    public WarrantyAdapter(Context context, int item_layout, List<Warranty> warranties) {
        this.context = context;
        this.item_layout = item_layout;
        this.warranties = warranties;
    }

    @Override
    public int getCount() {
        return warranties.size();
    }

    @Override
    public Object getItem(int position) {
        return warranties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);

            //linking views
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtDes = convertView.findViewById(R.id.txtDescription);
            holder.imvPhoto = convertView.findViewById(R.id.imvPhoto);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Binding
        Warranty w = warranties.get(position);
        holder.txtName.setText(w.getWarrantyName());
        holder.txtDes.setText(w.getGetWarrantyDescription());

        //Convert byte arr --> bitmap
        byte[] photo = w.getWarrantyPhoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);
        holder.imvPhoto.setImageBitmap(bitmap);
        return convertView;
    }
    public static class ViewHolder{
        ImageView imvPhoto;
        TextView txtName, txtDes;
    }
}
