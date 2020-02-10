package com.ateam.insulinpumpsimulation.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void showToast(Context context, String msg, Boolean isError) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = toast.getView();
        if (isError) {
            view.setBackgroundColor(Color.parseColor("#d73030"));
        } else {
            view.setBackgroundColor(Color.parseColor("#2962ff"));
        }
        view.setPadding(10, 0, 10, 0);
        TextView v = view.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    public static void snackbarMessage(View view, String msg, Boolean isError) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        if (isError) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.GREEN);
        }


        snackbar.show();
    }
}
