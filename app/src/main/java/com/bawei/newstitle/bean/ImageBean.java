package com.bawei.newstitle.bean;

/**
 * Created by 1 on 2017/2/17.
 */
public class ImageBean
{
    private String image;

    public ImageBean(String image)
    {
        this.image = image;
    }

    public ImageBean() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "image='" + image + '\'' +
                '}';
    }
}
