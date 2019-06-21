package com.example.daywanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daywanandroid.R;
import com.example.daywanandroid.model.bean.smartbean.SmartRecrdBean;

import java.util.ArrayList;
import java.util.List;

public class SmartAdapter extends RecyclerView.Adapter<SmartAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> Stringss;

    public SmartAdapter(Context context, ArrayList<String> stringss) {
        this.context = context;
        Stringss = stringss;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.smart_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
     viewHolder.tv1.setText(Stringss.get(i));
viewHolder.im.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Stringss.remove(Stringss.get(i));
        notifyDataSetChanged();
    }
});
    }

    @Override
    public int getItemCount() {
        return Stringss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private ImageView im;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.smart_smart1);
            im=itemView.findViewById(R.id.smart_im);
        }
    }
}
