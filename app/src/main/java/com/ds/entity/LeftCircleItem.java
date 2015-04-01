package com.ds.entity;

/**
 * Created by aaa on 15-4-1.
 */
public class LeftCircleItem {
    private String groupName;
    private League league1;
    private League league2;

    public LeftCircleItem() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public League getLeague1() {
        return league1;
    }

    public void setLeague1(League league1) {
        this.league1 = league1;
    }

    public League getLeague2() {
        return league2;
    }

    public void setLeague2(League league2) {
        this.league2 = league2;
    }

    public LeftCircleItem(String groupName, League league1, League league2) {

        this.groupName = groupName;
        this.league1 = league1;
        this.league2 = league2;
    }

    @Override
    public String toString() {
        return "LeftCircleItem{" +
                "groupName='" + groupName +
                '}';
    }
}
