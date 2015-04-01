package com.ds.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

    
	
	public MyViewPager(Context context) {
		super(context);
	}
	
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
            //只需这句话，让父类不拦截触摸事件就可以了。
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.dispatchTouchEvent(ev);
	}
}
