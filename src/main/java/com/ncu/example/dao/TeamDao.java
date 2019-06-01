package com.ncu.example.dao;

import com.ncu.example.pojo.Team;

public interface TeamDao {
    /**
     * 向Team表中插入一条数据
     * @param team
     */
    void insertTeam(Team team);
}
