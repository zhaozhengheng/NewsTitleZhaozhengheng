package com.bawei.newstitle.frament;

import android.content.Intent;
import android.os.Build;
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
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.active.Pindaoguanli;
import com.bawei.newstitle.bean.PublicClass;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 2017/2/10.
 */
public class Home extends Fragment
{

    String[] arr=new String[]{ "足球", "娱乐", "体育", "财经", "科技",
            "电影", "汽车", "笑话", "游戏", "时尚",
            "情感", "精选", "电台", "NBA", "数码",
            "移动", "彩票", "教育", "论坛", "旅游",
            "手机", "博客", "社会", "家居", "暴雪游戏",
            "亲子", "CBA", "消息", "军事"};
    private List<Fragment> list;
    private ViewPager ve;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv=inflater.inflate(R.layout.home, null);
        TabLayout tt= (TabLayout) vv.findViewById(R.id.TabLayout);
        TextView homejia = (TextView) vv.findViewById(R.id.TextView_homejia);
        homejia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getActivity(), Pindaoguanli.class);
                startActivity(intent);

            }
        });
        ve = (ViewPager) vv.findViewById(R.id.ViewPager);
        initDate();
        apdater();
        tt.setTabMode(TabLayout.MODE_SCROLLABLE);
        tt.setupWithViewPager(ve);
        return vv;
    }


private void apdater()
{
    ve.setOffscreenPageLimit(3);
    ve.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager())
       {
        @Override
        public Fragment getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public int getCount()
        {
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
            Bundle bb=new Bundle();
            bb.putString("name", PublicClass.home_Titles_ID[i]);
            te.setArguments(bb);
            list.add(te);
        }
    }
}

