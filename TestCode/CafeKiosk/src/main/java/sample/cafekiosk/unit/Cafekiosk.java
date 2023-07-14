package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverages.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cafekiosk {

    private final List<Beverage> beverageList = new ArrayList<>();

    /**
     * 주문 목록에 음료 추가
     * @param beverage 음료 클래스
     */
    public void add(Beverage beverage){
        beverageList.add(beverage);
    }

    /**
     * 주문 목록에 음료 여러잔 추가
     *
     */
    public void add(Beverage beverage,int count){
        //만약 0잔 이하의 주문일 경우 예외를 발생시킨다고 가정
        if(count <= 0){
            throw new IllegalArgumentException("음료는 1잔 이상 주문하실 수 있습니다.");
        }
    }

    /**
     * 주문 목록에 음료 삭제
     * @param beverage 음료 클래스
     */
    public void remove(Beverage beverage){
        beverageList.remove(beverage);
    }

    /**
     * 주문 목록 비우기
     */
    public void clear(){
        beverageList.clear();
    }

    /**
     * 전체 주문 금액 계산
     * @return 전체 주문금액
     */
    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : beverageList) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }

    /**
     * 주문 생성하기
     */
    public Order createOrder(){
        return new Order(LocalDateTime.now(), beverageList);
    }
}
