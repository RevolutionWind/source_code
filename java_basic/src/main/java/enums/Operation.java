package enums;

import java.util.function.DoubleBinaryOperator;

/**
 * 枚举+lambda表达式
 *
 * @author walker
 * @date 2020-05
 */
@SuppressWarnings("unused")
public enum Operation {
    /**
     * 加
     */
    ADD(Double::sum),
    /**
     * 减
     */
    MINUS((x, y) -> x - y),
    /**
     * 乘
     */
    TIMES((x, y) -> x * y),
    /**
     * 除
     */
    DIVIDE((x, y) -> x / y)
    ;

    private final DoubleBinaryOperator binaryOperator;

    Operation(DoubleBinaryOperator op) {
        this.binaryOperator = op;
    }

    public double apply(double x, double y) {
        return binaryOperator.applyAsDouble(x, y);
    }
}
