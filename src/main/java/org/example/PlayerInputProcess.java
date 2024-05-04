package org.example;

import org.example.errors.PocketIsEmptyException;
import org.example.models.Farm;
import org.example.models.PlaneVector;
import org.example.models.PlayerInventory;
import org.example.models.item.Item;

import java.util.Scanner;

public class PlayerInputProcess {
    private String input;
    private PlayerInventory inventory;
    private Farm farm;
    public PlayerInputProcess() {
        this.inventory = PlayerInventory.getInstance();
        this.farm = Farm.getInstance();
    }

    public void processInput(String input) {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String command = scanner.next();
            try {
                if (command.equals("I")) {
                    processItemUseCommand(scanner);
                } else if (command.equals("Next day")) {
                    advanceDay();
                } else if (command.equals("END")) {
                    break;
                } else {
                    System.out.println("Unknown command");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + e.getMessage());
                break;
            }
        }
        scanner.close();
    }

    private void processItemUseCommand(Scanner scanner) throws Exception {
        int index = scanner.nextInt();
        ensureNextToken(scanner, "P");
        int posX = scanner.nextInt();
        int posY = scanner.nextInt();
        ensureNextToken(scanner, "D");
        int dirX = scanner.nextInt();
        int dirY = scanner.nextInt();
        ensureNextToken(scanner, "L");
        int powerLevel = scanner.nextInt();
        executeCommandI(index, posX, posY, new PlaneVector((double) dirX, (double) dirY), powerLevel);
    }

    private void executeCommandI(int index, int posX, int posY, PlaneVector direction, int powerLevel) {
        if (index < 0 || index >= inventory.getCapacity()) {
            throw new IllegalArgumentException("Invalid index");
        }
        Item item = null;
        try {
            item = inventory.getItem(index);
        } catch (PocketIsEmptyException e) {
            throw new RuntimeException(e);
        }
        PlaneVector position = new PlaneVector((double) posX, (double) posY);
        item.use(farm, position, direction, powerLevel);
        System.out.println("Executed command at " + position + " with power level " + powerLevel);
    }
    private void ensureNextToken(Scanner scanner, String expectedToken) throws IllegalArgumentException {
        String token = scanner.next();
        if (!token.equals(expectedToken)) {
            throw new IllegalArgumentException("Expected '" + expectedToken + "' but found '" + token + "'");
        }
    }
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
