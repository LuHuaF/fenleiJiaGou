package com.umeng.soexample.lianxia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.soexample.lianxia.Contacts;
import com.umeng.soexample.lianxia.MainActivity;
import com.umeng.soexample.lianxia.R;
import com.umeng.soexample.lianxia.bean.AddCartBean;
import com.umeng.soexample.lianxia.presenter.PresenterImpl;
import com.umeng.soexample.lianxia.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android_lhf：2019/1/19
 */
public class DetailsActivity extends AppCompatActivity implements IView {

    Button addCart;
    @BindView(R.id.cart)
    Button cart;
    @BindView(R.id.web_view)
    WebView webView;
    private PresenterImpl presenter;
    private int pid;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        presenter = new PresenterImpl(this);
        String details = intent.getStringExtra("lu");
        pid = intent.getIntExtra("lu",0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(details);
    }
    @OnClick({R.id.add_cart, R.id.cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_cart:
                presenter.startGetRequest(String.format(Contacts.ADDGOODS_URL,pid), AddCartBean.class);
                break;
            case R.id.cart:
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("lu",1+"");
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public void success(Object data) {
        if (data instanceof AddCartBean) {
            AddCartBean addCartBean = (AddCartBean) data;
            if (addCartBean.getCode().equals("0")) {
                Toast.makeText(this, addCartBean.getMsg() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failure(Object error) {

    }
}
