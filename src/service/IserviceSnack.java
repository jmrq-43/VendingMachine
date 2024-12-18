package service;

import domain.Snack;

import java.util.List;

public interface IserviceSnack {
    void addSnacks(Snack snack);

    void showSnacks();

    List<Snack> getSnacks();
}
