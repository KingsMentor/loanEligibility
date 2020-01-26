package com.intelia.loansdk;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Pattern;

public class InputSam {

    InputFilter inputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence c, int i, int i1, Spanned spanned, int i2, int i3) {

            if (c == null) {
                return "";
            }
            Pattern pattern = Pattern.compile("^[0-9a-fA-F]+$");
            if (pattern.matcher(c).matches()) {
                return "";
            }
            return null;
        }
    };
}
