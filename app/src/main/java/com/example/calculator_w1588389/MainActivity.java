package com.example.calculator_w1588389;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity {

    private Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonDot,buttonAdd,buttonSub,buttonMul,buttonDiv,buttonEqual,buttonClear,buttonDel,buttonObrac,buttonCbrac ;
    private TextView info, result;
    private final char ADDITION= '+' ;
    private final char SUBTRACTION= '-' ;
    private final char MULTIPLICATION= '*' ;
    private final char DIVISION= '/' ;
    private final char EQU= 0  ;
    private double var1= Double.NaN  ;
    private double var2=0 ;
    private char ACTION;
    private char prevops=0 ;
    private int check_dot=0;
    private boolean check_bracket=false;
    private int count_bracket=0;

    private String bracket_string="";
    private double  one=0,two=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "9");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_dot == 0){
                    info.setText(info.getText().toString() + ".");
                    check_dot = 1;
                }
                else {

                }
            }
        });

        buttonObrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "(");
                check_bracket=true;
                count_bracket++;

            }
        });

        buttonCbrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + ")");
                check_bracket=false;

            }
        });



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevops==0 && !check_bracket){
                    compute();
                }

                check_dot = 0;

                if(!check_bracket){
                    ACTION= ADDITION;
                }


                if(check_bracket){
                    info.setText(info.getText().toString() + "+");
                }

                else {
                    if(!Double.isNaN(var1)){
                        result.setText(null);
                        result.setText(String.valueOf(var1) + "+");
                        info.setText(null);
                    }

                    else {
                        result.setText(null);
                        info.setText(null);
                        result.setText("Invalid input");

                    }
                }

            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevops==0 && !check_bracket){
                    compute();
                }
                check_dot = 0;

                if(!check_bracket){
                    ACTION= SUBTRACTION;
                }



                if(check_bracket){
                    info.setText(info.getText().toString() + "-");
                }
                else {
                    if(!Double.isNaN(var1)){
                        result.setText(String.valueOf(var1) + "-");
                        info.setText(null);
                    }

                    else {
                        result.setText(null);
                        info.setText(null);
                        result.setText("Invalid input");

                    }
                }

            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevops==0 && !check_bracket){
                    compute();
                }
                check_dot = 0;

                if(!check_bracket){
                    ACTION= DIVISION;
                }


                if(check_bracket){
                    info.setText(info.getText().toString() + "/");
                }
                else {
                    if(!Double.isNaN(var1)){
                        result.setText(String.valueOf(var1) + "/");
                        info.setText(null);
                    }
                    else {
                        result.setText(null);
                        info.setText(null);
                        result.setText("Invalid input");

                    }
                }


            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevops==0 && !check_bracket){
                    compute();
                }
                check_dot = 0;

                if(!check_bracket){
                    ACTION= MULTIPLICATION;
                }


                if(check_bracket){
                    info.setText(info.getText().toString() + "*");
                }
                else {
                    if(!Double.isNaN(var1)){
                        result.setText(String.valueOf(var1) + "*");
                        info.setText(null);

                    }

                    else {
                        result.setText(null);
                        info.setText(null);
                        result.setText("Invalid input");

                    }
                }


            }
        });


        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_bracket>0){
                    bracket_string=info.getText().toString();

                    char[] store_operations_order=new char[count_bracket] ;
                    String[] store_numbers_order=new String[count_bracket+1] ;

                    Pattern numbers = Pattern.compile("(\\d+\\.\\d+|\\d+)");
                    Matcher m_numbers = numbers.matcher(bracket_string);

                    int j=0;
                    while(m_numbers.find()) {
                        store_numbers_order[j]=m_numbers.group();

                        j++;
                    }

                    j=0;
                    for (int l=0;l<bracket_string.length();l++) {

                        if (bracket_string.charAt(l) == '+' || bracket_string.charAt(l) == '-' || bracket_string.charAt(l) == '*' || bracket_string.charAt(l) == '/') {
                            store_operations_order[j]=bracket_string.charAt(l);
                            j++;
                        }
                    }

                    j=1;


                    two=Double.parseDouble(store_numbers_order[store_numbers_order.length-1]);

                    while(j<store_numbers_order.length) {
                        one=Double.parseDouble(store_numbers_order[store_numbers_order.length-j-1]);

                        switch (store_operations_order[store_operations_order.length-j]) {
                            case '+':
                                two=one+two;
                                break;
                            case '-':
                                two=one-two;
                                break;
                            case '/':
                                two=one/two;
                                break;
                            case '*':
                                two=one*two;
                                break;
                        }
                        j++;
                    }

                    info.setText(String.valueOf(two));

                    count_bracket=0;

                }
                compute();

                if(var2 == 0 && ACTION==DIVISION) {
                    result.setText(null);
                    info.setText(null);
                    result.setText("Error");
                }
                else if(Double.isNaN(var1) && var2==0){
                    info.setText(null);
                    result.setText(null);
                }
                else {
                    if(two!=0){
                        result.setText(result.getText().toString() + bracket_string + "=" + "  "+String.valueOf(var1));
                        info.setText(null);
                        two=0;
                    }
                    else {
                        result.setText(result.getText().toString() + String.valueOf(var2) + "=" + "  "+String.valueOf(var1));
                        info.setText(null);
                    }

                }

            }
        });

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() > 0) {
                    CharSequence name = info.getText().toString() ;
                    info.setText(name.subSequence(0,name.length()-1));
                }
                else {
                    var1 = Double.NaN;
                    var2 = 0;
                    prevops=0;
                    info.setText(null);
                    result.setText(null);
                    check_bracket=false;
                    count_bracket=0;
                    two=0;

                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    var1 = Double.NaN;
                    var2 = 0;
                    prevops=0;
                    info.setText(null);
                    result.setText(null);
                    check_bracket=false;
                    count_bracket=0;
                    two=0;

            }
        });




    }

    public void setupUIViews() {
        button0=(Button)findViewById(R.id.btn0) ;
        button1=(Button)findViewById(R.id.btn1) ;
        button2=(Button)findViewById(R.id.btn2) ;
        button3=(Button)findViewById(R.id.btn3) ;
        button4=(Button)findViewById(R.id.btn4) ;
        button5=(Button)findViewById(R.id.btn5) ;
        button6=(Button)findViewById(R.id.btn6) ;
        button7=(Button)findViewById(R.id.btn7) ;
        button8=(Button)findViewById(R.id.btn8) ;
        button9=(Button)findViewById(R.id.btn9) ;
        buttonDot=(Button)findViewById(R.id.btndot) ;
        buttonAdd=(Button)findViewById(R.id.btnadd) ;
        buttonSub=(Button)findViewById(R.id.btnsub) ;
        buttonMul=(Button)findViewById(R.id.btnmul) ;
        buttonDiv=(Button)findViewById(R.id.btndiv) ;
        buttonClear=(Button)findViewById(R.id.btnclear) ;
        buttonDel=(Button)findViewById(R.id.btndel) ;
        buttonEqual=(Button)findViewById(R.id.btnEqual) ;
        buttonObrac=(Button)findViewById(R.id.btnOpenBracket) ;
        buttonCbrac=(Button)findViewById(R.id.btnCloseBracket) ;
        info=(TextView)findViewById(R.id.textControl) ;
        result=(TextView)findViewById(R.id.textResult) ;


    }

    private void compute(){
        if (!Double.isNaN(var1)) {

                try {
                  var2 = Double.parseDouble(info.getText().toString());
                }
                catch (Exception e) {

                }


            switch (ACTION) {
                case ADDITION:
                    var1 = var1 + var2;
                    prevops= ADDITION;
                    break;
                case SUBTRACTION:
                    var1 = var1 - var2;
                    prevops= SUBTRACTION;
                    break;
                case DIVISION:
                    var1 = var1 / var2;
                    prevops= DIVISION;
                    break;
                case MULTIPLICATION:
                    var1 = var1 * var2;
                    prevops= MULTIPLICATION;
                    break;
                case EQU:
                    break;


            }
        }
        else {
            try {
                var1 =Double.parseDouble(info.getText().toString());
            }
            catch (Exception e) {

            }

        }
    }


}
