package com.example.daywanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daywanandroid.R;
import com.example.daywanandroid.app.Constant;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.model.likebean.MainBean;
import com.example.daywanandroid.sql.DBsql;
import com.example.daywanandroid.sql.Student;
import com.example.daywanandroid.util.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    private List<MainBean.DataBean.DatasBean> datas;
    private Context context;


    public LikeAdapter(List<MainBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.like_wengzhang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MainBean.DataBean.DatasBean datasBean = datas.get(i);

        viewHolder.tv1.setText(datas.get(i).getAuthor());
        viewHolder.tv2.setText(datas.get(i).getNiceDate());
        viewHolder.tv3.setText(datas.get(i).getTitle());
        viewHolder.tv4.setText(datas.get(i).getChapterName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClick(v, datasBean);
            }
        });

        if (viewHolder.ch.isChecked()) {
            viewHolder.ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateByLocalData(datasBean, viewHolder.ch);
                }
            });
        }
    }

    private void updateByLocalData(MainBean.DataBean.DatasBean datasBean, CheckBox ch) {
        if (ch.isChecked()) {
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "删除失败！", Toast.LENGTH_SHORT).show();
        }
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
        private CheckBox ch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.like_tv1);
            tv2 = itemView.findViewById(R.id.like_tv2);
            tv3 = itemView.findViewById(R.id.like_tv3);
            tv4 = itemView.findViewById(R.id.like_tv4);
            ch = itemView.findViewById(R.id.im_like_xin);
        }
    }

    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void onClick(View v, MainBean.DataBean.DatasBean datasBean);
    }
}
