package com.bawei.newstitle.active;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.Utils.utils;
import com.bawei.newstitle.frament.Attention;
import com.bawei.newstitle.frament.Home;
import com.bawei.newstitle.frament.My;
import com.bawei.newstitle.frament.Vip;
import com.handmark.pulltorefresh.library.internal.Utils;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {private LinearLayout[] arr;
    private TextView[] arr1;
    private ImageView[] arr2;
    int[] im=new int[]{R.mipmap.shou,R.mipmap.guanzhu,R.mipmap.vip,R.mipmap.wode};
    int[] im1=new int[]{R.mipmap.shou1,R.mipmap.guanzhu1,R.mipmap.vip1,R.mipmap.wode1};
    private LinearLayout home;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control();
        if(utils.flas)
        {
            if(new My()!=null)
            {
                control();
                replac(new My());
                color(3);

            }
            control();
            replac(new Home());
            color(0);
            utils.flas=false;
        }





    }
    public void replac(Fragment ff)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.FrameLayout,ff);
        transaction.commit();
    }

    @Override
    public void onClick(View view)
    {
   switch (view.getId())
   {
       case R.id.LinearLayout_home:
           replac(new Home());
           color(0);
           break;
       case R.id.LinearLayout_attention:
           replac(new Attention());
           color(1);
           break;
       case R.id.LinearLayout_vip:
           replac(new Vip());
           color(2);
           break;
       case R.id.LinearLayout_my:
           replac(new My());
           color(3);
           break;
   }
    }
    public void control()
    {
        home = (LinearLayout) findViewById(R.id.LinearLayout_home);
        LinearLayout attenton = (LinearLayout) findViewById(R.id.LinearLayout_attention);
        LinearLayout vip = (LinearLayout) findViewById(R.id.LinearLayout_vip);
        LinearLayout my = (LinearLayout) findViewById(R.id.LinearLayout_my);
        TextView home1= (TextView) findViewById(R.id.TextView_home);
        TextView attenton1= (TextView) findViewById(R.id.TextView_attention);
        TextView vip1= (TextView) findViewById(R.id.TextView_vip);
        TextView my1= (TextView) findViewById(R.id.TextView_my);
        arr1 = new TextView[]{home1,attenton1,vip1,my1};
        ImageView shou= (ImageView) findViewById(R.id.ImageView_shou);
        ImageView attention= (ImageView) findViewById(R.id.ImageView_attention);
        ImageView vip2= (ImageView) findViewById(R.id.ImageView_vip);
        ImageView my2= (ImageView) findViewById(R.id.ImageView_my);
        arr2 = new ImageView[]{shou,attention,vip2,my2};
        home.setOnClickListener(this);
        attenton.setOnClickListener(this);
        vip.setOnClickListener(this);
        my.setOnClickListener(this);
        arr = new LinearLayout[]{home,attenton,vip,my};
    }
    public void color(int position)
    {
        for(int i=0;i<arr.length;i++)
        {
           if(position==i)
           {
               arr1[i].setTextColor(Color.RED);
               arr2[i].setImageResource(im1[i]);
           }else
           {
               arr1[i].setTextColor(Color.BLUE);
               arr2[i].setImageResource(im[i]);
           }
        }
    }

}
