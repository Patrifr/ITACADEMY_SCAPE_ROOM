package logic;

import utils.Helper;

public class Menu {

    private final static ScapeRoom scapeRoom = ScapeRoom.getInstance();

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
                    scapeRoom.addDecoToRoom();
                    break;
                case 6:
                    scapeRoom.showInventory();
                    break;
                case 7:
                    scapeRoom.removeElements();
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
                + "\n5. Add decoration items to a room."
                + "\n6. Show updated inventory."
                + "\n7. Remove an element from the inventory."
                + "\n8. Manage the accounts."
                + "\n9. Register a new Customer."
                + "\n10. Customer's management."
                + "\n0. Exit.");
        return option;
    }
}
