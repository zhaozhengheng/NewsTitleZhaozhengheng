package com.bawei.newstitle.Utils;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by 1 on 2017/2/16.
 */
public class utils extends Application
{
    public static boolean flas;
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        flas=true;
        ImageLoaderConfiguration im=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(im);
        // 默认设置为日间模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }
}
