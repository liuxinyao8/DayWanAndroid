package com.example.daywanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.daywanandroid.R;
import com.example.daywanandroid.activity.SamrtActivityWv;
import com.example.daywanandroid.model.bean.smartbean.SmartWengZhangBean;

import java.util.List;

public class SmartWengZhangAdapter extends RecyclerView.Adapter<SmartWengZhangAdapter.ViewHolder> {
    private List<SmartWengZhangBean.DataBean.DatasBean> datas;
    private Context context;

    public SmartWengZhangAdapter(List<SmartWengZhangBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.smart_wengzhang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
viewHolder.tv1.setText(datas.get(i).getAuthor());
viewHolder.tv2.setText(datas.get(i).getNiceDate());
viewHolder.tv3.setText(datas.get(i).getTitle());
viewHolder.tv4.setText(datas.get(i).getSuperChapterName()+"/"+datas.get(i).getChapterName());
viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SamrtActivityWv.class);
        intent.putExtra("top",datas.get(i).getTitle());
        intent.putExtra("l",datas.get(i).getLink());
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private CheckBox cbh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.smart_tv1);
            tv2=itemView.findViewById(R.id.smart_tv2);
            tv3=itemView.findViewById(R.id.smart_tv3);
            tv4=itemView.findViewById(R.id.smart_tv4);
            cbh=itemView.findViewById(R.id.im_smart_xin);
        }
    }
}
