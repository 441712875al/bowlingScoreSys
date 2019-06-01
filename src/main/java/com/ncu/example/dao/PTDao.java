package com.ncu.example.dao;

import com.ncu.example.pojo.ContestType;

import com.ncu.example.pojo.Team;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Map;

public interface PTDao {

    /**
     * 插入team组选手的的选手的比赛成绩
     * @param team
     */
    void insertGrade(Team team);


    /**
     * 查询比赛的成绩，结果按总分排名
     * @param contestType
     * @return
     */
    List<Map<Integer,Integer>> findTeamGrade(ContestType contestType);

    /**
     * 根据id和姓名查询个人的所有成绩
     * @param pid
     * @param name
     * @return
     */
    SqlRowSet findPlayerGrade(int pid, String name);
}
