package com.example.daywanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.example.daywanandroid.activity.SYWZWV;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.sql.DBsql;
import com.example.daywanandroid.sql.Student;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class ShouYeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShuoYeBean.DataBean> data;
    private List<ShuoYeWengZhangBean.DataBean.DatasBean> datas;
    private Context context;
    private  int a;
    public ShouYeAdapter(List<ShuoYeBean.DataBean> data, List<ShuoYeWengZhangBean.DataBean.DatasBean> datas, Context context) {
        this.data = data;
        this.datas = datas;
        this.context = context;
    }

    public ShouYeAdapter(List<ShuoYeWengZhangBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.sy_banner, null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        } else if (i==1){
            View view = LayoutInflater.from(context).inflate(R.layout.sy_wengzhang, null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.sy_wengzhang2, null);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == 0) {
            ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
            final ShuoYeBean.DataBean dataBean = data.get(i);
            viewHolder1.banner.setImages(data).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    ShuoYeBean.DataBean dataBean = (ShuoYeBean.DataBean) path;
                    Glide.with(context).load(dataBean.getImagePath()).into(imageView);
                }
            }).start();

            viewHolder1.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    mybanner.onBannerlisenter(dataBean,position);
                }
            });

        } else {
             a=i;
            if (data.size() > 0) {
                a -=1;
            }
            if (viewType==1) {
                final ShuoYeWengZhangBean.DataBean.DatasBean datasBean = datas.get(a);
                final ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
                viewHolder2.tv1.setText(datas.get(a).getAuthor());
                viewHolder2.tv2.setText(datas.get(a).getNiceDate());
                viewHolder2.tv3.setText(datas.get(a).getTitle());
                viewHolder2.tv4.setText(datas.get(a).getSuperChapterName() + "/" + datas.get(a).getChapterName());
                viewHolder2.ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (viewHolder2.ch.isChecked()) {
//                            Student student = new Student();
//                            student.setId(null);
//                            student.setAutho(datasBean.getAuthor());
//                            student.setTime(datasBean.getNiceDate());
//                            student.setTitle(datasBean.getTitle());
//                            student.setTypename(datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
//                            DBsql.getdBsql().insert(student);
                            wengzhang.onBannerlisenter(viewHolder2.ch.isChecked(),datasBean,a);
//                            Toast.makeText(context, "收藏成功！", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String link = datasBean.getLink();
                        Intent intent = new Intent(context, SYWZWV.class);
                        intent.putExtra("link",link);
                        context.startActivity(intent);

                    }
                });

            }else {
                final ShuoYeWengZhangBean.DataBean.DatasBean datasBean = datas.get(a);
                final ViewHolder3 viewHolder3 = (ViewHolder3) viewHolder;
                viewHolder3.tv1.setText(datas.get(a).getAuthor());
                viewHolder3.tv2.setText(datas.get(a).getNiceDate());
                viewHolder3.tv3.setText(datas.get(a).getTitle());
                viewHolder3.tv4.setText(datas.get(a).getSuperChapterName()+"/"+datas.get(a).getChapterName());
                Glide.with(context).load(datas.get(a).getEnvelopePic()).into(viewHolder3.im);
                viewHolder3.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            wengzhang.onBannerlisenter(viewHolder3.cb.isChecked(),datasBean,a);
//                            Toast.makeText(context, "收藏成功！", Toast.LENGTH_SHORT).show();
                        }else {

                        }
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        if (data.size() > 0) {
            return datas.size() + 1;
        } else {
            return datas.size();
        }

    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private Banner banner;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.ShuoYeban);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private CheckBox ch;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.SY_tv1);
            tv2 = itemView.findViewById(R.id.SY_tv2);
            tv3 = itemView.findViewById(R.id.SY_tv3);
            tv4 = itemView.findViewById(R.id.SY_tv4);
            ch = itemView.findViewById(R.id.im_Sy_xin);
        }
    }
class  ViewHolder3 extends RecyclerView.ViewHolder{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private ImageView im;
        private CheckBox cb;
    public ViewHolder3(@NonNull View itemView) {
        super(itemView);
        tv1=itemView.findViewById(R.id.SY_lay_tv1);
        tv2=itemView.findViewById(R.id.SY_lay_tv2);
        tv3=itemView.findViewById(R.id.SY_lay_tv3);
        tv4=itemView.findViewById(R.id.SY_lay_tv4);
        im=itemView.findViewById(R.id.im_sy_im1);
        cb=itemView.findViewById(R.id.im_Sy_xin);
    }
}
    @Override
    public int getItemViewType(int position) {
        if (data.size() > 0 && position == 0) {
            return 0;
        } else {
           int p=position;
           if (data!=null&&data.size()>0){
               p-=1;
           }

            if (!datas.get(p).getEnvelopePic().equals("")){
                return 2;
            }
            return 1;
        }

    }
    private Mybanner mybanner;

    public void setMybanner(Mybanner mybanner) {
        this.mybanner = mybanner;
    }

    public   interface Mybanner{
        void onBannerlisenter(ShuoYeBean.DataBean dataBean,int p);
  }


    private Wengzhang wengzhang;

    public void setWengzhang(Wengzhang wengzhang) {
        this.wengzhang = wengzhang;
    }

    public   interface Wengzhang{
        void onBannerlisenter(Boolean b,ShuoYeWengZhangBean.DataBean.DatasBean datasBean,int p);
    }
}
