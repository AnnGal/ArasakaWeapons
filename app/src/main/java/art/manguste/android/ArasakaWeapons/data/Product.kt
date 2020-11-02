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
    /*
    var id: Int = 0
        private set
    var title: String = ""
        private set
    var shortDescription: String = ""
        private set
    var fullDescription: String = ""
        private set
    var price: Double = 0.0
        private set
    private var type: CatalogType? = null
    var imageResourceId: Int = 0
        private set

    *//*    public CatalogType getType() {
        return type;
    }*//*  var weaponType: WeaponType? = null
        private set
    var iconResourceId = 0
        private set*/

/*
    @JvmOverloads
    constructor(id: Int, title: String, shortDescription: String, fullDescription: String, price: Double,
                type: CatalogType, imageResourceId: Int, weaponType: WeaponType = WeaponType.NONE, iconResourceId: Int = 0) : this() {
        this.id = id
        this.title = title
        this.shortDescription = shortDescription
        this.fullDescription = fullDescription
        this.price = price
        this.type = type
        this.imageResourceId = imageResourceId
        this.weaponType = weaponType
        // if no special icon - use full image
        if (iconResourceId == 0) {
            this.iconResourceId = imageResourceId
        } else {
            this.iconResourceId = iconResourceId
        }
    }
*/

    val priceString: String
        get() = DecimalFormat(decimalPattern).format(price)

    override fun describeContents(): Int {
        return 0
    }

    /*override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(title)
        dest.writeString(shortDescription)
        dest.writeString(fullDescription)
        dest.writeValue(price)
        dest.writeInt(if (type == null) -1 else type!!.ordinal)
        dest.writeInt(imageResourceId)
        dest.writeInt(if (weaponType == null) -1 else weaponType!!.ordinal)
    }*/

    /*protected constructor(incomeParcel: Parcel) : this() {
        id = incomeParcel.readInt()
        title = incomeParcel.readString()
        shortDescription = incomeParcel.readString()
        fullDescription = incomeParcel.readString()
        price = incomeParcel.readValue(Double::class.java.classLoader) as Double
        val tmpType = incomeParcel.readInt()
        type = if (tmpType == -1) null else CatalogType.values()[tmpType]
        imageResourceId = incomeParcel.readInt()
        val tmpWeaponType = incomeParcel.readInt()
        weaponType = if (tmpWeaponType == -1) null else WeaponType.values()[tmpWeaponType]
    }*/

    fun getTotalPriceString(count: Int): String {
        return DecimalFormat(decimalPattern).format(price * count)
    }

    companion object {
        lateinit var weapons: Array<Product>
        lateinit var services: Array<Product>

        const val decimalPattern = "##.##"

    /*    val CREATOR: Parcelable.Creator<Product?> = object : Parcelable.Creator<Product?> {
            override fun createFromParcel(source: Parcel): Product? {
                return Product(source)
            }

            override fun newArray(size: Int): Array<Product?> {
                return arrayOfNulls(size)
            }
        }*/

    }



    /*

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.shortDescription = in.readString();
        this.fullDescription = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : CatalogType.values()[tmpType];
        this.imageResourceId = in.readInt();
        int tmpWeaponType = in.readInt();
        this.weaponType = tmpWeaponType == -1 ? null : WeaponType.values()[tmpWeaponType];
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };*
    * */
}