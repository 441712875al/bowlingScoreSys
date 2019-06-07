package com.ncu.example.dao;



import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Team;
import com.ncu.example.view.GameScore;
import com.ncu.example.view.PersonScore;

import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PTDao {

    /**
     * 插入team组选手的的选手的比赛成绩
     * @param team 队伍信息类
     */
    void insertPt(Team team);


    /**
     * 查询比赛的成绩，结果按总分排名
     * @param contestType 比赛类型
     * @return 赛事分数信息列表
     */
    List<GameScore> findTeamGrade(ContestType contestType);

    /**
     * 根据id和姓名查询个人的所有成绩
     * @param pId 个人的编号
     * @param name 选手姓名
     * @return 个人成绩
     */
    List<PersonScore> findPlayerGrade(int pId, String name);


    /**
     * 查询小组的队员信息
     * @return
     */

    List<Team> findTeamINfo();
}
