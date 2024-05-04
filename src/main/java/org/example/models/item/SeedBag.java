package org.example.models.item;

import org.example.models.Field;
import org.example.models.PlaneVector;
import org.example.models.Farm;
import org.example.models.PlayerInventory;
import org.example.models.crops.Crop;
import org.example.models.crops.FarmCrop;
import org.example.models.crops.FarmTree;
import org.example.models.enums.ItemType;

public class SeedBag extends Item {
    private Integer quantity;
    private final Crop seedType;
    public SeedBag(String name, Crop seedType, int quantity) {
        super(name, ItemType.BAG_OF_SEEDS);
        this.seedType = seedType;
        this.quantity = quantity;
    }

    @Override
    public void use(Farm farm, PlaneVector position) throws Exception {
        Field field = farm.getField(position);
        switch (field.getState()) {
            case FREE:
                throw new Exception("Cannot plant on non dug-up fields. ");
            case BLOCKED:
                throw new Exception("Field blocked. ");
            case SOWN:
                throw new Exception("Field already occupied. ");
            case DUG_UP:
                if (seedType instanceof FarmCrop) {
                    field.setContent(new FarmCrop(getName(), 0, 1));
                } else {
                    field.setContent(new FarmTree(getName(), 0, 10));
                }
                setQuantity(this.quantity--);
        }
    }

    @Override
    public void use(Farm farm, PlaneVector position, PlaneVector direction, int powerLevel) {
        try {
            use(farm, position);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity > 0 ) {
            this.quantity = quantity;
        } else if (quantity == 0) {
            PlayerInventory.getInstance().removeItem(this);
        } else {
            throw new IllegalArgumentException("Cannot add negative seed quantity to the bag");
        }
    }

    public Crop getSeedType() {
        return seedType;
    }
}
