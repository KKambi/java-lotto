package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("현재 금액에서 돈을 사용하면 그만큼 차감되어야 한다.")
    void useMoneyTest() {

        // given
        Money money = new Money(1_000);
        Money expected = new Money(0);

        // when
        Money result = money.useMoney(1);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 금액에서 가격을 받으면 구입 갯수를 반환할 수 있다.")
    void calculatePurchaseCountTest() {

        // given
        Money money = new Money(10_000);
        int price = 1000;

        // when
        int result = money.calculatePurchaseCount();

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 구매가 불가능할 때 구매 가능 갯수를 요청받으면 Excpetion을 반환해야 한다.")
    void checkAvailableForPurchaseLottoTest() {

        // given
        Money money = new Money(500);

        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> money.useMoney(2))
            .withMessageMatching("로또 게임을 진행하려면 로또 가격보다 많은 돈을 넣어야 한다.");
    }

    @Test
    @DisplayName("돈이 음수로 들어오면 Exception을 반환해야 한다.")
    void checkNegativeMoneyTest() {

        // given
        int input = -1;

        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Money(input))
            .withMessageMatching("돈은 음수가 들어올 수 없다.");
    }

    @Test
    @DisplayName("총 획득 상금이 들어오면 수익률을 계산할 수 있다.")
    void calcualteYeildTest() {

        // given
        Money money = new Money(1_000);
        double winMoney = 5_000;

        // when
        double result = money.calculateYeild(winMoney);

        // then
        assertThat(result).isEqualTo(5.0);
    }

}