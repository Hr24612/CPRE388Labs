package e.amirhamza.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button plus;
    private Button minus;
    private Button multiply;
    private Button divide;
    private Button equal;
    private Button decimal;
    private Button clear;
    private TextView info;
    private TextView result;
    private final char Subtraction = '-';
    private final char Addition = '+';
    private final char Division = '/';
    private final char Multiplication = '*';
    private final char Equ = 0;
    private double value1 = Double.NaN;
    private double value2;
    private char Action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.setText(info.getText().toString() +  "9");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = Subtraction;
                result.setText(String.valueOf(value1) +  "-");
                info.setText(null);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = Addition;
                result.setText(String.valueOf(value1) +  "+");
                info.setText(null);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = Division;
                result.setText(String.valueOf(value1) +  "/");
                info.setText(null);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = Multiplication;
                result.setText(String.valueOf(value1) +  "*");
                info.setText(null);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = Equ;
                result.setText(result.getText().toString() + String.valueOf(value2) + '=' + String.valueOf(value1));
                info.setText(null);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getText().length() > 0){
                    CharSequence name = info.getText().toString();
                    info.setText(name.subSequence(0, name.length()-1));
                }
                else {
                    value1 = Double.NaN;
                    value2 = Double.NaN;
                    info.setText(null);
                    result.setText(null);
                }
            }
        });


    }

   private void setUpViews(){

        one = (Button)findViewById(R.id.button1);
       two = (Button)findViewById(R.id.button2);
       three = (Button)findViewById(R.id.button3);
       four = (Button)findViewById(R.id.button4);
       five = (Button)findViewById(R.id.button5);
       six = (Button)findViewById(R.id.button6);
       seven = (Button)findViewById(R.id.button7);
       eight = (Button)findViewById(R.id.button8);
       nine = (Button)findViewById(R.id.button9);
       zero = (Button)findViewById(R.id.button0);
       plus = (Button)findViewById(R.id.add);
       minus = (Button)findViewById(R.id.minus);
       multiply = (Button)findViewById(R.id.multi);
       divide = (Button)findViewById(R.id.divide);
       equal = (Button)findViewById(R.id.equal);
       decimal = (Button)findViewById(R.id.point);
       clear = (Button)findViewById(R.id.clear);
       info = (TextView)findViewById(R.id.textView2);
       result = (TextView)findViewById(R.id.textView);
       equal = (Button)findViewById(R.id.equal);
       clear = (Button)findViewById(R.id.clear);
   }

   private void calculate(){

        if(!Double.isNaN(value1)) {

                value2 = Double.parseDouble(info.getText().toString());
                switch (Action) {
                    case Addition:
                        value1 = value1 + value2;
                        break;
                    case Subtraction:
                        value1 = value1 - value2;
                        break;
                    case Division:
                        value1 = value1 / value2;
                        break;
                    case Multiplication:
                        value1 = value1 * value2;
                        break;
                    case Equ:
                        break;

                }
            } else value1 = Double.parseDouble(info.getText().toString());
   }
}
