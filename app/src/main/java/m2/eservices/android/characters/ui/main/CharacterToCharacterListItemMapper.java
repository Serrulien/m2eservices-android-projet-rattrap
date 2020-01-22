package m2.eservices.android.characters.ui.main;

import m2.eservices.android.characters.api.model.Character;

public class CharacterToCharacterListItemMapper {

    public static CharacterListItemViewModel map(Character character) {
        return new CharacterListItemViewModel(
                character.getName(),
                character.getId(),
                character.getImage()
        );
    }

}
