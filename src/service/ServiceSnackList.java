package service;

import domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServiceSnackList implements  IserviceSnack {
    private static List<Snack> snacks;

    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("coke", 80));
        snacks.add(new Snack("chocolate", 40));
        snacks.add(new Snack("fries", 30));
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }

    @Override
    public void showSnacks() {
        var inventorySnacks = "";
        for (Snack snack : snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println("---- snack in inventory ----");
        System.out.println(inventorySnacks);
    }

    @Override
    public void addSnacks(Snack snack) {
        snacks.add(snack);
    }
}
