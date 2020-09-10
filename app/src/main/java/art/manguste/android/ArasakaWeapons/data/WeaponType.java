package art.manguste.android.ArasakaWeapons.data;

public enum WeaponType {
    SMART("smart weapon"),
    MELEE("melee weapon"),
    CYBERWARE("cyberware"),
    HEAVY("heavy weapon");

    private String label;

    WeaponType(String label) {
        this.label = label;
    }

    public String getTitle() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
