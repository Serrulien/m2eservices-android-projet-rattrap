package m2.eservices.android.characters.ui.main;

public class CharacterListItemViewModel {
    private String name;
    private int id;
    private String imgUrl;

    public CharacterListItemViewModel(String name, int id, String imgUrl) {
        this.name = name;
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
