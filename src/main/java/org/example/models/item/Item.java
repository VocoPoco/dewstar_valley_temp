package org.example.models.item;

import org.example.models.Farm;
import org.example.models.PlaneVector;
import org.example.models.enums.ItemType;

abstract public class Item {
    private final String name;
    private final ItemType itemType;
    Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    protected abstract void use(Farm farm, PlaneVector position) throws Exception;
    protected abstract void use(Farm farm, PlaneVector position, PlaneVector direction, int powerLevel) throws Exception;


    public String getName() {
        return name;
    }

    public ItemType getType() {
        return itemType;
    }
}

