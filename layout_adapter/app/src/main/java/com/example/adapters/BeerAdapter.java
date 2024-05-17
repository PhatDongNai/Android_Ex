package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout_adapter.R;
import com.example.models.Beer;

import java.util.ArrayList;

public class BeerAdapter extends BaseAdapter {

    Activity activity;
    int item_layout;
    ArrayList<Beer> beers;

    //contructor
    public BeerAdapter(Activity activity, int item_layout, ArrayList<Beer> beers) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.beers = beers;
    }

    //trả về lượng item
    @Override
    public int getCount() {
        return beers.size();
    }

    //lấy item vị trí thứ
    @Override
    public Object getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //2 nhiệm vụ: ánh xạ tới các view đã thiết kế, khi liên kết rồi thì binding đổ dữ liệu lên
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout,null);
            //Link item view
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);

            // để không phải gọi lần 2
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //Binding
        Beer b = beers.get(position);
        holder.imvThumb.setImageResource(b.getBeerThumb());
        holder.txtName.setText(b.getName());
        holder.txtPrice.setText(String.valueOf(b.getPrice()));

        return view;
    }

    public class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice;
    }
}
