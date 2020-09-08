package art.manguste.android.ArasakaWeapons.data;

import art.manguste.android.ArasakaWeapons.R;

public class Product {
    private String title;
    private String shortDescription;
    private String fullDescription;
    private Double price;
    private CatalogType type;
    private int imageResourceId;
    //TODO enum WeaponType

    public Product(String title, String shortDescription, String fullDescription, Double price, CatalogType type, int imageResourceId) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.type = type;
        this.imageResourceId = imageResourceId;
    }


    // rebalanced your implants
    // consult about weapon types
    // your personal guard for an evening/day/even
    // consult hov to build better defence in your home area
    // install your implants by our techs
    // improving wepons skill with in an instructor for your team
    // alert team for rent

    public static final Product[] weapons = {
            new Product("TestWeaponTitle", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle1", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle2", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle3", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle4", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle5", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo)/*,
            new Product("TestWeaponTitle6", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle7", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle8", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo),
            new Product("TestWeaponTitle9", "TestWeaponDesc", "TestWeaponDescFull", 5500d, CatalogType.WEAPON, R.drawable.arasaka_logo)*/
    };

    public static final Product[] services = {
            new Product("TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product("TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
            new Product("TestServiceTitle", "TestServiceDesc", "TestServiceDescFull", 75500d, CatalogType.SERVICE, R.drawable.arasaka_logo)
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

    public CatalogType getType() {
        return type;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
