package sample.tableInfo;


public class PersonScore {
    private String name;
    private String gameType;
    private int pid;
    private int tid;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;
    private int score6;
    private int score7;
    private int score8;
    private int score9;
    private int score10;
    private int totalScore;
    private int foulNum;

    public PersonScore(String name, String gameType,int pid,int tid, int score1, int score2, int score3, int score4, int score5, int score6, int score7, int score8, int score9, int score10, int totalScore,int foulNum) {
        this.name = name;
        this.gameType = gameType;
        this.pid = pid;
        this.tid = tid;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
        this.score6 = score6;
        this.score7 = score7;
        this.score8 = score8;
        this.score9 = score9;
        this.score10 = score10;
        this.totalScore = totalScore;
        this.foulNum = foulNum;
    }



    public String getName() {
        return name;
    }


    public int getFoulNum() {
        return foulNum;
    }

    public void setFoulNum(int foulNum) {
        this.foulNum = foulNum;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore4() {
        return score4;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getScore5() {
        return score5;
    }

    public int getScore6() {
        return score6;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getScore7() {
        return score7;
    }

    public void setScore4(int score4) {
        this.score4 = score4;
    }

    public void setScore5(int score5) {
        this.score5 = score5;
    }

    public void setScore6(int score6) {
        this.score6 = score6;
    }

    public void setScore7(int score7) {
        this.score7 = score7;
    }

    public void setScore8(int score8) {
        this.score8 = score8;
    }

    public void setScore9(int score9) {
        this.score9 = score9;
    }

    public void setScore10(int score10) {
        this.score10 = score10;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getScore8() {
        return score8;
    }

    public int getScore9() {
        return score9;
    }

    public int getScore10() {
        return score10;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getScore2() {
        return score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }
}
