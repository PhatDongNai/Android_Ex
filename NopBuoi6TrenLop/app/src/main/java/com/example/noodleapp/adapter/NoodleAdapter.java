package com.example.noodleapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.noodleapp.R;
import com.example.noodleapp.models.Noodle;

import java.util.ArrayList;

public class NoodleAdapter extends BaseAdapter {
    Activity activity;
    int item_layout;
    ArrayList<Noodle> noodles;

    //constructor
    public NoodleAdapter(Activity activity, int item_layout, ArrayList<Noodle> noodles) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.noodles = noodles;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder ;
        if(view == null ){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            //Link item views
            holder.imvThumb = view.findViewById(R.id.thumb);
            holder.txt_infor = view.findViewById(R.id.txt_infor);
            holder.txt_subtitle = view.findViewById(R.id.txt_subtitle);
            holder.txt_star = view.findViewById(R.id.txt_star);
            holder.txt_distance = view.findViewById(R.id.txt_distance);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
            //Binding data
            Noodle b = noodles.get(position);
            holder.imvThumb.setImageResource(b.getThumb());
            holder.txt_infor.setText(b.getInfor());
            holder.txt_subtitle.setText(b.getSubTitle());
            holder.txt_star.setText(b.getRate());
            holder.txt_distance.setText(b.getDistance());
        }
        return view;

    }

    public class ViewHolder{
        ImageView imvThumb;
        TextView txt_infor;
        TextView txt_subtitle;
        TextView txt_star;
        TextView txt_distance;

        //getter and setter

        public ImageView getImvThumb() {
            return imvThumb;
        }

        public void setImvThumb(ImageView imvThumb) {
            this.imvThumb = imvThumb;
        }

        public TextView getTxt_infor() {
            return txt_infor;
        }

        public void setTxt_infor(TextView txt_infor) {
            this.txt_infor = txt_infor;
        }

        public TextView getTxt_subtitle() {
            return txt_subtitle;
        }

        public void setTxt_subtitle(TextView txt_subtitle) {
            this.txt_subtitle = txt_subtitle;
        }

        public TextView getTxt_star() {
            return txt_star;
        }

        public void setTxt_star(TextView txt_star) {
            this.txt_star = txt_star;
        }

        public TextView getTxt_distance() {
            return txt_distance;
        }

        public void setTxt_distance(TextView txt_distance) {
            this.txt_distance = txt_distance;
        }
    }
}
