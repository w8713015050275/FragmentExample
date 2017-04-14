package com.letv.fragmentexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhanghuancheng on 17-4-14.
 */

public class Fragment1 extends Fragment {

    private static final String TAG = "zhc Fragment1";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentViewPagerAdapter mAdapter;
    String[] mTitles = new String[] {
            "tab1",
            "tab2",
            "tab3"
    };
    ViewPagerFragment mViewPagerFragment1;
    ViewPagerFragment mViewPagerFragment2;
    ViewPagerFragment mViewPagerFragment3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        //fragment中嵌套使用Fragment时，构造adapter时使用getChildFragmentManager()，避免视图错误
        //http://www.cnblogs.com/zhengxt/p/3781139.html
        //在ViewPager滑动切换Fragment时，会预加载当前fragment的左右fragment；
        //滑动时，会将超出的fragment执行到onDestroyView()和对下一个相邻的fragment预加载（加载到onResume周期）；
        mAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(), mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class FragmentViewPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles;

        public FragmentViewPagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                if (mViewPagerFragment1 == null) {
                    mViewPagerFragment1 = ViewPagerFragment.newInstance("index: " + position);
                }
                fragment = mViewPagerFragment1;
            } else if (position == 1) {
                if (mViewPagerFragment2 == null) {
                    mViewPagerFragment2 = ViewPagerFragment.newInstance("index: " + position);
                }
                fragment = mViewPagerFragment2;
            } else if (position == 2) {
                if (mViewPagerFragment3 == null) {
                    mViewPagerFragment3 = ViewPagerFragment.newInstance("index: " + position);
                }
                fragment = mViewPagerFragment3;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
