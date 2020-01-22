package m2.eservices.android.characters.ui.ui.character;

import java.io.Serializable;
import java.util.List;

import m2.eservices.android.characters.api.model.Origin;

public class CharacterEpisodesViewModel implements Serializable {
    private List<String> episodes;

    public List<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<String> episodes) {
        this.episodes = episodes;
    }
}
