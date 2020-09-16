package art.manguste.android.ArasakaWeapons.data;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

import art.manguste.android.ArasakaWeapons.R;

public class Product implements Parcelable {

    private final String decimalPattern = "##.##";
    public static Product[] weapons;
    public static Product[] services;

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

/*    public CatalogType getType() {
        return type;
    }*/

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
