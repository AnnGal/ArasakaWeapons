package art.manguste.android.ArasakaWeapons.Util;

import androidx.annotation.NonNull;

/**
 * What kind of weapon it is
 */
public enum WeaponType {
    SMART("smart weapon"),
    MELEE("melee weapon"),
    CYBERWARE("cyberware"),
    HEAVY("heavy weapon"),
    SUBMACHINE("submachine gun"),
    AUTOPISTOL("auto pistol"),
    ASSAULTRIFLE("assault rifle"),
    NONE("");

    private String label;

    WeaponType(String label) {
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return label;
    }
}
