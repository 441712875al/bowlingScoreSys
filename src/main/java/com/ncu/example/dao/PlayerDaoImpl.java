package com.ncu.example.dao;


import com.ncu.example.pojo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PlayerDaoImpl implements PlayerDao {


    @Autowired
    private  JdbcTemplate jdbcTemplate;


    //插入一个选手SQL
    private final String INSERT_PLAYER_SQL = "insert into player(pid,name) values(?,?)";

    //查询所有选手的SQL
    private final String SELECT_PLAYERS_SQL = "select pid,name from player";

    //查询某个选手的信息
    private final String SELECT_PLAYER_SQL = "select count(*) from player where pid=? and name=?";

    //删除一个选手的信息
    private final String DELETE_PLAYER_SQL = "delete from player where pid = ? and name =?";


    /**
     * 在选手信息表中插入一条信息，表示已报名参赛
     * @param player 选手信息类
     */
    @Override
    public int insertPlayer(Player player) {
        Object[] args = {player.getId(),player.getName()};
        jdbcTemplate.update(INSERT_PLAYER_SQL,args);
        return 1;
    }


    /**
     * 查询所有的选手信息
     * @return
     */
    @Override
    public List<Player> findAllPlayer() {
        List<Player> playersTmp = new ArrayList<>();
        jdbcTemplate.query(SELECT_PLAYERS_SQL,e->{
            playersTmp.add(new Player(e.getInt("pid"),e.getString("name")));
        });
        return playersTmp;
    }


    /**
     * 删除指定选手的信息
     * @param player 选手信息类
     * @return
     */
    @Override
    public int  deletePlayer(Player player) {
        Object[] args = {player.getId(),player.getName()};
        if(jdbcTemplate.queryForObject(SELECT_PLAYER_SQL,args,Integer.class)!=1)
            return 0;//数据库没有改选手信息
        jdbcTemplate.update(DELETE_PLAYER_SQL,args);
        return 1;
    }

}
