package com.bawei.newstitle.bean;

import java.util.List;

/**
 * Created by 1 on 2017/2/17.
 */
public class ImageTitleBean
{
    private String title;
    private String source;
    private String imgsrc;
    private String uri;
    private List<ImageBean> imgextra;

    public ImageTitleBean(String title, String source, String imgsrc, String uri, List<ImageBean> imgextra) {
        this.title = title;
        this.source = source;
        this.imgsrc = imgsrc;
        this.uri = uri;
        this.imgextra = imgextra;
    }

    public ImageTitleBean() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<ImageBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImageBean> imgextra) {
        this.imgextra = imgextra;
    }

    @Override
    public String toString() {
        return "ImageTitleBean{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", uri='" + uri + '\'' +
                ", imgextra=" + imgextra +
                '}';
    }
}
