package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    
    @Test
    void 게임종료_후_재시작한다() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }
    
    @Test
    void 숫자를_더_적게_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("12"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains("[ERROR] 잘못된 숫자 개수 입니다.");
        });
    }

    @Test
    void 숫자를_더_많이_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1234"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains("[ERROR] 잘못된 숫자 개수 입니다.");
        });
    }
    
    @Test
    void 문자를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("abc"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains("[ERROR] 잘못된 입력입니다.");
        });
    }
    
    @Test
    void 게임종료_후_재시작시_다른_값을_입력하면_예외가_발생한다() {
        assertRandomNumberInRangeTest(() -> {
            assertThatThrownBy(() -> runException("246", "135", "a"));
            assertThat(output()).contains("[ERROR] 잘못된 입력입니다.");
        }, 1, 3, 5, 5, 8, 9);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
