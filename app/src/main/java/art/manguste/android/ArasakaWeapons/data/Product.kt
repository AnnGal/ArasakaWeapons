package art.manguste.android.ArasakaWeapons.data

import android.os.Parcelable
import art.manguste.android.ArasakaWeapons.Util.CatalogType
import art.manguste.android.ArasakaWeapons.Util.WeaponType
import kotlinx.android.parcel.Parcelize
import java.text.DecimalFormat

/**
 * Class for items in store. Can be different types, depends of [WeaponType].
 */
@Parcelize
data class Product(var id: Int, var title: String, var shortDescription: String,
                   var fullDescription: String, var price: Double,
                   var type: CatalogType,
                   var imageResourceId: Int,
                   var weaponType: WeaponType = WeaponType.NONE,
                   var iconResourceId: Int) : Parcelable {

    val priceString: String
        get() = DecimalFormat(decimalPattern).format(price)

    override fun describeContents(): Int {
        return 0
    }

    fun getTotalPriceString(count: Int): String {
        return DecimalFormat(decimalPattern).format(price * count)
    }

    companion object {
        lateinit var weapons: Array<Product>
        lateinit var services: Array<Product>
        const val decimalPattern = "##.##"
    }

}