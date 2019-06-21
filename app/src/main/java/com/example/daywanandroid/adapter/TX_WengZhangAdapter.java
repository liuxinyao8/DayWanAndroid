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
import com.example.daywanandroid.activity.TXWengzhangWv;
import com.example.daywanandroid.model.bean.zxtxBean.WengZhangBean;

import java.util.List;

public class TX_WengZhangAdapter extends RecyclerView.Adapter<TX_WengZhangAdapter.ViewHolder> {
    private List<WengZhangBean.DataBean.DatasBean> datas;
    private Context context;

    public TX_WengZhangAdapter(List<WengZhangBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.zstx_wengzhang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        WengZhangBean.DataBean.DatasBean datasBean = datas.get(i);
        viewHolder.tv1.setText(datas.get(i).getAuthor());
viewHolder.tv2.setText(datas.get(i).getNiceDate());
viewHolder.tv3.setText(datas.get(i).getTitle());
viewHolder.tv4.setText(datas.get(i).getSuperChapterName()+"/"+datas.get(i).getChapterName());
viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, TXWengzhangWv.class);
        intent.putExtra("tittle",datas.get(i).getTitle());
        intent.putExtra("link",datas.get(i).getLink());
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
        private CheckBox im;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.TX_tv1);
            tv2=itemView.findViewById(R.id.TX_tv2);
            tv3=itemView.findViewById(R.id.TX_tv3);
            tv4=itemView.findViewById(R.id.TX_tv4);
            im=itemView.findViewById(R.id.im_TX_xin);
        }
    }
}
