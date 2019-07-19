package ajouTeam8.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LottoTest {

    @Test
    public void lotto_안에있는_integer_list_가져오는_test() {
        List<Integer> numberList = Arrays.asList(new Integer[]{1,2,3});
        Lotto lotto = new Lotto(numberList);
        assertThat(lotto.getNumbers().size(),is(numberList.size()));
    }
}