package com.example.daywanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daywanandroid.R;
import com.example.daywanandroid.activity.DhWv;
import com.example.daywanandroid.model.bean.dhbean.DhBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DhAdapter extends RecyclerView.Adapter<DhAdapter.ViewHolder> {
    private ArrayList<DhBean.DataBean> data;
    private Context context;

    public DhAdapter(ArrayList<DhBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(ArrayList<DhBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fl_dh, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final int num = i;
        viewHolder.tv.setText(data.get(i).getName());
        ArrayList<String> title = new ArrayList<>();
        List<DhBean.DataBean.ArticlesBean> articles = data.get(i).getArticles();
        for (int j = 0; j < articles.size(); j++) {
            title.add(articles.get(j).getTitle());
        }
        if (title != null) {
            viewHolder.tagFlowLayout.setAdapter(new TagAdapter<String>(title) {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.dh_fl_itm, null);
                    TextView text = inflate.findViewById(R.id.fl_text);
                    text.setText(s);
                    String[] colors = {"#27AB0B", "#93335A", "#070405", "#F63406", "#375618", "#B19911", "#B522EA"};
                    Random random = new Random();
                    int color = random.nextInt(6);
                    text.setTextColor(Color.parseColor(colors[color]));
                    text.setBackground(context.getResources().getDrawable(R.drawable.resou));
                    text.setTextSize(20f);
                    text.setPadding(15, 10, 15, 10);
                    return inflate;
                }
            });
        }
        viewHolder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String link = data.get(num).getArticles().get(position).getLink();
                Intent intent = new Intent(context, DhWv.class);
                intent.putExtra("url", link);
                context.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private TagFlowLayout tagFlowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tagFlowLayout = itemView.findViewById(R.id.fl_dh);

        }
    }
}
