package lotto;

import java.util.List;

import lotto.domain.Lottery;
import lotto.domain.LotteryResults;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.domain.Numbers;
import lotto.domain.Wallet;
import lotto.utils.StringUtils;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoApplication {
	public static void main(String[] args) {
		Lottos lottos = purchaseLotto();
		drawLotto(lottos);
	}

	private static Lottos purchaseLotto() {
		int money = InputView.inputMoney();
		Wallet wallet = new Wallet(money);
		int numberOfLotto = wallet.numberOfThingsToBuy();
		Lottos lottos = wallet.buyLotto(numberOfLotto);
		ResultView.outputPurchaseLotto(lottos);
		return lottos;
	}

	private static void drawLotto(Lottos lottos) {
		String strWinningNumbers = InputView.inputWinningNumbers();
		List<Number> numbers = StringUtils.convertToNumberList(strWinningNumbers);
		Numbers winningNumbers = new Numbers(numbers);
		Number bonusNumber = new Number(InputView.inputBonusNumber());
		Lottery lottery = new Lottery(winningNumbers, bonusNumber);
		LotteryResults results = lottery.draw(lottos);
		ResultView.outputWinningStatistics(results);
	}
}