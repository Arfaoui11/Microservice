package io.microservice.moviecatalogservice.models;

public class CatalogItem {

    private String name;
    private String Desc;
    private int rating ;

    public CatalogItem(String name, String desc, int rating) {
        this.name = name;
        this.Desc = desc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
