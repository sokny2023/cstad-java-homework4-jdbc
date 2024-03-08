package view;

import controller.UserController;

public class View {
    private final UserController userController = new UserController();
    public static void UI(){
        System.out.println(".".repeat(70));
        System.out.println(".".repeat(20)+" User Data ".toUpperCase()+".".repeat(20));
        System.out.println(".".repeat(70));
        System.out.println(" 1. Get all users");
        System.out.println(" 2. Search by id");
    }
}
