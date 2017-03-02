package com.bawei.newstitle.bean;

/**
 * Created by 1 on 2017/2/22.
 */
public class Vieobean
{
    private String title;
    private String name;
    private String vido;
    private String image;
   private String uri;


    public Vieobean() {
        super();
    }

    public Vieobean(String title, String name, String vido, String image) {
        this.title = title;
        this.name = name;
        this.vido = vido;
        this.image = image;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVido() {
        return vido;
    }

    public void setVido(String vido) {
        this.vido = vido;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Vieobean{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", vido='" + vido + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
