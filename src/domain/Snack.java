package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class Snack implements Serializable {
    private static int countSnack = 0;
    private int idSnack;
    private String name;
    private double price;

    public Snack() {
        this.idSnack = ++Snack.countSnack;
    }

    public Snack(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public static int getCountSnack() {
        return countSnack;
    }

    public static void setCountSnack(int countSnack) {
        Snack.countSnack = countSnack;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(int idSnack) {
        this.idSnack = idSnack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String writeSnack() {
        return idSnack + "," + name + "," + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Snack snack = (Snack) obj;
        return idSnack == snack.idSnack && Double.compare(price, snack.price) == 0 && Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, name, price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Snack.class.getSimpleName() + "[", "]")
                .add("idSnack=" + idSnack)
                .add("name='" + name + "'")
                .add("price=" + price)
                .toString();
    }
}
