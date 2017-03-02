package com.bawei.newstitle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.active.Main2Activity;
import com.bawei.newstitle.bean.Vieobean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/2/20.
 */
public class Recomment extends Fragment implements PullToRefreshListView.OnRefreshListener2<ListView>
{
   Handler han=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);

           int currentItem = tuijian.getCurrentItem();
           tuijian.setCurrentItem(++currentItem);
           han.sendEmptyMessageDelayed(0,1000);

       }
   };
    private View vv;
    private ViewPager tuijian;
    private LinearLayout linearlayout;
    private PullToRefreshListView listview;
    int[] image=new int[]{R.mipmap.one1,R.mipmap.one2,R.mipmap.one3,R.mipmap.one4};
    private List<ImageView> list;
    private int text=0;
    private List<Vieobean> list1;
    private DisplayImageOptions dd;
    private BaseAdapter bb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.recomment,null);
        list1 = new ArrayList<>();
        dd = new DisplayImageOptions.Builder().build();
        initView();
        initDate();
        yuan();
        han.sendEmptyMessageDelayed(0,1000);
        listview = (PullToRefreshListView) vv.findViewById(R.id.PullToRefreshListView_tuijian);
        initShuaxin();
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(this);

        return vv;
    }
    private void shipei()
    {
        bb = new BaseAdapter() {
            @Override
            public int getCount() {
                return list1.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {
                ViewHodle viewHodle;
                if(view==null)
                {
                    viewHodle=new ViewHodle();
                    view=View.inflate(getActivity(), R.layout.item2,null);
                    viewHodle.tuijiantitle = (TextView) view.findViewById(R.id.TextView_tuijiantitle);
                    viewHodle.shijian = (TextView) view.findViewById(R.id.TextView_tuijianshijian);
                    viewHodle.wangzhan = (TextView) view.findViewById(R.id.TextView_tuijianwangzhan);
                    viewHodle. tujian = (ImageView) view.findViewById(R.id.ImageView_tujian);
                    view.setTag(viewHodle);
                }else
                {
                    viewHodle= (ViewHodle) view.getTag();
                }
                viewHodle.tuijiantitle.setText(list1.get(i).getTitle());
                viewHodle.shijian.setText(list1.get(i).getVido());
                viewHodle.wangzhan.setText(list1.get(i).getName());
                ImageLoader.getInstance().displayImage(list1.get(i).getImage(), viewHodle. tujian,dd);

                return view;
            }
        };
        listview.setAdapter(bb);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("name",list1.get(i-1).getUri());
                startActivity(intent);
            }
        });
    }

    private void initShuaxin()
    {
        RequestParams re=new RequestParams("http://c.m.163.com/nc/article/headline/T1348647909107/"+text+"-20.html");
        x.http().get(re, new Callback.CommonCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {

                try {
                    JSONObject js=new JSONObject(result);
                    JSONArray array = js.optJSONArray("T1348647909107");
                    String[] an=new String[array.length()];

                        for(int i=0;i<an.length;i++)
                        {
                            an[i]=array.optString(i);
                            JSONObject jj=new JSONObject(an[i]);


                            String imgsrc = jj.optString("imgsrc");
                            String ptime = jj.optString("ptime");
                            String source = jj.optString("source");
                            String url = jj.optString("url");
                            String title = jj.optString("title");
                            Vieobean vieobean=new Vieobean();
                            vieobean.setImage(imgsrc);
                            vieobean.setUri(url);
                            vieobean.setTitle(title);
                            vieobean.setName(source);
                            vieobean.setVido(ptime);
                            list1.add(vieobean);




                        }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                shipei();
               listview.onRefreshComplete();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void yuan()
    {
        list = new ArrayList<>();
        for(int a=0;a<image.length;a++)
        {
            ImageView im=new ImageView(getActivity());
            if(a==0)
            {
                im.setImageResource(R.drawable.yuan);
            }else
            {
                im.setImageResource(R.drawable.yuan1);
            }
            im.setScaleType(ImageView.ScaleType.FIT_XY);
            im.setLayoutParams(new LinearLayout.LayoutParams(20,20));
            linearlayout.addView(im);
            list.add(im);
        }
    }

    private void initView()
    {
        tuijian = (ViewPager) vv.findViewById(R.id.ViewPager_tuijian);
        linearlayout = (LinearLayout) vv.findViewById(R.id.LinearLayout_tuijian);

    }

    private void initDate()
    {
        tuijian.setAdapter(new PagerAdapter() {
            @Override
            public int getCount()
            {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view=View.inflate(getActivity(),R.layout.item1,null);
                ImageView tu = (ImageView) view.findViewById(R.id.ImageView_tu);
                int i = position % image.length;
                tu.setImageResource(image[i]);
                container.addView(view);


                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        });
        tuijian.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
           for(int i=0;i<image.length;i++)
           {
               ImageView imageView = list.get(i);
               if(i==position%image.length)
               {
                   imageView.setImageResource(R.drawable.yuan);
               }else
               {
                   imageView.setImageResource(R.drawable.yuan1);
               }
           }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }


    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        list1.clear();
        text=0;
        initShuaxin();

    }


    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        text+=10;
        initShuaxin();
        bb.notifyDataSetChanged();
    }
    class ViewHodle{
        TextView tuijiantitle;
        TextView shijian;
        TextView wangzhan;
        ImageView tujian;

    }
}
