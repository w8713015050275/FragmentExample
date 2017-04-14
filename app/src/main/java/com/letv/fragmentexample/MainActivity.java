package com.letv.fragmentexample;

import android.graphics.Color;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        //在FragmentTabHost点击切换fragment时，会走fragment：onCreateView()->onDestroyView()生命周期
        //并不会destory fragment;避免反复创建销毁fragment
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(getIndicatorView(0)), Fragment1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(getIndicatorView(1)), Fragment2.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(getIndicatorView(2)), Fragment3.class, null);
    }

    private View getIndicatorView(int index) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        //注意：inflate 一个tab的indicator时，注意传入parent为null，在TabHost调用addTab时，将indicator添加到
        //TabWidget中，TabWidget会对view的LayoutParams重新赋值，使其均分宽度；
        View view = inflater.inflate(R.layout.indicator_view_layout, null);
        ((TextView)view.findViewById(R.id.indicator_text)).setText("tab" + index);
        return view;
    }
}
