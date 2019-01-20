package com.umeng.soexample.lianxia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.umeng.soexample.lianxia.Contacts;
import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.adapter.GoodsAdapter;
import com.umeng.soexample.lianxia.bean.GoodsBean;
import com.umeng.soexample.lianxia.presenter.PresenterImpl;
import com.umeng.soexample.lianxia.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android_lhf：2019/1/19
 */
public class GoodsActivity extends AppCompatActivity implements IView {
    @BindView(R.id.recy_list)
    RecyclerView recyList;
    private PresenterImpl presenter;
    private List<GoodsBean.DataBean> mList = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        recyList.setLayoutManager(new LinearLayoutManager(this));
        presenter = new PresenterImpl(this);
        goodsAdapter = new GoodsAdapter(this, mList);
        recyList.setAdapter(goodsAdapter);

        Intent intent = getIntent();
        int lu = intent.getIntExtra("lu", 0);
        presenter.startGetRequest(String.format(Contacts.GOODS_URL, lu), GoodsBean.class);
    }
    @Override
    public void success(Object data) {
        if (data instanceof GoodsBean) {

            GoodsBean goodsBean = (GoodsBean) data;
                mList.addAll(goodsBean.getData());
                goodsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure(Object error) {
        Toast.makeText(this, error+"", Toast.LENGTH_SHORT).show();
    }

}
