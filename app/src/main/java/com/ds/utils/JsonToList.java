package com.ds.utils;

import android.util.Log;

import com.ds.entity.Attachment;
import com.ds.entity.Comment;
import com.ds.entity.Groups;
import com.ds.entity.HotTopic;
import com.ds.entity.League;
import com.ds.entity.Member;
import com.ds.entity.TopicDetailHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-26.
 */
public class JsonToList {


    //热帖页面的json解析
    public static List<HotTopic> hotTopicJsonToList(String jsonString){
        List<HotTopic>list=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                HotTopic hotTopic=new HotTopic();
                JSONObject object = data.getJSONObject(i);
                hotTopic.setTitle(object.getString("title"));
                hotTopic.setContent(object.getString("content"));
                hotTopic.setCreated_at(object.getString("created_at"));
                hotTopic.setId(object.getString("id"));
                hotTopic.setTotal_replies(object.getInt("total_replies"));
                JSONObject author = object.getJSONObject("author");

                Member member=new Member();
                member.setId(author.getString("id"));
                member.setUsername(author.getString("username"));
                member.setAvatar(author.getString("avatar"));
                member.setMedal_desc(author.getString("medal_desc"));
                member.setMedal_id(author.getString("medal_id"));
                hotTopic.setAuthor(member);

                JSONObject group = object.getJSONObject("group");
                League league=new League();
                league.setId(group.getInt("id"));
                league.setType(group.getString("type"));
                league.setTitle(group.getString("title"));
                hotTopic.setGroup(league);

                hotTopic.setAttachments_total(object.getInt("attachments_total"));

                List<Attachment>attachments=new ArrayList<>();

                if (hotTopic.getAttachments_total()>0){
                    JSONArray attachments1 = object.getJSONArray("attachments");
                    for (int j = 0; j < attachments1.length(); j++) {
                        JSONObject jsonObject1 = attachments1.getJSONObject(j);
                        Attachment attachment=new Attachment();
                        attachment.setHeight(jsonObject1.getInt("height"));
                        attachment.setwidth(jsonObject1.getInt("width"));
                        attachment.setThumb(jsonObject1.getString("thumb"));
                        attachment.setUrl(jsonObject1.getString("url"));
                        attachments.add(attachment);
                    }
                }
                hotTopic.setAttachments(attachments);
                list.add(hotTopic);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //圈子页面的json解析
    public static List<Groups> circleJsonToList(String jsonString){
        List<Groups>list=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                Groups group=new Groups();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                group.setId(jsonObject.getInt("id"));
                group.setName(jsonObject.getString("name"));
                List<League>leagues=new ArrayList<>();
                JSONArray jsonArray1 = jsonObject.getJSONArray("groups");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    League league=new League();
                    league.setId(jsonObject1.getInt("id"));
                    league.setTitle(jsonObject1.getString("title"));
                    league.setThumb(jsonObject1.getString("thumb"));
                    league.setTopic_total(jsonObject1.getInt("topic_total"));
                    league.setJoin_user_total(jsonObject1.getInt("join_user_total"));
                    league.setLeague_id(jsonObject1.getInt("league_id"));
                    leagues.add(league);
                }
                group.setList(leagues);
                list.add(group);
            }
            return list;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    //帖子详情页面中，头部的json解析
    public static TopicDetailHeader jsonToTopicDetailHeader(String jsonString){
        TopicDetailHeader header=new TopicDetailHeader();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            header.setTitle(jsonObject.getString("title"));
            header.setContent(jsonObject.getString("content"));
            header.setCreated_at(jsonObject.getString("created_at").substring(0,16));
            header.setTotal_replies(jsonObject.getInt("total_replies"));

            JSONObject group = jsonObject.getJSONObject("group");
            League league=new League();
            league.setId(group.getInt("id"));
            league.setTitle(group.getString("title"));
            league.setThumb(group.getString("thumb"));
            league.setDescrible(group.getString("describe"));
            header.setGroup(league);

            JSONObject author = jsonObject.getJSONObject("author");
            Member member=new Member();
            member.setId("" + author.getInt("id"));
            member.setUsername(author.getString("username"));
            member.setAvatar(author.getString("avatar"));
            header.setAuthor(member);

            JSONArray last_up_users = jsonObject.getJSONArray("last_up_users");
            List<Member>list=new ArrayList<>();
            for (int i = 0; i < last_up_users.length(); i++) {
                Member member1=new Member();
                JSONObject jsonObject1 = last_up_users.getJSONObject(i);
                member1.setId("" + jsonObject1.getString("id"));
                member1.setUsername(jsonObject1.getString("username"));
                list.add(member1);
            }
            header.setLast_up_users(list);

            JSONArray attachments = jsonObject.getJSONArray("attachments");
            List<Attachment>list1=new ArrayList<>();
            for (int i = 0; i < attachments.length(); i++) {
                Attachment attachment=new Attachment();
                JSONObject jsonObject1 = attachments.getJSONObject(i);
                attachment.setUrl(jsonObject1.getString("url"));
                attachment.setwidth(jsonObject1.getInt("width"));
                attachment.setHeight(jsonObject1.getInt("height"));
                attachment.setThumb(jsonObject1.getString("thumb"));
                list1.add(attachment);
            }
            header.setAttachments(list1);
            return header;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //帖子详情页面中，评论的json解析
    public static List<Comment> commentJsonToList(String jsonString){
        List<Comment>list=new ArrayList<>();

        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray data = object.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                Comment comment=new Comment();
                JSONObject jsonObject = data.getJSONObject(i);
                comment.setContent(jsonObject.getString("content"));
                comment.setFloor(jsonObject.getInt("floor"));
                Member member=new Member();
                JSONObject author= jsonObject.getJSONObject("author");
                member.setUsername(author.getString("username"));
                member.setAvatar(author.getString("avatar"));
                comment.setAuthor(member);
                comment.setCreated_at(jsonObject.getString("created_at"));
                list.add(comment);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
