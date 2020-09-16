package art.manguste.android.ArasakaWeapons.data;

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
    // TODO make ru localization

    public static final Product[] weapons = {
            new Product(10104,"Gorilla arms",
                    "Classic and reliable replacements for natural limbs, improving both strength and endurance",
                    "Outfitted with kinetic energy recovery system.\nClassic and reliable replacements for natural limbs, improving both strength and endurance",
                    5500d, CatalogType.WEAPON, R.drawable.gorilla_arms, WeaponType.CYBERWARE),
            new Product(10105,"Cyberdyn M122", "An extremely short assault rifle intended for vehicles crews",
                    "The great successor of legendary M22. \nAn extremely short assault rifle intended for vehicles crews. \n"+
                            "It's equipped with an integrated laser sight, tactical light, aim point and silencer",
                    5500d, CatalogType.WEAPON, R.drawable.cyberdyn, WeaponType.ASSAULTRIFLE),
            new Product(10106,"Projectile Launch System",
                    "A varied warhead selection.",
                    "A varied warhead selection.\nA missile launcher in the palm of your hand. More or less literally",
                    5500d, CatalogType.WEAPON, R.drawable.launch_system, WeaponType.CYBERWARE),
            new Product(10107,"\"New-in-town\" KIT",
                    "WSA Automatic Pistol plus two types of ammunition, against mechanoids and armored opponents",
                    "Standard-issue sidearm for all Arasaka troops and executives. WSA Automatic Pistol comes with two types of ammunition, against mechanoids and armored opponents",
                    5500d, CatalogType.WEAPON, R.drawable.wsa_pistol, WeaponType.AUTOPISTOL),
            new Product(10103,
                    "thermal katana",
                    "Nano filament heated blade",
                    "Nano filament heated blade. \n"+
                            "The purest essence of a katana. No add-ons, no modifications, only ripping hot steel",
                    5500d, CatalogType.WEAPON, R.drawable.thermal_katana, WeaponType.MELEE),
            new Product(10101,
                    "Mantis Blades",
                    "Modular design with swappable blade edges. Arm blades designed with lethality and concealment in mind",
                    "Modular design with swappable blade edges. Arm blades designed with lethality and concealment in mind.\nAs effective as they are flashy",
                    5500d, CatalogType.WEAPON, R.drawable.mantisblades, WeaponType.CYBERWARE),
            new Product(10102,
                    "TKI-20 Shingen",
                    "Two mode - burst fire and micro guided missile.",
                    "Two mode - burst fire and micro guided missile.\n" +
                            "Smart weapons require a smart link to unlock the full potential of their targeting systems",
                    5500d, CatalogType.WEAPON, R.drawable.tki_20_shingen, WeaponType.SUBMACHINE),
            new Product(10108,"Setsuko-Arasaka \"P.M.S.\"",
                    "Fires a subsonic, caseless 7mm bullet paired with a built-in Sonex suppressor",
                    "Designed for low-profile corporate security and paramilitary forces where discretion is valued over raw firepower.\n"+
                            "The PMS fires a subsonic, caseless 7mm bullet paired with a built-in Sonex suppressor",
                    5500d, CatalogType.WEAPON, R.drawable.pms, WeaponType.SUBMACHINE)
    };

    // TODO add pics
    public static final Product[] services = {
            new Product(20001,"Rebalancing implants",
                    "Within two hours we will conduct a full analysis of your implants and correct their behavior",
                    "Within two hours we will conduct a full analysis of your implants and correct their behavior",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20001,"Rent a guard",
                    "Your personal guard for an evening/day/event",
                    "Your personal guard for an evening/day/event",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20001,"Home security consultation",
                    "Consult how to build the best protection of your home area",
                    "Consult how to build the best protection of your home area",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20001,"Install implants",
                    "Install your implants with the help of our best specialists",
                    "Install your implants with the help of our best specialists",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20001,"Train team",
                    "Improving weapons skill with the best instructors for your team",
                    "Improving weapons skill with the best instructors for your team",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product(20001,"Control area",
                    "Special trained team for control the area and suppress riots",
                    "Special trained team for control the area and suppress riots",
                    75500d, CatalogType.SERVICE, R.drawable.arasaka_logo)
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
