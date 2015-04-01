package com.ds.entity;

import java.util.List;

/**
 * Created by aaa on 15-3-27.
 */
public class Groups {
    private int id;
    private List<League>list;
    private String name;

    public Groups(){}

    public Groups(int id, List<League> list, String name) {
        this.id = id;
        this.list = list;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<League> getList() {
        return list;
    }

    public void setList(List<League> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", list=" + list +
                ", name='" + name + '\'' +
                '}';
    }
}
