package com.Enlaces;

import java.util.Arrays;

public enum Operator {
    OPEN_FROM_TO("->"),
    OPEN_TO_FROM("<-"),
    TRAVEL_FROM_TO("=>"),
    TRAVEL_TO_FROM("<="),
    CLOSE_CONNECTIONS("-");

    private String operatorString;

    Operator(String operatorString) {
        this.operatorString = operatorString;
    }

    @Override
    public String toString() {
        return operatorString;
    }

    public static Operator convertStringToEnum(String strOperator) {
        return Arrays.stream(Operator.values()).
                filter(operator -> operator.getOperatorString().equals(strOperator))
                .findFirst()
                .get();
    }

    public String getOperatorString() {
        return operatorString;
    }


}
