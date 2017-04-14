package com.letv.fragmentexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhanghuancheng on 17-4-14.
 */

public class ViewPagerFragment extends Fragment {
    private static final String TAG = "zhc ViewPagerFragment";
    private static final String CONTENT = "content";
    private String content;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mArgs = getArguments();
        content = (String) mArgs.get(ViewPagerFragment.CONTENT);
        Log.d(TAG, "onCreate: " + content);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: " + content);
        //在ViewPager中使用Fragment，应该将parentView设为null，否则不知道是Activity还是ViewPager的父视图
        //http://blog.csdn.net/huanchengdao/article/details/45847303
        mView = inflater.inflate(R.layout.viewpager_fragment1_layout, null, false);
        TextView index = (TextView) mView.findViewById(R.id.viewpager_index_text);
        index.setText(content);
        return mView;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: " +  content);
        super.onStart();

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: " + content);
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: " + content);
    }

    public static ViewPagerFragment newInstance(String content) {
        ViewPagerFragment fragment =  new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ViewPagerFragment.CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }
}
