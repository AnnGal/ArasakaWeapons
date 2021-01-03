package art.manguste.android.arasakaWeapons.core

/**
 * What kind of weapon it is
 */
enum class WeaponType(private val label: String) {
    SMART("smart weapon"),
    MELEE("melee weapon"),
    CYBERWARE("cyberware"),
    HEAVY("heavy weapon"),
    SUBMACHINE("submachine gun"),
    AUTOPISTOL("auto pistol"),
    ASSAULTRIFLE("assault rifle"),
    NONE("");

    override fun toString(): String {
        return label
    }
}