package logic;

import utils.Helper;

public class Menu {

    private static ScapeRoom scapeRoom = ScapeRoom.getInstance();

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
                    scapeRoom.newRoom();
                    break;
                case 2:
                    scapeRoom.newClue();
                    break;
                case 3:
                    scapeRoom.addClueToRoom();
                    break;
                case 4:
                    scapeRoom.newDeco();
                    break;
                case 5:
                    //add deco to a room
                    break;
                case 6:
                    scapeRoom.showInventory();
                    break;
                case 7:
                    //removeFromInventory();
                    break;
                case 8:
                    scapeRoom.accountManagement();
                    break;
                case 9:
                    scapeRoom.newCustomer();
                    break;
                case 10:
                    scapeRoom.customerMenu();
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
                + "\n2. Create a new clue."
                + "\n3. Add clues to a room."
                + "\n4. Create a new decoration item."
                + "\n5. Add decoration item to a room."
                + "\n6. Show updated inventory."
                + "\n7. Remove an item from the inventory."
                + "\n8. Manage the accounts." //generate new invoice, show total profits
                + "\n9. Create new Customer."
                + "\n10. Customer's management."
                + "\n0. Exit.");
        return option;
    }
}
