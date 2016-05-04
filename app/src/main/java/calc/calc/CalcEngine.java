package calc.calc;

import static calc.calc.InputChar.*;
import static calc.calc.CalcEngine.Operation.*;

public class CalcEngine {

    protected static enum Operation {
        ADD {
            @Override
            public double doOperation(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            public double doOperation(double a, double b) {
                return a - b;
            }
        },
        MULTIPLY {
            @Override
            public double doOperation(double a, double b) {
                return a * b;
            }
        },
        DIVIDE {
            @Override
            public double doOperation(double a, double b) {
                return a / b;
            }
        };

        public abstract double doOperation(double a, double b);
    }

    private double result;
    private StringBuilder input;
    private OutputListener listener;
    private Operation operation;
    private double sign = 1;

    public CalcEngine() {
        input = new StringBuilder();
    }

    public void addOutputListener(OutputListener listener) {
        this.listener = listener;
    }

    public void addChar(InputChar ch) {
        if (ch == DOT && input.indexOf(".") >= 0)
            return;
        input.append(ch.toChar());
        outputResult();
    }

    private void outputResult() {
        if (input.length() > 0)
            listener.output((sign < 0 ? "-" : "") + input.toString());
        else
            listener.output(doubleToString(result));
    }

    private String doubleToString(double in) {
        if(in == (long) in)
            return String.format("%d", (long)in);
        else
            return String.format("%s", in);
    }

    private void prepareOperation(Operation op) {
        if (op != null && input.length() == 0) {
            operation = op;
        } else if (op != null) {
            operation = op;
            result = sign * Double.parseDouble(input.toString());
            sign = 1;
            input.setLength(0);
        } else if (op == null && operation != null) {
            if (input.length() == 0) {
                if (operation != DIVIDE || result != 0)
                    result = operation.doOperation(result, result);
                else
                    reset();
            } else {
                double secondOperand = sign * Double.parseDouble(input.toString());
                if (operation != DIVIDE || secondOperand != 0) {
                    result = operation.doOperation(result, secondOperand);
                    input.setLength(0);
                } else {
                    reset();
                }
            }
        }
        outputResult();
    }

    public void add() {
        prepareOperation(ADD);
    }

    public void subtract() {
        prepareOperation(SUBTRACT);
    }

    public void multiply() {
        prepareOperation(MULTIPLY);
    }

    public void divide() {
        prepareOperation(DIVIDE);
    }

    public void backspace() {
        if (input.length() == 0)
            return;
        input.deleteCharAt(input.length()-1);
        outputResult();
    }

    public void clear() {
        input.setLength(0);
        sign = 1;
        outputResult();
    }

    public void reset() {
        result = 0;
        sign = 1;
        input.setLength(0);
        outputResult();
    }

    public void invertSign() {
        sign = - sign;
        outputResult();
    }

    public void calculate() {
        prepareOperation(null);
    }
}
