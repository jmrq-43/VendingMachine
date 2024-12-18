package presentation;

import domain.Snack;
import service.IserviceSnack;
import service.ServiceSnackArchive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class vendingMachine {
    public static void main(String[] args) {
        vendingMachine();
    }

    private static void vendingMachine() {
        var exit = false;
        var console = new Scanner(System.in);

        IserviceSnack iserviceSnack = new ServiceSnackArchive();
        List<Snack> products = new ArrayList<>();
        System.out.println("***Vending Machine***");
        iserviceSnack.showSnacks();

        while (!exit) {
            try {
                var option = showMenu(console);
                exit = runOptions(option, console, products, iserviceSnack);
            } catch (Exception e) {
                System.out.println("An error ocurred " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner console) {
        System.out.println("""
                Menu:
                1. buy snack
                2. show ticket
                3. add new snack
                4. show inventory
                5. exit
                
                choose an option:\s""");
        return Integer.parseInt(console.nextLine());
    }

    private static boolean runOptions(int option, Scanner console,
                                      List<Snack> products,
                                      IserviceSnack iserviceSnack) {
        var getOut = false;
        switch (option) {
            case 1 -> buySnack(console, products, iserviceSnack);
            case 2 -> showTicket(products);
            case 3 -> addNewSnack(console, iserviceSnack);
            case 4 -> listInventorySnack(console, iserviceSnack);
            case 5 -> {
                System.out.println("Leaving...");
                getOut = true;
            }
            default -> System.out.println("Invalid Option " + option);
        }
        return getOut;
    }

    private static void buySnack(Scanner console,
                                 List<Snack> products,
                                 IserviceSnack iserviceSnack) {

        System.out.println("What snack do you want to buy (id)? ");
        var idSnack = Integer.parseInt(console.nextLine());
        var snackFound = false;
        for (var snack : iserviceSnack.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                products.add(snack);
                System.out.println("snack added: " + snack);
                snackFound = true;
                break;
            }
        }
        if (!snackFound) {
            System.out.println("snack id not found: " + idSnack);
        }
    }

    private static void showTicket(List<Snack> products) {
        var ticket = "***Sales ticket***";
        var total = 0.0;
        for (var product : products) {
            ticket += "\n\t" + product.getName() + " - $" + product.getPrice();
            total += product.getPrice();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void addNewSnack(Scanner console,
                                    IserviceSnack iserviceSnack) {
        System.out.println("nombre del nuevo snack ");
        var nombre = console.nextLine();
        System.out.println("precio del Snack");
        var precio = Double.parseDouble(console.nextLine());
        iserviceSnack.addSnacks(new Snack(nombre, precio));
        System.out.println("Sanck agregado correctamente ");
        iserviceSnack.getSnacks();
    }

    private static void listInventorySnack(Scanner console,
                                           IserviceSnack iserviceSnack) {
        iserviceSnack.showSnacks();

    }

}
