package m2.eservices.android.characters.ui.ui.character;

import m2.eservices.android.characters.api.model.Character;

public class CharacterToCharacterEpisodesViewModelMapper {

    public static CharacterEpisodesViewModel map(Character character) {
        CharacterEpisodesViewModel viewModel = new CharacterEpisodesViewModel();
        viewModel.setEpisodes(character.getEpisode());
        return viewModel;
    }

}
