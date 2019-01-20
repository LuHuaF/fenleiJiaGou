package com.umeng.soexample.lianxia.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.umeng.soexample.lianxia.Contacts;
import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.bean.CartBean;
import com.umeng.soexample.lianxia.presenter.PresenterImpl;
import com.umeng.soexample.lianxia.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by android_lhf：2019/1/19
 */
public class Shooping_CartFragment extends Fragment implements IView {
    Unbinder unbinder;
    @BindView(R.id.recy)
    RecyclerView recy;
    private PresenterImpl presenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shooping__cart, null);
        presenter = new PresenterImpl(this);
        presenter.startGetRequest(String.format(Contacts.QUERYGOODS_URL, 71), CartBean.class);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void success(Object data) {
        if (data instanceof CartBean) {
            CartBean cartBean = (CartBean) data;
            if (cartBean.getCode().equals("0")) {
                Toast.makeText(getActivity(), cartBean.getData().get(1).getList() + "", Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
