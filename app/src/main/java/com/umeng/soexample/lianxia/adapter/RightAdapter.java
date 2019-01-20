package com.umeng.soexample.lianxia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.bean.RightBean;
import com.umeng.soexample.lianxia.ui.GoodsActivity;

import java.util.List;

/**
 * Created by android_lhf：2019/1/19
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    private Context mContext;
    private List<RightBean.DataBean> mList;

    public RightAdapter(Context mContext, List<RightBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.right_item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(0).getList().get(position).getIcon()).into(holder.rightImg);
        holder.rightTitle.setText(mList.get(0).getList().get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("lu", mList.get(0).getList().get(position).getPscid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView rightImg;
        private TextView rightTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            rightImg = itemView.findViewById(R.id.right_img);
            rightTitle = itemView.findViewById(R.id.right_title);
        }
    }
}
