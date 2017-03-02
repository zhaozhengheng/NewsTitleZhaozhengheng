package com.bawei.newstitle.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.bean.PublicClass;
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

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by 1 on 2017/2/15.
 */
public class Shipin extends Fragment  implements PullToRefreshListView.OnRefreshListener2<ListView>
{

    private View vv;
    private TextView shipin1;
    private TextView shipin2;
    private JCVideoPlayer jj;
    private String name;
    private List<Vieobean> list;
    private PullToRefreshListView pp;
    private BaseAdapter bb;
    private int dext=10;
    private DisplayImageOptions dd;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.title,null);
        dd = new DisplayImageOptions.Builder().build();
        list = new ArrayList<>();
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        
        initView();
        initDate();


        return vv;
    }

    private void initDate()
    {
        RequestParams re=new RequestParams("http://c.3g.163.com/nc/video/list/"+name+"/n/"+dext+"-10.html");
        x.http().get(re, new Callback.CommonCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                try {
                    JSONObject js=new JSONObject(result);
                    JSONArray jsonArray = js.optJSONArray(name);
                    String[] arr=new String[jsonArray.length()];
                    for(int i=0;i<arr.length;i++)
                    {
                        arr[i]=jsonArray.optString(i);
                        JSONObject je=new JSONObject( arr[i]);
                        String mp4_url = je.optString("mp4_url");
                        String title = je.optString("title");
                        String topicImg = je.optString("cover");
                        String topicName = je.optString("topicName");
                        Vieobean vieobean=new Vieobean();
                        vieobean.setName(topicName);
                        vieobean.setTitle(title);
                        vieobean.setVido(mp4_url);
                        vieobean.setImage(topicImg);
                        list.add(vieobean);



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                shipei();
                pp.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback)
            {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView()
    {

        pp = (PullToRefreshListView) vv.findViewById(R.id.PullToRefreshListView);
        pp.setMode(PullToRefreshBase.Mode.BOTH);
        pp.setOnRefreshListener(this);
    }
    public void shipei()
    {
        bb = new BaseAdapter()
        {
            @Override
            public int getCount() {
                return list.size();
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
                     view=View.inflate(getActivity(), R.layout.shipin,null);
                    viewHodle.shipin1 = (TextView) view.findViewById(R.id.TextView_shipin1);
                    viewHodle.shipin2 = (TextView) view.findViewById(R.id.TextView_shipin2);
                    viewHodle.jj = (JCVideoPlayer) view.findViewById(R.id.JCVideoPlayer);
                    view.setTag(viewHodle);

                }else
                {
                    viewHodle = (ViewHodle) view.getTag();
                }
                viewHodle.shipin1.setText(list.get(i).getTitle());
                viewHodle.shipin2.setText(list.get(i).getName());
                viewHodle.jj.setUp(list.get(i).getVido(),list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getImage(),viewHodle.jj.ivThumb,dd);
                return view;
            }
        };
        pp.setAdapter(bb);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        list.clear();
        dext=10;
        initDate();
    }


    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        dext+=10;
        initDate();
        bb.notifyDataSetChanged();

    }

    class ViewHodle{
        TextView shipin1;
        TextView shipin2;
         JCVideoPlayer jj;
    }
}
