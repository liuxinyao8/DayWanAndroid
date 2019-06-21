package com.example.daywanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.daywanandroid.R;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.bean.wx.WxWengZhangBean;

import java.util.List;

public class WxAdapter extends RecyclerView.Adapter<WxAdapter.ViewHolder> {
    private List<WxWengZhangBean.DataBean.DatasBean> datas;
    private Context context;

    public WxAdapter(List<WxWengZhangBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.wx_wengzhang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final WxWengZhangBean.DataBean.DatasBean datasBean = datas.get(i);
        viewHolder.tv1.setText(datas.get(i).getAuthor());
        viewHolder.tv2.setText(datas.get(i).getNiceDate());
        viewHolder.tv3.setText(datas.get(i).getTitle());
        viewHolder.tv4.setText(datas.get(i).getSuperChapterName()+"/"+datas.get(i).getChapterName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wx.OnLister(datasBean,i);
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
        private CheckBox wx_cb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.wx_tv1);
            tv2=itemView.findViewById(R.id.wx_tv2);
            tv3=itemView.findViewById(R.id.wx_tv3);
            tv4=itemView.findViewById(R.id.wx_tv4);
            wx_cb=itemView.findViewById(R.id.im_wx_xin);
        }
    }
    private Wx wx;

    public void Wx(Wx wx) {
        this.wx = wx;
    }

    public  interface Wx{
        void OnLister(WxWengZhangBean.DataBean.DatasBean datasBean, int p);
    }
}
