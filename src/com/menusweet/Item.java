package com.menusweet;

public class Item {

    String name, description;
    int price, pictureResId;

    public Item(String name, String description, int price, int resId) {
        this.name = name;
        this.description = description;
        this.price = price < 0 ? 0 : price;
        this.pictureResId = resId;
    }

    public Item(String name, String description, int price) {
        this(name, description, price, 0);
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (pictureResId != item.pictureResId) return false;
        if (price != item.price) return false;
        if (!description.equals(item.description)) return false;
        if (!name.equals(item.name)) return false;

        return true;
    }
}
