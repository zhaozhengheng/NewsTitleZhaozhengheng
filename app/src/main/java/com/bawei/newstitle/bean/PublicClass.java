package com.bawei.newstitle.bean;

/**
 * Created by wdxnrylj on 2017/2/11.
 */

public class PublicClass
{

    public static String[] home_Titles = new String[]{
            "足球", "娱乐", "体育", "财经", "科技",
            "电影", "汽车", "笑话", "游戏", "时尚",
            "情感", "精选", "电台", "NBA", "数码",
            "移动", "彩票", "教育", "论坛", "旅游",
            "手机", "博客", "社会", "家居", "暴雪游戏",
            "亲子", "CBA", "消息", "军事"

    };
    public static String[] videoTitles = new String[]{
            "热点视频","娱乐视频","搞笑视频","精品视频"
    };
    public static String[] home_Titles_ID = new String[]{
            "T1399700447917", "T1348648517839", "T1348649079062", "T1348648756099", "T1348649580692",
            "T1348648650048", "T1348654060988", "T1350383429665", "T1348654151579", "T1348650593803",
            "T1348650839000", "T1370583240249", "T1379038288239", "T1348649145984", "T1348649776727",
            "T1351233117091", "T1356600029035", "T1348654225495", "T1349837670307", "T1348654204705",
            "T1348649654285", "T1349837698345", "T1348648037603", "T1348654105308", "T1397016069906",
            "T1397116135282", "T1348649475931", "T1371543208049", "T1348648141035"};

    public static String[] videoID = new String[]{
            "V9LG4B3A0","V9LG4CHOR","V9LG4E6VR","00850FRB"
    };

    public static String getURL(String id, int index) {
        String url = String.format("http://c.m.163.com/nc/article/headline/%1$s/%2$s-20.html", id, index);
        return url;
    }

    public static String getVideoURL(String id, int index)
    {
        String url = String.format("http://c.3g.163.com/nc/video/list/%1&s/n/%2&s-10.html",id,index);
        return url;
    }
}
