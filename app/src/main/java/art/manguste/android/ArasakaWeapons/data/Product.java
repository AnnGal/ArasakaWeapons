package art.manguste.android.ArasakaWeapons.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

import art.manguste.android.ArasakaWeapons.R;

public class Product implements Parcelable {

    private final String decimalPattern = "##.##";

    private Integer id;
    private String title;
    private String shortDescription;
    private String fullDescription;
    private Double price;
    private CatalogType type;
    private int imageResourceId;
    private WeaponType weaponType;

    public Integer getId() {
        return id;
    }

    public Product(Integer id, String title, String shortDescription, String fullDescription, Double price, CatalogType type, int imageResourceId, WeaponType weaponType) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.type = type;
        this.imageResourceId = imageResourceId;
        this.weaponType = weaponType;
    }

    public Product(Integer id, String title, String shortDescription, String fullDescription, Double price, CatalogType type, int imageResourceId) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.type = type;
        this.imageResourceId = imageResourceId;
        this.weaponType = WeaponType.NONE;
    }


    // rebalanced your implants
    // consult about weapon types
    // your personal guard for an evening/day/even
    // consult hov to build better defence in your home area
    // install your implants by our techs
    // improving wepons skill with in an instructor for your team
    // alert team for rent

    public static final Product[] weapons = {
            new Product(10101,"TestWeaponTitle", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo, WeaponType.MELEE),
            new Product(10102,"TestWeaponTitle1", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.ic_cart, WeaponType.HEAVY),
            new Product(10103,"TestWeaponTitle2", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo, WeaponType.NONE),
            new Product(10104,"TestWeaponTitle3", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo, WeaponType.MELEE),
            new Product(10105,"TestWeaponTitle4", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo, WeaponType.MELEE),
            new Product(10106,"TestWeaponTitle5", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo, WeaponType.MELEE)
    };

    public static final Product[] services = {
            new Product(20001,"TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20002,"TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20003,"TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo)
    };

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceString() {
        return new DecimalFormat(decimalPattern).format(price);
    }

    public CatalogType getType() {
        return type;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.shortDescription);
        dest.writeString(this.fullDescription);
        dest.writeValue(this.price);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeInt(this.imageResourceId);
        dest.writeInt(this.weaponType == null ? -1 : this.weaponType.ordinal());
    }

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
    };

    public String getTotalPriceString(int count) {
        return new DecimalFormat(decimalPattern).format(price * count);
    }
}
