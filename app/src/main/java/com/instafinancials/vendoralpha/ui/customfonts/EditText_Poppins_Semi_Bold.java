package com.instafinancials.vendoralpha.ui.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditText_Poppins_Semi_Bold extends androidx.appcompat.widget.AppCompatEditText {

    public EditText_Poppins_Semi_Bold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditText_Poppins_Semi_Bold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText_Poppins_Semi_Bold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Poppins_SemiBold.ttf");
            setTypeface(tf);
        }
    }

}