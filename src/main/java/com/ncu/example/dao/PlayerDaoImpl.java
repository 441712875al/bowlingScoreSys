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

//    private static PlayerDaoImpl playerDaoImpl;

//    private  PlayerDaoImpl() {
//        this.jdbcTemplate = JDBCUtils.getJdbcTemplate();
//    }

    /**
     * 单例模式自己维护
     * @return
     */
//    public static PlayerDaoImpl getInstance(){
//        if(playerDaoImpl==null)
//            playerDaoImpl = new PlayerDaoImpl();
//        return playerDaoImpl;
//    }


    //插入一个选手SQL
    private final String INSERT_PLAYER_SQL = "insert into player(pid,name) values(?,?)";

    //查询所有选手的SQL
    private final String SELECT_PLAYERS_SQL = "select pid,name from player";
    /**
     * 在选手信息表中插入一条信息，表示已报名参赛
     * @param player
     */
    @Override
    public void insertPlayer(Player player) {
        Object[] args = {player.getId(),player.getName()};
        try{
            jdbcTemplate.update(INSERT_PLAYER_SQL,args);
        }catch (Exception e){
            System.out.println("插入失败");
            e.printStackTrace();
        }
    }


    /**
     * 查询所有的选手
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
}
