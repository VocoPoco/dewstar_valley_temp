package org.example.models;

import org.example.errors.ItemAlreadyExistsException;
import org.example.errors.NoPocketsAvailableException;
import org.example.errors.PocketIsEmptyException;
import org.example.models.item.Item;
import org.example.models.item.SeedBag;
import org.example.models.item.Tool;

import java.util.ArrayList;

public class PlayerInventory {
    private ArrayList<Item> pockets;
    private static final Integer CAPACITY = 10;
    private static PlayerInventory instance;

    private PlayerInventory() {
        this.pockets = new ArrayList<>(CAPACITY);
    }

    public static synchronized PlayerInventory getInstance() {
        if (instance == null) {
            instance = new PlayerInventory();
        }
        return instance;
    }

    public void addItem(Item item) throws ItemAlreadyExistsException, NoPocketsAvailableException {
        if (this.pockets.size() >= CAPACITY) {
            throw new NoPocketsAvailableException("Inventory is full. Add failed!");
        }

        for (Item existingItem : pockets) {
            if (existingItem.getName().equals(item.getName())) {
                if (existingItem instanceof Tool && item instanceof Tool) {
                    throw new ItemAlreadyExistsException("Tool already exists. Add failed!");
                }
                if (existingItem instanceof SeedBag existingSeedBag && item instanceof SeedBag newSeedBag) {
                    existingSeedBag.setQuantity(existingSeedBag.getQuantity() + newSeedBag.getQuantity());
                    return;
                }
            }
        }
        this.pockets.add(item);
    }

    public void removeItem(Item item) {
        this.pockets.remove(item);
    }

    public Item getItem(int index) throws PocketIsEmptyException {
        if (index >= CAPACITY || index < 0) {
            throw new IndexOutOfBoundsException("No such item in the inventory!");
        }
        if (this.pockets.get(index) == null) {
            throw new PocketIsEmptyException("Inventory pocket is currently empty");
        }
        return this.pockets.get(index);
    }

    public ArrayList<Item> getPockets() {
        return new ArrayList<>(this.pockets);
    }

    public Integer getCapacity() {
        return CAPACITY;
    }
}
