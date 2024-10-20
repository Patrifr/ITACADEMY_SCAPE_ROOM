package logic;

import utils.Helper;

public class Menu {

    private ScapeRoom scapeRoom = ScapeRoom.getInstance();

    //tots els mètodes cridats són d'ScapeRoom

    public static void start() {
        int option = 0;

        do {
            option = menu();
            switch(option) {
                case 0:
                    System.out.println("You are exiting the system. Goodbye!");
                    break;
                case 1:
                    //newRoom();
                    break;
                case 2:
                    //addClues();
                    break;
                case 3:
                   // addDecoItems();
                    break;
                case 4:
                    //showInventory();
                    break;
                case 5:
                    //removeFromInventory();
                    break;
                case 6:
                    //removeFromInventory();
                    break;
                case 7:
                    //removeFromInventory();
                    break;
                case 8:
                    //removeFromInventory();
                    break;
                case 9:
                    //removeFromInventory();
                    break;
                default:
                    System.out.println("Only numbers from 0 to 8 are valid answers. Please, try again.");
            }
        } while(option != 0);

    }

    private static int menu() {
        int option = 0;
        option = Helper.readInt("Choose the action:\n"
                + "1. Create a new room."
                + "\n2. Add clues to a room."
                + "\n3. Add decoration objects to a room."
                + "\n4. Show updated inventory."
                + "\n5. Remove an item from the inventory."
                + "\n6. Manage the accounts." //generate new invoice, show total profits
                + "\n7. Generate newsletter."
                + "\n8. Add new customer to the newsletter."
                + "\n0. Exit.");
        return option;
    }
}
