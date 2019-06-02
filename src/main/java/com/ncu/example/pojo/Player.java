package com.ncu.example.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private int id;
    private String name;
    private Integer[] scores = new Integer[10];

    private List<Integer>[] grades;
    private int tolScore;
    private int foulsNum=0;

    public Player() {
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 返回一个记录选手每次出手击倒的瓶子的数量
     * @return
     */
    public List<Integer>[]  play(){
        for(int i=0;i<10;i++){
            grades[i] = new ArrayList<Integer>();
            int firstRoll = roll(10);
            int secondRoll = 0;

            grades[i].add(firstRoll);
            if(firstRoll <10||i==9){//第一次出手未全部击倒,第十轮肯定要扔第二次

                secondRoll = roll(10-firstRoll==0?10:10-firstRoll);//第一次全部击倒则此时再扔竖立的瓶子为10，否则为剩余的数量
                grades[i].add(secondRoll);
            }

            if(i==9&&firstRoll+secondRoll>=10){
                grades[i].add(roll(10-secondRoll==0?10:10-secondRoll));
            }
        }
        return grades;
    }

    /**
     * 返回选手扔一次击倒的瓶数
     * @param bottleNum 可击倒瓶子的数量
     * @return
     */
    public int roll(int bottleNum){
        Random rand = new Random();
        int grade = rand.nextInt(bottleNum+2);//bottleNum+1作为犯规情况模拟
        if(grade==bottleNum+1){
            grade = 0;
            foulsNum++;
        }
        return grade;
    }

    public void caculateToltalScore(){
        for(Integer e:scores)
            tolScore +=e;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getScores() {
        return scores;
    }

    public void setScores(Integer[] scores) {
        this.scores = scores;
    }

    public List<Integer>[] getGrades() {
        return grades;
    }

    public void setGrades(List<Integer>[] grades) {
        this.grades = grades;
    }

    public int getTolScore() {
        return tolScore;
    }

    public void setTolScore(int tolScore) {
        this.tolScore = tolScore;
    }

    public int getFoulsNum() {
        return foulsNum;
    }

    public void setFoulsNum(int foulsNum) {
        this.foulsNum = foulsNum;
    }


}
