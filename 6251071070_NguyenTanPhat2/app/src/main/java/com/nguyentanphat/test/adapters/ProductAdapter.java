package com.nguyentanphat.test.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentanphat.test.R;
import com.nguyentanphat.test.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Activity activity;
    int item_layout;
    ArrayList<Product> products;

    //contructor
    public ProductAdapter(Activity activity, int item_layout, ArrayList<Product> products) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.products = products;
    }

    //trả về lượng item
    @Override
    public int getCount() {
        return products.size();
    }

    //lấy item vị trí thứ
    @Override
    public Object getItem(int position) {
        return products.get(position);
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
            holder.txtInfor = view.findViewById(R.id.txtInfor);
            holder.txtRating = view.findViewById(R.id.txtRating);
            holder.txtRatingCount = view.findViewById(R.id.txtRatingCount);

            // để không phải gọi lần 2
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //Binding
        Product p = products.get(position);
        holder.imvThumb.setImageResource(p.getPhoto());
        holder.txtName.setText(p.getDishName());
        holder.txtInfor.setText(p.getPlaceName());
        holder.txtRating.setText(String.valueOf(p.getRatingValue()));
        holder.txtRatingCount.setText(p.getRatingCount());
        return view;
    }

    public class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtInfor, txtRating, txtRatingCount;

    }
}
