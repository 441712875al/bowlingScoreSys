package com.ncu.example.dao;

import com.ncu.example.pojo.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDao {
    /**
     * 向Team表中插入一条数据
     * @param team 保存了队伍的信息
     */
    void insertTeam(Team team);



    /**
     * 查询在数据库中的ID最大值
     * @return
     */
    int findMaxId();
}
