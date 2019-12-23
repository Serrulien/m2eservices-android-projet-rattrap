package m2.eservices.android.characters.ui;

import javax.inject.Singleton;

import m2.eservices.android.characters.api.model.Character;

public class CharacterMapper {

    public static CharacterViewModel map(Character character) {
        return new CharacterViewModel(
                character.getName(),
                character.getId(),
                character.getImage()
        );
    }

}
