package com.example.daywanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daywanandroid.R;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

import java.util.List;

public class ZhiShiAdapter extends RecyclerView.Adapter<ZhiShiAdapter.ViewHolder> {
    private List<ZstxBean.DataBean> data;
    private Context context;


    public ZhiShiAdapter(List<ZstxBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.zstx_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ZstxBean.DataBean dataBean = data.get(i);
        viewHolder.tv1.setText(data.get(i).getName());
        StringBuffer sf = new StringBuffer();
        final List<ZstxBean.DataBean.ChildrenBean> children = data.get(i).getChildren();
        for (ZstxBean.DataBean.ChildrenBean data:children) {
            sf.append(data.getName()).append("   ");

        }
        viewHolder.tv2.setText(sf.toString());
     viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             zhishi.onClick(dataBean,i);
         }
     });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.Zs_tv1);
            tv2=itemView.findViewById(R.id.ZS_tv2);
        }
    }
    private Zhishi zhishi;

    public void setZhishi(Zhishi zhishi) {
        this.zhishi = zhishi;
    }

    public   interface Zhishi {
        void onClick(ZstxBean.DataBean dataBean,int p);
    }
}
