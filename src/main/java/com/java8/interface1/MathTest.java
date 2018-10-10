package com.java8.interface1;

public class MathTest {
    private MathOperation mathOperation;

    public MathTest(MathOperation mathOperation) {
        this.mathOperation = mathOperation;
    }

    public MathOperation getMathOperation() {
        return mathOperation;
    }

    public void setMathOperation(MathOperation mathOperation) {
        this.mathOperation = mathOperation;
    }

    public int operate(int x,int y){
        return this.mathOperation.operation(x,y);
    }
}
