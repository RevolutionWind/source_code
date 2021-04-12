package enums;

/**
 * @author sunxy
 * @date 2020/8/29
 */
@SuppressWarnings("unused")
public enum OverLoadTest {

    A_1(1, "1"),
    A_2(1),
    ;

    private Integer code;
    private String info;

    OverLoadTest() {
    }

    OverLoadTest(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    OverLoadTest(Integer code) {
        this.code = code;
    }
}
