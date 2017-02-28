package com.bawei.newstitle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.active.Main2Activity;
import com.bawei.newstitle.bean.ImageBean;
import com.bawei.newstitle.bean.ImageTitleBean;
import com.bawei.newstitle.bean.PublicClass;
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
 * Created by 1 on 2017/2/11.
 */
public class Title extends Fragment  implements PullToRefreshListView.OnRefreshListener2<ListView>
{

    private PullToRefreshListView home;
    private String uri;
    private String name;
    private int age = 0;
    private List<ImageBean> list;
    private List<ImageTitleBean> listtitle;
    private DisplayImageOptions dd;
    private BaseAdapter bb;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.title, null);
        home = (PullToRefreshListView) vv.findViewById(R.id.PullToRefreshListView);
        name = getArguments().getString("name");
        list = new ArrayList<>();
        listtitle = new ArrayList<>();
        dd = new DisplayImageOptions.Builder().build();
        initDate();
        home.setMode(PullToRefreshBase.Mode.BOTH);
        home.setOnRefreshListener(this);


        return vv;
    }

    private void initView() {

        bb = new BaseAdapter() {
            final int item1 = 0;
            final int item2 = 1;

            @Override
            public int getCount() {
                return listtitle.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {


                if (listtitle.get(position).getImgextra() !=null) {
                    return item2;
                }
                return item1;


            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHodle viewhodle;
                int itemViewType = getItemViewType(i);


                if (view == null) {

                    viewhodle = new ViewHodle();
                    switch (itemViewType) {
                        case item1:
                            view = View.inflate(getActivity(), R.layout.itemone, null);
                            viewhodle.title = (TextView) view.findViewById(R.id.TextView_titlet1);
                            viewhodle.soure = (TextView) view.findViewById(R.id.TextView_source);
                            viewhodle.titletu = (ImageView) view.findViewById(R.id.ImageView_titletu);
                            view.setTag(viewhodle);
                            break;

                        case item2:
                            view = View.inflate(getActivity(), R.layout.itemtwo, null);
                            viewhodle.titletwo1 = (TextView) view.findViewById(R.id.TextView_titletwo1);
                            viewhodle.titletwo2 = (TextView) view.findViewById(R.id.TextView_titletwo2);
                            viewhodle.ImageViewtwo1 = (ImageView) view.findViewById(R.id.ImageViewtwo1);
                            viewhodle.ImageViewtwo2 = (ImageView) view.findViewById(R.id.ImageViewtwo2);
                            view.setTag(viewhodle);
                            break;
                    }
                } else {
                    viewhodle = (ViewHodle) view.getTag();
                }
                switch (itemViewType) {
                    case item1:
                        viewhodle.title.setText(listtitle.get(i).getTitle());
                        viewhodle.soure.setText(listtitle.get(i).getSource());
                        ImageLoader.getInstance().displayImage(listtitle.get(i).getImgsrc(), viewhodle.titletu, dd);
                        break;

                    case item2:
                        viewhodle.titletwo1.setText(listtitle.get(i).getTitle());
                        viewhodle.titletwo2.setText(listtitle.get(i).getSource());
                        ImageLoader.getInstance().displayImage(listtitle.get(i).getImgextra().get(0).getImage(), viewhodle.ImageViewtwo1, dd);
                        ImageLoader.getInstance().displayImage(listtitle.get(i).getImgextra().get(1).getImage(), viewhodle.ImageViewtwo2, dd);
                        break;
                }


                return view;
            }
        };
        home.setAdapter(bb);
        home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent=new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("name",listtitle.get(i-1).getUri());
                startActivity(intent);
            }
        });

    }


    private void initDate() {
        RequestParams re = new RequestParams(PublicClass.getURL(name, age));
        x.http().get(re, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                try {
                    JSONObject js = new JSONObject(result);
                    JSONArray array = js.optJSONArray(name);
                    String[] arr = new String[array.length()];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = array.optString(i);
                        JSONObject jj = new JSONObject(arr[i]);
                        ImageTitleBean imageTitleBean = new ImageTitleBean();
                        String title = jj.optString("title");
                        imageTitleBean.setTitle(title);
                        String source = jj.optString("source");
                        String url = jj.optString("url");
                        imageTitleBean.setUri(url);
                        imageTitleBean.setSource(source);
                        String imgsrc = jj.optString("imgsrc");
                        imageTitleBean.setImgsrc(imgsrc);
                        JSONArray imgextra = jj.optJSONArray("imgextra");
                        if (imgextra != null) {
                            String[] arr1 = new String[imgextra.length()];
                            for (int a = 0; a < arr1.length; a++) {
                                arr1[a] = imgextra.optString(a);
                                JSONObject je = new JSONObject(arr1[a]);
                                String imgsrc1 = je.optString("imgsrc");
                                ImageBean im = new ImageBean();
                                im.setImage(imgsrc1);
                                list.add(im);

                                imageTitleBean.setImgextra(list);
                            }
                        }


                        listtitle.add(imageTitleBean);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
              home.onRefreshComplete();
                initView();

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


    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        listtitle.clear();
        age=0;
        initDate();


    }


    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        age+=10;
        initDate();
        bb.notifyDataSetChanged();

    }

    class ViewHodle {
        TextView title;
        TextView titletwo1;
        ImageView ImageViewtwo2;
        ImageView ImageViewtwo1;
        ImageView titletu;
        TextView titletwo2;
        TextView soure;
    }

}
