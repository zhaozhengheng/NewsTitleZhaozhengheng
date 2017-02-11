package com.bawei.newstitle.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.newstitle.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 2017/2/10.
 */
public class Home extends Fragment
{

    String[] arr=new String[]{"推荐", "热点", "阳光", "体育", "北京", "社会", "娱乐", "财经"};
    private List<Fragment> list;
    private ViewPager ve;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv=inflater.inflate(R.layout.home, null);
        TabLayout tt= (TabLayout) vv.findViewById(R.id.TabLayout);
        ve = (ViewPager) vv.findViewById(R.id.ViewPager);
        initDate();
        apdater();
        tt.setTabMode(TabLayout.MODE_SCROLLABLE);
        tt.setupWithViewPager(ve);
        return vv;
    }


private void apdater()
{
    ve.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager())
       {
        @Override
        public Fragment getItem(int position) {
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
    private void initDate()
    {
        list = new ArrayList<Fragment>();
        for(int i=0;i<arr.length;i++)
        {
            Title te=new Title();
            list.add(te);
        }
    }
}

