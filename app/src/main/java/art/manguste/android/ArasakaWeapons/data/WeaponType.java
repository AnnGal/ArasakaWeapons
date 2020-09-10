package art.manguste.android.ArasakaWeapons.data;

public enum WeaponType {
    SMART("smart weapon"),
    MELEE("melee weapon"),
    CYBERWARE("cyberware"),
    HEAVY("heavy weapon"),
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
