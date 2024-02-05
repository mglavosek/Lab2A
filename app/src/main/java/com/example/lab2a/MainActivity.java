package com.example.lab2a;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2a.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        //Getting all the inputs from the user
        String totalBillInput = binding.totalBillInput.getText().toString();
        String tipPercentageInput = binding.tipPercentageInput.getText().toString();
        String numberOfPeopleInput = binding.numberPeopleInput.getText().toString();

        //If a valid input is put in, then the calculations are made
        if (isValidInput(totalBillInput, tipPercentageInput, numberOfPeopleInput)) {
            double totalBill = Double.parseDouble(totalBillInput);
            int tipPercentage = Integer.parseInt(tipPercentageInput);
            int numberOfPeople = Integer.parseInt(numberOfPeopleInput);

            double totalWithTip = totalBill * (1 + tipPercentage / 100.0);
            double totalPerPerson = totalWithTip / numberOfPeople;

            //Formatting the total amount per person to appear as a price
            String output = getString(R.string.total_per_person, String.format("$%.2f", totalPerPerson));
            binding.outputText.setText(output);
        } else {
            //Outputting an error message if an invalid input was put in
            String invalid_input = getString(R.string.invalid_input);
            binding.outputText.setText(invalid_input);
        }
    }

    //Checks for a valid input
    private boolean isValidInput(String totalBill, String tipPercentage, String numberOfPeople) {
        return !totalBill.isEmpty() && !tipPercentage.isEmpty() && !numberOfPeople.isEmpty()
                && isNumeric(totalBill) && isNumeric(tipPercentage) && isNumeric(numberOfPeople)
                && !totalBill.equals("0") && !tipPercentage.equals("0") && !numberOfPeople.equals("0");
    }

    //Throws an error if an input is not a number
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
