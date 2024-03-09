import controller.UserController;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;
import java.util.Scanner;

import view.MenuView;

public class Main {
    private final static UserController userController = new UserController();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            MenuView.menu();
            System.out.print("choose: ");
            int op = scanner.nextInt();
            switch (op){
                case 1 ->{
                    Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
                    table.setColumnWidth(0,20,30);
                    table.setColumnWidth(1,20,30);
                    table.setColumnWidth(2,20,30);
                    table.setColumnWidth(3,20,30);
                    table.setColumnWidth(4,20,30);
                    table.setColumnWidth(5,20,30);
                    table.setColumnWidth(6,20,30);
                    // header of table
                    table.addCell("Id");
                    table.addCell("UUId");
                    table.addCell("User Name");
                    table.addCell("User Email");
                    table.addCell("User Password");
                    table.addCell("Is Deleted");
                    table.addCell("Is Verified");
                    // body of table
                    userController.getAllUsers().forEach(e -> {
                        table.addCell(e.getUserId().toString());
                        table.addCell(e.getUserUuid());
                        table.addCell(e.getUserName());
                        table.addCell(e.getUserEmail());
                        table.addCell(e.getUserPassword());
                        table.addCell(e.getIsDeleted().toString());
                        table.addCell(e.getIsVerified().toString());
                    });
                    System.out.println(table.render());


                    //userController.getAllUsers().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Enter Id to search: ");
                    System.out.println(userController.searchById(scanner.nextInt()));
                }
                case 3 -> {
                    System.out.println("3.Create users");
                }
                case 4 -> {
                    System.out.println("4.Read users");
                }
                case 5 -> {
                    System.out.println("5.Update users");
                }
                case 6 -> {
                    System.out.println("6.Delete users");
                }
                case 7 -> {
                    System.out.println("7.Exit Application");
                    System.exit(0);
                }
                default -> {
                    System.out.println("invalid...!");
                }
            }
        }


    }
}