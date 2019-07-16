package ajouTeam8;

import ajouTeam8.domain.Lotto;
import ajouTeam8.domain.WinningLotto;

import java.util.ArrayList;

import ajouTeam8.sevice.LottoService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int gameCount, bonusNum;
        int[] winStates;
        ArrayList<Lotto> userLotto_list;
        Lotto lastWinningLotto;

        gameCount = LottoService.getPurchaseLottoNum();
        userLotto_list = LottoService.generateLottoList(gameCount);
        lastWinningLotto = LottoService.getLastWinningNum();
        bonusNum = LottoService.getBonusNum();
        WinningLotto winningLotto = new WinningLotto(lastWinningLotto, bonusNum);

        winStates = LottoService.calculateWinning(winningLotto, userLotto_list, gameCount);
        LottoService.printResult(winStates, gameCount);
    }
}
