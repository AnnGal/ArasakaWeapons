package art.manguste.android.ArasakaWeapons.data;

abstract class Product {
     String title;
     String description;
     Float price;
     CatalogType type;

    public Product(String title, String description, Float price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
