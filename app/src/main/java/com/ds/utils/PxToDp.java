package com.ds.utils;

import android.content.Context;

/**
 * Created by aaa on 15-4-3.
 */
public class PxToDp {
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
