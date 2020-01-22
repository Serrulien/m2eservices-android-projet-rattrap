package m2.eservices.android.characters.ui.ui.character;

import m2.eservices.android.characters.api.model.Character;
import m2.eservices.android.characters.ui.main.CharacterListItemViewModel;

public class CharacterToCharacterViewModelMapper {

    public static CharacterViewModel map(Character character) {
        CharacterViewModel viewModel = new CharacterViewModel();
        viewModel.setName(character.getName());
        viewModel.setGender(character.getGender());
        viewModel.setImageUrl(character.getImage());
        viewModel.setOrigin(character.getOrigin());
        viewModel.setStatus(character.getStatus());
        return viewModel;
    }

}
