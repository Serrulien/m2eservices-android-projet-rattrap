package android.eservices.rawgytbmonitor.data.api.model;

import com.google.gson.annotations.SerializedName;

public class Game {
    private int id;
    private String name;
    private float rating;
    @SerializedName("background_image")
    private String image;

    private boolean isFavorite;

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
