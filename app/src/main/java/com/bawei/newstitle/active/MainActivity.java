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
import android.widget.TextView;

import com.bawei.newstitle.R;
import com.bawei.newstitle.frament.Attention;
import com.bawei.newstitle.frament.Home;
import com.bawei.newstitle.frament.My;
import com.bawei.newstitle.frament.Vip;

import org.w3c.dom.Text;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private TextView[] arr;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control();
        replace(new Home());
        color(0);

    }
    public void replace(Fragment ff)
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
       case R.id.TextView_home:
           replace(new Home());
           color(0);
           break;
       case R.id.TextView_attention:
           replace(new Attention());
           color(1);
           break;
       case R.id.TextView_vip:
           replace(new Vip());
           color(2);
           break;
       case R.id.TextView_my:
           replace(new My());
           color(3);
           break;
   }
    }
    public void control()
    {
        TextView home = (TextView) findViewById(R.id.TextView_home);
        TextView attenton = (TextView) findViewById(R.id.TextView_attention);
        TextView vip = (TextView) findViewById(R.id.TextView_vip);
        TextView my = (TextView) findViewById(R.id.TextView_my);
        home.setOnClickListener(this);
        attenton.setOnClickListener(this);
        vip.setOnClickListener(this);
        my.setOnClickListener(this);
        arr = new TextView[]{home,attenton,vip,my};
    }
    public void color(int position)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(i==position)
            {
                arr[i].setTextColor(Color.RED);
            }else
            {
                arr[i].setTextColor(Color.BLUE);
            }
        }
    }

}
