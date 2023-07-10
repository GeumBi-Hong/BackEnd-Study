package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverages.Americano;

class CafekioskTest {

    @Test
    void add(){
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano());

        System.out.println(">>>담긴  음료 수 :" + cafekiosk.getBeverageList().size());
        System.out.println(">>>담긴  음료 수 :" + cafekiosk.getBeverageList().get(0).getName());

    }

}