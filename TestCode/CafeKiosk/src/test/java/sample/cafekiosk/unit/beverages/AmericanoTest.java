package sample.cafekiosk.unit.beverages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
class AmericanoTest {


    @Test
    void getName(){
        Americano americano = new Americano();


        //assertEquals는 JUnit의 API
        assertEquals(americano.getName(),"아메리카노");  //JUnit
        assertThat(americano.getName()).isEqualTo("아메리카노"); //AssertJ

    }

    @Test
    void getPrice(){
        Americano americano = new Americano();

        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}