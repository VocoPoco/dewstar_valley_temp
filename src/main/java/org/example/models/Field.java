package org.example.models;

import org.example.models.crops.Crop;
import org.example.models.crops.FarmCrop;
import org.example.models.enums.FieldState;
import org.example.models.enums.ToolType;
import org.example.models.item.Tool;

public class Field {
    private FieldState state;
    private Crop content;

    public Field() {
        this.state = FieldState.FREE;
        this.content = null;
    }

    public void applyTool(Tool tool) throws Exception {
        switch (this.state) {
            case FREE:
                if (tool.getToolType() == ToolType.HOE) {
                    state = FieldState.DUG_UP;
                } else {
                    throw new Exception("Invalid tool for FREE state.");
                }
                break;
            case BLOCKED:
                throw new Exception("No tool is valid on BLOCKED cells.");
            case DUG_UP:
                throw new Exception("No tool is valid on DUG UP cells.");
            case SOWN:
                Crop crop = this.content;
                int status = crop.applyTool(tool);
                switch (status) {
                    case -1:
                    case 0:
                        setState(FieldState.DUG_UP);
                    case 2:
                        if (crop instanceof FarmCrop) {
                            setState(FieldState.DUG_UP);
                        }
                }
        }
    }

    public FieldState getState() {
        return this.state;
    }

    public Crop getContent() {
        return this.content;
    }

    public void setState(FieldState state) {
        this.state = state;
    }

    public void setContent(Crop crop) {
        this.content = crop;
    }
}
