package com.example.findtheroots;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;
    private TextView textViewX1;
    private TextView textViewX2;
    private TextView textViewEqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // ax + b = c // x = (c-b)/a
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextA = findViewById(R.id.edit_text_a);
        editTextB = findViewById(R.id.edit_text_b);
        editTextC = findViewById(R.id.edit_text_c);
        textViewX1 = findViewById(R.id.text_view_x1);
        textViewX2 = findViewById(R.id.text_view_x2);
        textViewEqu = findViewById(R.id.text_view_equ);
    }

    private double getDoubleValue(EditText editText) {
        return Double.parseDouble(editText.getText().toString());
    }
    private boolean check(EditText editText){
        if (editText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkForEmpty(EditText editTextA, EditText editTextB, EditText editTextC) {
        if (check(editTextA) || check(editTextB) || check(editTextC)) {
            Toast.makeText(this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void click(View view) {
        if (!checkForEmpty(editTextA, editTextB, editTextC)) {
            double a = getDoubleValue(editTextA);
            double b = getDoubleValue(editTextB);
            double c = getDoubleValue(editTextC);

            String sign1;
            String sign2;

            if (b >= 0) {
                sign1 = "+ ";
            } else {
                sign1 = "- ";
            }
            if (c >= 0) {
                sign2 = "+ ";
            } else {
                sign2 = "- ";
            }

            textViewEqu.setText(a + " * x^2 " + sign1 + b + " * x " + sign2 + c + " = 0");

            if (a != 0) {
                double d = b * b - 4 * a * c;
                if (d < 0) {
                    textViewX1.setText("Уравнение не имеет корней");
                    textViewX2.setText("");
                } else if (d == 0) {
                    double x = -b / (2 * a);
                    textViewX1.setText(String.format("%.2f", x));
                    textViewX2.setText("");
                } else if (d > 0) {
                    double x1 = (-b - Math.sqrt(d)) / (2 * a);
                    double x2 = (-b + Math.sqrt(d)) / (2 * a);
                    textViewX1.setText("x1 = " + String.format("%.2f", x1));
                    textViewX2.setText("x2 = " + String.format("%.2f", x2));
                }
            } else {
                if (b != 0) {
                    double x = -c / b;
                    textViewX1.setText(String.format("%.2f", x));
                    textViewX2.setText("");
                } else {
                    if (c == 0) {
                        textViewX1.setText("Уравнение имеет бесконечное");
                        textViewX2.setText("множество решений");
                    } else {
                        textViewX1.setText("Уравнение не имеет корней");
                        textViewX2.setText("");
                    }
                }
            }
            editTextA.setText("");
            editTextB.setText("");
            editTextC.setText("");
        }
    }
}