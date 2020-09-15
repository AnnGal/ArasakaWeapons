package art.manguste.android.ArasakaWeapons.data;

public enum WeaponType {
    SMART("smart weapon"),
    MELEE("melee weapon"),
    CYBERWARE("cyberware"),
    HEAVY("heavy weapon"),
    SUBMACHINE("submachine gun"),
    AUTOPISTOL("auto pistol"),
    ASSAULTRIFLE("Assault rifle"),
    NONE("");

    private String label;

    WeaponType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
