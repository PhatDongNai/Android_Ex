package com.nguyentanphat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentanphat.models.Tours;
import com.nguyentanphat.sqlitetest.MainActivity;
import com.nguyentanphat.sqlitetest.R;

import java.util.List;

public class TourAdapter extends BaseAdapter {

    MainActivity context;
    int item_layout;
    List<Tours> tours;

    public TourAdapter(MainActivity context, int item_layout, List<Tours> tours) {
        this.context = context;
        this.item_layout = item_layout;
        this.tours = tours;
    }
    @Override
    public int getCount() {
        return tours.size();
    }

    @Override
    public Tours getItem(int position) {
        return tours.get(position);
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
        Tours p = tours.get(position);
        viewHolder.txtInfo.setText(p.getTourName() + " - " + String.format("%.0f đ", p.getTourPrice())+ " - " + p.getTourDescription() + " - " + p.getPhoto());
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
