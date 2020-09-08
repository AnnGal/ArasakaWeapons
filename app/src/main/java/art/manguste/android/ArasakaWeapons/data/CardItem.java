package art.manguste.android.ArasakaWeapons.data;

abstract class CardItem {
     String title;
     String description;
     Float price;
     CatalogType type;

    public CardItem(String title, String description, Float price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
