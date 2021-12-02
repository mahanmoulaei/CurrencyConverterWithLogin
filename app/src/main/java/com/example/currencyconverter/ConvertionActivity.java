package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ConvertionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEuro;
    TextView textViewValue;
    Button buttonConvert;
    String[] currencies;
    ArrayAdapter arrayAdapter;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion);

        InitializeScreenComponents();
    }

    private void InitializeScreenComponents() {
        editTextEuro = findViewById(R.id.editTextEuro);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        currencies = getResources().getStringArray(R.array.currencies); //create a list of items for the dropdown.
        arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_items, currencies); //create an adapter to describe how the items are displayed
        autoCompleteTextView.setAdapter(arrayAdapter); //set the dropdown adapter to the previously created one.
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(this);
        textViewValue = findViewById(R.id.textViewValue);
        dropdown = findViewById(R.id.dropdown);
        ((AutoCompleteTextView)dropdown.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                textViewValue.setText(null);
            }
        });
        ResetScreen();
    }

    private void ResetScreen() {
        editTextEuro.setText(null);
        textViewValue.setText(null);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonConvert) {
            String euro = editTextEuro.getText().toString();
            if (euro.isEmpty()) {
                Toast.makeText(this, "The input cannot be empty!", Toast.LENGTH_LONG).show();
            } else if (!isNumber(euro)) {
                Toast.makeText(this, "The input MUST be only number not letter!", Toast.LENGTH_LONG).show();
            } else {
                String selectedValue =((AutoCompleteTextView)dropdown.getEditText()).getText().toString();
                switch (selectedValue) {
                    case "USD": {
                        double usd = (Double.valueOf(euro) / 0.92);
                        textViewValue.setText("$" + usd + " " + selectedValue);
                        break;
                    }
                    case "BR": {
                        double br = (Double.valueOf(euro) / 0.18);
                        textViewValue.setText("$" + br + " " + selectedValue);
                        break;
                    }
                }
            }

        }
    }

    private boolean isNumber(String str) {
        try {
            double v = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

}