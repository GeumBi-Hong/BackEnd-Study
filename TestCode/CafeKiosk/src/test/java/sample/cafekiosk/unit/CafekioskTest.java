package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverages.Americano;
import sample.cafekiosk.unit.beverages.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafekioskTest {

    @Test
    void add_manual_test(){
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano());

       // System.out.println(">>>담긴  음료 수 :" + cafekiosk.getBeverageList().size());
        // System.out.println(">>>담긴  음료 수 :" + cafekiosk.getBeverageList().get(0).getName());

    }

    @Test
    void add(){
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano());

        //assertThat(cafekiosk.getBeverageList().size()).isEqualTo(1); 아래와 동일
        assertThat(cafekiosk.getBeverageList()).hasSize(1);
        assertThat(cafekiosk.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
    }


    @Test
    void addSeveralBeverages(){
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        //Happy Case
        cafekiosk.add(americano , 2);
        assertThat(cafekiosk.getBeverageList().get(0)).isEqualTo(americano);
        assertThat(cafekiosk.getBeverageList().get(1)).isEqualTo(americano);
        //assertThat(cafekiosk.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
    }


    @Test
    void addZeroBeverages(){
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        //Exception Case
       // cafekiosk.add(new Americano() , 0);
        assertThatThrownBy(()->cafekiosk.add(americano,0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }

    @Test
    void remove(){
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        cafekiosk.add(americano);
        assertThat(cafekiosk.getBeverageList()).hasSize(1);

        cafekiosk.remove(americano);
        assertThat(cafekiosk.getBeverageList()).isEmpty();


    }

    @Test
    void clear(){

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafekiosk.add(americano);
        cafekiosk.add(latte);

        assertThat(cafekiosk.getBeverageList()).hasSize(2);
        cafekiosk.clear();
        assertThat(cafekiosk.getBeverageList()).isEmpty();

    }

 /*   @Test
    void createOrder(){

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        cafekiosk.add(americano);

        Order order = cafekiosk.createOrder();

        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0)).isEqualTo("아메리카노");

    }*/


    @Test
    void createOrderWithCurrentTime(){

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        cafekiosk.add(americano);

        Order order = cafekiosk.createOrder(LocalDateTime.of(2023,1,17,10,0));

        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0).getName()).isEqualTo("아메리카노");

    }

    @Test
    void createOrderOutsideOpenTime(){

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        cafekiosk.add(americano);

        assertThatThrownBy(() -> cafekiosk.createOrder(LocalDateTime.of(2023,1,17,9,59)))
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");

    }

}