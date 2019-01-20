package com.umeng.soexample.lianxia.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.lianxia.Contacts;
import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.adapter.LeftAdapter;
import com.umeng.soexample.lianxia.adapter.RightAdapter;
import com.umeng.soexample.lianxia.bean.LeftBean;
import com.umeng.soexample.lianxia.bean.RightBean;
import com.umeng.soexample.lianxia.presenter.PresenterImpl;
import com.umeng.soexample.lianxia.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by android_lhf：2019/1/19
 */
public class ClassifyFragment extends Fragment implements IView {
    @BindView(R.id.left_recy)
    RecyclerView leftRecy;
    @BindView(R.id.right_recy)
    RecyclerView rightRecy;
    Unbinder unbinder;
    private List<LeftBean.DataBean> mLeft = new ArrayList<>();
    private List<RightBean.DataBean> mRight = new ArrayList<>();
    private PresenterImpl presenter;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify, null);
        unbinder = ButterKnife.bind(this, v);
        initView();
        leftAdapter = new LeftAdapter(getActivity(), mLeft);
        leftRecy.setAdapter(leftAdapter);
        presenter.startGetRequest(Contacts.LEFT_URL,LeftBean.class);
        onclick();
        return v;
    }
    private void onclick() {
        leftAdapter.setOnClick(new LeftAdapter.onClick() {
            @Override
            public void getCid(View v, int cid) {
                rightAdapter = new RightAdapter(getActivity(), mRight);
                rightRecy.setAdapter(rightAdapter);
                presenter.startGetRequest(String.format(Contacts.RIGHT_URL,cid),RightBean.class);
            }
        });
    }

    private void initView() {
        presenter = new PresenterImpl(this);
        leftRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {
        if(data instanceof LeftBean){
            LeftBean leftBean = (LeftBean) data;
            mLeft.addAll(leftBean.getData());
            leftAdapter.notifyDataSetChanged();
        }
        if(data instanceof RightBean){
            mRight.clear();
            RightBean rightBean = (RightBean) data;
            mRight.addAll(rightBean.getData());
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure(Object error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
