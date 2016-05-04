package calc.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.OnClickListener;

import static calc.calc.InputChar.*;

public class Calc extends AppCompatActivity {

    private CalcEngine engine;

    @BindView(R.id.textView)
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        ButterKnife.bind(this);
        engine = new CalcEngine();
        engine.addOutputListener(output -> out.setText(output));
    }

    @OnClick(R.id.button)
    public void onOneButtonClick() {
        engine.addChar(ONE);
    }

    @OnClick(R.id.button2)
    public void onTwoButtonClick() {
        engine.addChar(TWO);
    }

    @OnClick(R.id.button3)
    public void onThreeButtonClick() {
        engine.addChar(THREE);
    }

    @OnClick(R.id.button5)
    public void onFourButtonClick() {
        engine.addChar(FOUR);
    }

    @OnClick(R.id.button6)
    public void onFiveButtonClick() {
        engine.addChar(FIVE);
    }

    @OnClick(R.id.button7)
    public void onSixButtonClick() {
        engine.addChar(SIX);
    }

    @OnClick(R.id.button9)
    public void onSevenButtonClick() {
        engine.addChar(SEVEN);
    }

    @OnClick(R.id.button10)
    public void onEightButtonClick() {
        engine.addChar(EIGHT);
    }

    @OnClick(R.id.button11)
    public void onNineButtonClick() {
        engine.addChar(NINE);
    }

    @OnClick(R.id.button14)
    public void onZeroButtonClick() {
        engine.addChar(ZERO);
    }

    @OnClick(R.id.button17)
    public void onDotButtonClick() {
        engine.addChar(DOT);
    }

    @OnClick(R.id.button15)
    public void onClearButtonClick() {
        engine.clear();
    }

    @OnClick(R.id.button18)
    public void onBackspaceButtonClick() {
        engine.backspace();
    }

    @OnClick(R.id.button19)
    public void onResetButtonClick() {
        engine.reset();
    }

    @OnClick(R.id.button13)
    public void onGignButtonClick() {
        engine.invertSign();
    }

    @OnClick(R.id.button4)
    public void onAddButtonClick() {
        engine.add();
    }

    @OnClick(R.id.button8)
    public void onSubtractButtonClick() {
        engine.subtract();
    }

    @OnClick(R.id.button12)
    public void onMultiplyButtonClick() {
        engine.multiply();
    }

    @OnClick(R.id.button16)
    public void onDivideButtonClick() {
        engine.divide();
    }

    @OnClick(R.id.button20)
    public void onCalculateButtonClick() {
        engine.calculate();
    }
}
