package com.umeng.soexample.lianxia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.umeng.soexample.lianxia.fragment.ClassifyFragment;
import com.umeng.soexample.lianxia.fragment.NullPageFragment;
import com.umeng.soexample.lianxia.fragment.Shooping_CartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_page)
    RadioButton homePage;
    @BindView(R.id.classify)
    RadioButton classify;
    @BindView(R.id.seek)
    RadioButton seek;
    @BindView(R.id.shooping_cart)
    RadioButton shoopingCart;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.frame)
    FrameLayout frame;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame, new NullPageFragment()).commit();
    }
    @OnClick({R.id.home_page, R.id.classify, R.id.seek, R.id.shooping_cart, R.id.mine})
    public void onViewClicked(View view) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.home_page:
                transaction.replace(R.id.frame, new NullPageFragment()).commit();
                break;
            case R.id.classify:
                transaction.replace(R.id.frame, new ClassifyFragment()).commit();
                break;
            case R.id.seek:
                transaction.replace(R.id.frame, new NullPageFragment()).commit();
                break;
            case R.id.shooping_cart:
                transaction.replace(R.id.frame, new Shooping_CartFragment()).commit();
                break;
            case R.id.mine:
                transaction.replace(R.id.frame, new NullPageFragment()).commit();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String lu = intent.getStringExtra("lu");
        if (lu !=null){
            if(lu.equals("1")){
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, new Shooping_CartFragment()).commit();
            }
        }
    }
}
