package service;

import domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceSnackArchive implements IserviceSnack {
    private final String NAME_ARCHIVE = "snacks.txt";

    private List<Snack> snacks = new ArrayList<>();

    public ServiceSnackArchive() {
        var archive = new File(NAME_ARCHIVE);
        var exits = false;

        try {
            exits = archive.exists();
            if (exits) {
                this.snacks = methodGetSnacks();
            } else {
                var exit = new PrintWriter(new FileWriter(archive));
                exit.close();
                System.out.println("file has been created");
            }
        } catch (Exception e) {
            System.out.println("Error creating file " + e.getMessage());
        }

        if (!exits) {
            loadInitialFiles();
        }
    }

    private void loadInitialFiles() {
        this.addSnacks(new Snack("coke", 80));
        this.addSnacks(new Snack("chocolate", 40));
        this.addSnacks(new Snack("fries", 30));
    }

    private List<Snack> methodGetSnacks() {
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(NAME_ARCHIVE));
            for (String line : lines) {
                String[] lineSnack = line.split(",");
                var idSnack = lineSnack[0];
                var name = lineSnack[1];
                var price = Double.parseDouble(lineSnack[2]);
                var snack = new Snack(name, price);
                snacks.add(snack);
            }
        } catch (Exception e) {
            System.out.println("Error reading snack file" + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }

    @Override
    public void showSnacks() {
        System.out.println("---- snacks in inventory ----");
        var inventorySnack = "";
        for (Snack snack : snacks) {
            inventorySnack += snack.toString() + "\n";
        }
        System.out.println(inventorySnack);
    }

    @Override
    public void addSnacks(Snack snack) {
        this.snacks.add(snack);
        this.addSnacksArchive(snack);
    }

    private void addSnacksArchive(Snack snack) {
        boolean append = false;
        var archive = new File(NAME_ARCHIVE);
        try {
            append = archive.exists();
            var exit = new PrintWriter(new FileWriter(archive, append));
            exit.println(snack.writeSnack());
            exit.close();
        } catch (Exception e) {
            System.out.println("Error adding snack " + e.getMessage());
        }
    }
}
