package com.example.daywanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daywanandroid.R;
import com.example.daywanandroid.model.bean.xmbean.XmwzBean;

import java.util.List;

public class XmFragmnetAdapter extends RecyclerView.Adapter<XmFragmnetAdapter.ViewHolder> {
    private List<XmwzBean.DataBean.DatasBean> datas;
    private Context context;

    public XmFragmnetAdapter(List<XmwzBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xm_wengzhang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(datas.get(i).getTitle());
        viewHolder.tv2.setText(datas.get(i).getSuperChapterName());
        viewHolder.tv3.setText(datas.get(i).getDesc());
        viewHolder.tv4.setText(datas.get(i).getChapterName());
        Glide.with(context).load(datas.get(i).getEnvelopePic()).into(viewHolder.im1);

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
        private ImageView im1;
        private CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.xm_tv1);
            tv2 = itemView.findViewById(R.id.xm_tv2);
            tv3 = itemView.findViewById(R.id.xm_tv3);
            tv4 = itemView.findViewById(R.id.xm_tv4);
            im1 = itemView.findViewById(R.id.im_xm_im1);
            cb = itemView.findViewById(R.id.im_xm_xin);
        }
    }
}
