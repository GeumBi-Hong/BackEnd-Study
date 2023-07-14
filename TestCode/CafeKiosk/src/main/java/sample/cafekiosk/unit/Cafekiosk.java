package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverages.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cafekiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10,0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22,0);

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

        for (int i = 0; i < count; i++) {
             beverageList.add(beverage);
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
     * 조건 :시간이 10:00 ~ 22:00만 주문을 받을 수 있도록 한다.
     */
    public Order createOrder(LocalDateTime currentDateTime){


        //아래 함수는 실행될때 마다 변경되는 값이다. 이 부분이 문제가 된다.
        //따라서 LocalDateTime을 매개변수로 받게 하여 외부로 분리시킨다.
        //LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();

        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요");
        }

        return new Order(LocalDateTime.now(), beverageList);
    }
}
