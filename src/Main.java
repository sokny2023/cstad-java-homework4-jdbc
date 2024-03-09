import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.Scanner;
import java.util.UUID;

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
                    Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
                    CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                    table.setColumnWidth(0,10,15);
                    table.setColumnWidth(1,20,30);
                    table.setColumnWidth(2,20,30);
                    table.setColumnWidth(3,20,30);
                    table.setColumnWidth(4,10,15);
                    table.setColumnWidth(5,15,20);
                    table.setColumnWidth(6,15,20);
                    // header of table
                    table.addCell("Id", cellStyle);
                    table.addCell("UUId", cellStyle);
                    table.addCell("User Name", cellStyle);
                    table.addCell("User Email", cellStyle);
                    table.addCell("User Password", cellStyle);
                    table.addCell("Is Deleted", cellStyle);
                    table.addCell("Is Verified", cellStyle);
                    // body of table
                    userController.getAllUsers().forEach(e -> {
                        table.addCell(e.getUserId().toString(), cellStyle);
                        table.addCell(e.getUserUuid(), cellStyle);
                        table.addCell(e.getUserName(), cellStyle);
                        table.addCell(e.getUserEmail() ,cellStyle);
                        table.addCell(e.getUserPassword(), cellStyle);
                        table.addCell(e.getIsDeleted().toString(), cellStyle);
                        table.addCell(e.getIsVerified().toString(), cellStyle);
                    });
                    System.out.println(table.render());
                }
                case 2 -> {
                    System.out.print("Enter Id to search: ");
                    System.out.println(userController.searchById(scanner.nextInt()));
                }
                case 3 -> {
                    System.out.println("3.Create users");
                    System.out.print("Enter id: ");
                    int id =scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name :");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    User newUser = new User(id, UUID.randomUUID().toString(),name,email,password,false,false);

                    userController.createUser(newUser);
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