package com.bawei.newstitle.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.newstitle.R;
import com.bawei.newstitle.bean.PublicClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/2/10.
 */
public class Vip extends Fragment
{

    private View vv;
    private TabLayout shipin_tt;
    private ViewPager shipin_viewpage;
   List<Fragment> list=new ArrayList<Fragment>();
    String[] arr = new String[]{
            "热点视频","娱乐视频","搞笑视频","精品视频"
    };
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.vip,null);
        initView();
        initDate();
        addfragmet();
        shipin_tt.setTabMode(TabLayout.MODE_SCROLLABLE);
        shipin_tt.setupWithViewPager(shipin_viewpage);

        return vv;
    }

    private void initView()
    {
        shipin_tt = (TabLayout) vv.findViewById(R.id.TabLayout_shipin);
        shipin_viewpage = (ViewPager) vv.findViewById(R.id.ViewPager_shipin);
    }

    private void initDate()
    {
        shipin_viewpage.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position)
            {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return arr[position];
            }
        });
    }
    public void addfragmet()
    {
        for(int i=0;i<arr.length;i++)
        {
            Shipin tt=new Shipin();
            Bundle bb=new Bundle();
            bb.putString("name", PublicClass.videoID[i]);
            tt.setArguments(bb);
            list.add(tt);

        }
    }
}
