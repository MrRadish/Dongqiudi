package com.ds.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aaa on 15-3-31.
 */
public class Urls {
    public static String news = "https://data.dongqiudi.com/soccerDataAPI/interface/getMatchVideo.php?matchID="; //后缀：?matchID=1973973
    public static String attention = "http://b.data.dongqiudi.com/soccerDataAPI/interface/getZuCaiMatchlistByDate.php?start=";
    public static  String main = "http://b.data.dongqiudi.com/soccerDataAPI/interface/getZuCaiMatchlistByDate.php?start=";
    public static String chatRoom = "http://b.api.dongqiudi.com/chatroom/state/";//插入：match_id 后缀：?type=match
    public static  String chatContent = "https://api.leancloud.cn/1.1/rtm/messages/logs/?timestamp=1427767215874&convid=";//插入：chartroom  后缀:&limit=20
    // 数组i --》 JSON：data --> 字段：message（内容） username(用户名)  avatar(头像网址)
    public static String initDate(){
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:00:00");
        Long twoDaysAgoMili=now.getTime()-72*1000*60*60;
        Date twodaysago=new Date(twoDaysAgoMili);
        String twodaysagoString=sdf.format(twodaysago);
        return twodaysagoString;
        }

    }

