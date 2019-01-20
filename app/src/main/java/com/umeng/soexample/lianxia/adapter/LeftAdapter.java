package com.umeng.soexample.lianxia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.bean.LeftBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by android_lhf：2019/1/19
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context mContext;
    private List<LeftBean.DataBean> mList;

    public LeftAdapter(Context mContext, List<LeftBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.left_item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.leftTitle.setText(mList.get(position).getName());
        holder.leftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null){
                    onclick.getCid(v,mList.get(position).getCid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_title)
        TextView leftTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            leftTitle = itemView.findViewById(R.id.left_title);
        }
    }
    public interface onClick{
        void getCid(View v,int cid);
    }
    private onClick onclick;
    public void setOnClick(onClick onclick){
        this.onclick = onclick;
    }
}
