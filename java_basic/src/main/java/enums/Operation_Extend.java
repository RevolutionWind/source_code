package enums;

/**
 * Created by walker on 2020/4/28 17:22
 */
@FunctionalInterface
interface OperationInterface {
    /**
     * 函数式接口的方法
     *
     * @param x param1
     * @param y param2
     * @return double
     */
    double apply(double x, double y);
}

/**
 * @author walker
 */
public enum Operation_Extend implements OperationInterface {
    /**
     * 加
     */
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    /**
     * 减
     */
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    /**
     * 乘
     */
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    /**
     * 除
     */
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    };

    private final String symbol;

    Operation_Extend(String symbol) {
        this.symbol = symbol;
    }

}
