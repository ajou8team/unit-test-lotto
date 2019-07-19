package ajouTeam8.sevice;

import ajouTeam8.domain.Lotto;
import ajouTeam8.domain.Rank;
import ajouTeam8.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LottoService {
    private static int lottoNumBound = 45;
    private static int lottoCountBound = 6;

    WinningLotto winningLotto;

    public static int getPurchaseLottoNum() {
        Scanner scan = new Scanner(System.in);
        System.out.println("구입할 로또 개수를 입력해 주세요");
        int purchase = scan.nextInt();
        int count = purchase;
        if(count <= 0){
            throw new IllegalArgumentException();
        }
        System.out.println((count) + "개를 구매했습니다.");

        scan.nextLine();
        return count;
    }

    public static List<Integer> CreateNum(int randomCount) {
        Random random = new Random();
        ArrayList<Integer> ListNum = new ArrayList<>();
        int num = 0;

        while (ListNum.size() < 6) {
            num = random.nextInt(lottoNumBound) + 1;
            ListNum.remove((Integer) num);
            ListNum.add(num);
        }
        return ListNum;
    }

    public static ArrayList<Lotto> generateLottoList(int gameCount) {
        List<Integer> UserNum = new ArrayList<>();
        ArrayList<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < gameCount; i++) {
            UserNum = CreateNum(lottoCountBound);
            System.out.println(UserNum);
            lottoList.add(new Lotto(UserNum));
        }
        return lottoList;
    }

    public static Lotto getLastWinningNum() {
        Scanner scan = new Scanner(System.in);

        List<Integer> winningNum = new ArrayList<>();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] LottoNum = scan.nextLine().split(",| |, ");
        for (String s : LottoNum) {
            int tmp = Integer.valueOf(s);
            winningNum.add(tmp);
        }
        Lotto winningLotto = new Lotto(winningNum);
        return winningLotto;
    }

    public static int getBonusNum() {
        Scanner scan = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해주세요.");
        return scan.nextInt();
    }

    public static int[] calculateWinning(WinningLotto winningLotto, List<Lotto> userLotto_list, int gameCount) {
        int[] winStates = {0, 0, 0, 0, 0, 0};
        ArrayList<Rank> rank_list = new ArrayList<>();

        for (int i = 0; i < gameCount; i++) {
            rank_list.add(winningLotto.match(userLotto_list.get(i)));
            winStates[rank_list.get(i).ordinal()] += 1;
        }
        return winStates;
    }

    public static void printResult(int[] winStates, int gameCount) {
        System.out.println("당첨 통계");
        System.out.println("-----------");

        double moneyEarned = 0;
        for (int k = 4; k >= 0; k--) {
            System.out.println((Rank.values()[k].getCountOfMatch()) + "개 일치(" + (Rank.values()[k].getWinningMoney()) + "원)- " + (winStates[k]) + "개");
            moneyEarned += winStates[k] * Rank.values()[k].getWinningMoney();
        }
        System.out.println("총 수익률은 " + (moneyEarned / (gameCount * 1000)) + "입니다.");
    }
}
