package com.bawei.newstitle.frament;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.newstitle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/2/10.
 */
public class Attention extends Fragment implements View.OnClickListener
{

   // private View vv;
    private ViewPager viewPager;
    private List<Fragment> list;
    private TextView[] arr;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View   vv = inflater.inflate(R.layout.attention,null);
        TextView guanzhu = (TextView) vv.findViewById(R.id.TextView_guanzhu);
        guanzhu.setOnClickListener(this);
        TextView tuijian = (TextView) vv.findViewById(R.id.TextView_tuijian);
        tuijian.setOnClickListener(this);
        TextView yanse1 = (TextView) vv.findViewById(R.id.TextView_yanse1);
        TextView yanse2 = (TextView) vv.findViewById(R.id.TextView_yanse2);
        arr = new TextView[]{yanse1,yanse2};
        viewPager = (ViewPager) vv.findViewById(R.id.ViewPager_guanzhu);
        view2();
        return vv;
    }

    private void view2() {
        list = new ArrayList<Fragment>();
        list.add(new Recomment());
        list.add(new Guanzhu());


        initDate();
        viewPager.setCurrentItem(0);
        zhuanhuan(0);
    }





    private void zhuanhuan(int dext)
    {
        for(int i=0;i<arr.length;i++)
        {
            if (dext == i) {
                arr[i].setBackgroundColor(Color.RED);
            } else {
                arr[i].setBackgroundColor(Color.WHITE);
            }
        }

    }

    private void initDate()
{
    viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    });





    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < arr.length; i++) {
                if (position == i) {
                    arr[i].setBackgroundColor(Color.RED);
                } else {
                    arr[i].setBackgroundColor(Color.WHITE);
                }
            }
        }

        public void onPageScrollStateChanged(int state)
            {

        }
    });

}


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.TextView_tuijian:
                viewPager.setCurrentItem(0);
                zhuanhuan(0);

                break;
            case R.id.TextView_guanzhu:
                viewPager.setCurrentItem(1);
                zhuanhuan(1);
                break;
        }
    }
}
