package com.ateam.insulinpumpsimulation.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
}
