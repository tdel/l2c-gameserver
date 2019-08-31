package model.entity.enumerate;

public enum CharacterSex {
    MALE, FEMALE;

    static public CharacterSex fromByte(byte _sex) {
        if (_sex == 0) {
            return CharacterSex.MALE;
        }

        return CharacterSex.FEMALE;
    }
}
