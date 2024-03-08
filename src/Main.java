import controller.UserController;
import view.View;

import java.util.Scanner;

public class Main {
    private final static UserController userController = new UserController();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            View.UI();
            System.out.print("choose: ");
            int op = scanner.nextInt();
            switch (op){
                case 1 ->{
                    userController.getAllUsers().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Enter Id to search: ");
                    System.out.println(userController.searchById(scanner.nextInt()));
                }
                default -> {
                    System.out.println("invalid..");
                    return;
                }
            }
        }


    }
}