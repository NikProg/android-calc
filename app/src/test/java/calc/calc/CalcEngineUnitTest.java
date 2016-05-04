package calc.calc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import static calc.calc.InputChar.*;

public class CalcEngineUnitTest {

    private CalcEngine engine = new CalcEngine();
    private OutputListener listener = mock(OutputListener.class);

    @Before
    public void setupListener() {
        engine.addOutputListener(listener);
    }

    @Test
    public void shouldBeCorrectInputNumber() {
        engine.addChar(TWO);
        engine.addChar(FIVE);
        engine.addChar(EIGHT);
        verify(listener, times(1)).output("2");
        verify(listener, times(1)).output("25");
        verify(listener, times(1)).output("258");
    }

    @Test
    public void shouldBeCorrectSign() {
        engine.addChar(SIX);
        engine.addChar(NINE);
        engine.invertSign();
        verify(listener, times(1)).output("6");
        verify(listener, times(1)).output("69");
        verify(listener, times(1)).output("-69");
    }

    @Test
    public void shouldBeCorrectBackspaceAction() {
        engine.addChar(SEVEN);
        engine.addChar(THREE);
        engine.addChar(FIVE);
        engine.backspace();
        verify(listener, times(1)).output("7");
        verify(listener, times(2)).output("73");
        verify(listener, times(1)).output("735");
    }

    @Test
    public void shouldBeCorrectFloatingPointNumber() {
        engine.addChar(TWO);
        engine.addChar(FIVE);
        engine.addChar(DOT);
        engine.addChar(EIGHT);
        verify(listener, times(1)).output("2");
        verify(listener, times(1)).output("25");
        verify(listener, times(1)).output("25.");
        verify(listener, times(1)).output("25.8");
    }

    @Test
    public void shouldBeNumberWithOnlyOnePoint() {
        engine.addChar(TWO);
        engine.addChar(DOT);
        engine.addChar(FIVE);
        engine.addChar(DOT);
        engine.addChar(EIGHT);
        verify(listener, times(1)).output("2");
        verify(listener, times(1)).output("2.");
        verify(listener, times(1)).output("2.5");
        verify(listener, times(1)).output("2.58");
    }

    @Test
    public void shouldBeCorrectSumOfTwoOperands() {
        engine.addChar(TWO);
        engine.add();
        engine.addChar(THREE);
        engine.calculate();
        verify(listener, times(2)).output("2");
        verify(listener, times(1)).output("3");
        verify(listener, times(1)).output("5");
    }

    @Test
    public void shouldBeCorrectSumTheSameOperand() {
        engine.addChar(THREE);
        engine.add();
        engine.calculate();
        verify(listener, times(2)).output("3");
        verify(listener, times(1)).output("6");
    }

    @Test
    public void shouldBeNegativeSinOfResult() {
        engine.addChar(TWO);
        engine.invertSign();
        engine.multiply();
        engine.addChar(THREE);
        engine.addChar(DOT);
        engine.addChar(TWO);
        engine.calculate();
        verify(listener, times(1)).output("2");
        verify(listener, times(2)).output("-2");
        verify(listener, times(1)).output("3");
        verify(listener, times(1)).output("3.");
        verify(listener, times(1)).output("3.2");
        verify(listener, times(1)).output("-6.4");
    }

    @Test
    public void shouldBePositiveSinOfResult() {
        engine.addChar(TWO);
        engine.invertSign();
        engine.multiply();
        engine.addChar(THREE);
        engine.addChar(DOT);
        engine.invertSign();
        engine.addChar(FIVE);
        engine.calculate();
        verify(listener, times(1)).output("2");
        verify(listener, times(2)).output("-2");
        verify(listener, times(1)).output("3");
        verify(listener, times(1)).output("3.");
        verify(listener, times(1)).output("-3.");
        verify(listener, times(1)).output("-3.5");
        verify(listener, times(1)).output("7");
    }

    @Test
    public void shouldBeProcessClearAction() {
        engine.addChar(TWO);
        engine.subtract();
        engine.addChar(THREE);
        engine.clear();
        engine.addChar(FIVE);
        engine.calculate();
        verify(listener, times(3)).output("2");
        verify(listener, times(1)).output("3");
        verify(listener, times(1)).output("5");
        verify(listener, times(1)).output("-3");
    }

    @Test
    public void shouldBeChangingOperation() {
        engine.addChar(THREE);
        engine.add();
        engine.divide();
        engine.addChar(TWO);
        engine.calculate();
        verify(listener, times(3)).output("3");
        verify(listener, times(1)).output("2");
        verify(listener, times(1)).output("1.5");
    }

    @Test
    public void shouldBeResetAfterDivideByZero() {
        engine.addChar(THREE);
        engine.divide();
        engine.addChar(ZERO);
        engine.calculate();
        verify(listener, times(2)).output("3");
        verify(listener, times(3)).output("0");
    }

}
