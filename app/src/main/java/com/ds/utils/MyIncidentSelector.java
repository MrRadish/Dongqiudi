package com.ds.utils;

import com.ds.dongqiudi.R;

/**
 * Created by aaa on 15-4-1.
 */
public class MyIncidentSelector {
    public static int getNewsImage(String news){
          if (news.equals("G")){
              return R.drawable.g;
          }else if
              (news.equals("AS")){
              return R.drawable.as;
          }else if
                  (news.equals("YC")){
              return R.drawable.yc;
          }else if
                  (news.equals("SI")){
              return R.drawable.si;
          }else if
                  (news.equals("SO")){
              return R.drawable.so;
          }else if
                  (news.equals("PM")){
              return R.drawable.pm;
          }else if
                  (news.equals("PG")){
              return R.drawable.pg;
          }else if
                  (news.equals("Y2C")){
              return R.drawable.y2c;
          }else if
                  (news.equals("OG")){
              return R.drawable.og;
          }else if
                  (news.equals("RC")){
              return R.drawable.rc;
          }
        return 0;
    }
}
