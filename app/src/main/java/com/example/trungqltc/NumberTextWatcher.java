package com.example.trungqltc;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class NumberTextWatcher implements TextWatcher {

    private final EditText editText;
    private String previousNumber;
    private String prefix;
    private static final int MAX_LENGTH = 20;
    private static final int MAX_DECIMAL_DIGIT = 10;
    DecimalFormat integerFormatter;

    public NumberTextWatcher(EditText editText, String prefix) {
        this.editText = editText;
        this.prefix = prefix;
        integerFormatter = new DecimalFormat("#,###.###", new DecimalFormatSymbols(Locale.US));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        if (str.length() < prefix.length()) {
            editText.setText(prefix);
            editText.setSelection(prefix.length());
            return;
        }
        if (str.equals(prefix)) {
            return;
        }
        // number this the string which not contain prefix and ,
        String number = str.replace(prefix, "").replaceAll("[,]", "");
        // for prevent afterTextChanged recursive call
        if (number.equals(previousNumber) || number.isEmpty()) {
            return;
        }
        previousNumber = number;

        String formattedString = prefix + formatNumber(number);
        editText.removeTextChangedListener(this); // Remove listener
        editText.setText(formattedString);
        handleSelection();
        editText.addTextChangedListener(this); // Add back the listener
    }

    private String formatNumber(String number) {
        if (number.contains(".")) {
            return formatDecimal(number);
        }
        return formatInteger(number);
    }

    private String formatInteger(String str) {
        BigDecimal parsed = new BigDecimal(str);
        return integerFormatter.format(parsed);
    }

    private String formatDecimal(String str) {
        if (str.equals(".")) {
            return ".";
        }
        BigDecimal parsed = new BigDecimal(str);
        // example pattern VND #,###.00
        DecimalFormat formatter = new DecimalFormat("#,###." + getDecimalPattern(str),
                new DecimalFormatSymbols(Locale.US));
        formatter.setRoundingMode(RoundingMode.DOWN);
        return formatter.format(parsed);
    }

    /**
     * It will return suitable pattern for format decimal
     * For example: 10.2 -> return 0 | 10.23 -> return 00, | 10.235 -> return 000
     */
    private String getDecimalPattern(String str) {
        int decimalCount = str.length() - str.indexOf(".") - 1;
        StringBuilder decimalPattern = new StringBuilder();
        for (int i = 0; i < decimalCount && i < MAX_DECIMAL_DIGIT; i++) {
            decimalPattern.append("0");
        }
        return decimalPattern.toString();
    }

    private void handleSelection() {
        if (editText.getText().length() <= MAX_LENGTH) {
            editText.setSelection(editText.getText().length());
        } else {
            editText.setSelection(MAX_LENGTH);
        }
    }

}