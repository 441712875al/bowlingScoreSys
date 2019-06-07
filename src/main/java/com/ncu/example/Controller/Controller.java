package com.ncu.example.Controller;





import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Manager;
import com.ncu.example.pojo.Player;
import com.ncu.example.pojo.Team;
import com.ncu.example.view.GameScore;
import com.ncu.example.view.PersonScore;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@FXMLController
public class Controller {

    @Autowired
    private Manager manager;

    @FXML
    private VBox Menu;


    @FXML
    private Pane gamePane;

    @FXML
    private TableView<GameScore> gameTable;

    @FXML
    private ChoiceBox<?> gameType;

    @FXML
    private Pane informationPane;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField InfoName;

    @FXML
    private TextField InfoId;

    @FXML
    private Label hint2;

    @FXML
    private TextField InfoAge;

    @FXML
    private Label hint3;

    @FXML
    private TextField InfoSex;

    @FXML
    private Label hint21;

    @FXML
    private TextField InfoTel;

    @FXML
    private TableView<Player> playersTable;

    @FXML
    private Button btnQueryPlayer;

    @FXML
    private Pane resultPane;

    @FXML
    private Button btnQueryResult;

    @FXML
    private TextField resultName;

    @FXML
    private TextField resultId;

    @FXML
    private TableView<PersonScore> resultTable;

    @FXML
    private Pane teamPane;

    @FXML
    private TableView<Team> teamTable;

    @FXML
    private TableColumn<?, ?> infoTable1;

    @FXML
    private TableColumn<?, ?> infoTable11;

    @FXML
    private TableColumn<?, ?> infoTable111;

    @FXML
    private Button btnQueryTeam;

    @FXML
    private TextField teamId;

    /**
     * 切换面板，设赛事管理界面可见
     * @param event
     */
    @FXML
    void Game(ActionEvent event) {
        closeAll();
        gamePane.setVisible(true);
    }

    /**
     * 切换面板，信息管理设界面可见
     * @param event
     */
    @FXML
    void Info(ActionEvent event) {
        closeAll();
        informationPane.setVisible(true);
    }

    /**
     * 切换面板，设结果查询界面可见
     * @param event
     */
    @FXML
    void Result(ActionEvent event) {
        closeAll();
        resultPane.setVisible(true);
    }

    /**
     * 切换面板，设分组管理界面可见
     * @param event
     */
    @FXML
    void Team(ActionEvent event) {
        closeAll();
        teamPane.setVisible(true);
    }



    //##################################以下为数据库操作##########################################

    /**
     * 依据输入信息，添加运动员
     * @param event
     */
    @FXML
    void addPlayer(ActionEvent event) {
        //运动员信息
        String name = InfoName.getText();
         try{
             int pId =Integer.parseInt(InfoId.getText());
             manager.register(pId,name);
             showInfoMessage("register successfully!");
        }catch (Exception e){
             showInfoMessage("ID 格式输入有误,请重新输入！");
         }

    }


    /**
     * 依据输入信息，删除运动员
     * @param event
     */
    @FXML
    void deletePlayer(ActionEvent event) {
        //运动员信息

        String name = InfoName.getText();
        int pId = Integer.parseInt(InfoId.getText());
        if(manager.getPlayerDaoImpl().deletePlayer(new Player(pId,name))!=0)
            showInfoMessage("delete player "+name+" successfully!");
        else
            showErroMessage("fail to delete player "+name);
        playersTable.refresh();
    }

    /**
     * 显示所有运动员的个人信息
     * @param event
     */
    @FXML
    void displayPlayers(ActionEvent event) {

        //向数据库获取运动员信息
        List<Player> players = manager.getPlayers();

        ObservableList<Player> data = FXCollections.observableList(players);

        ((TableColumn)playersTable.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        ((TableColumn)playersTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<Player, String>("id"));
        playersTable.setItems(data);


    }

    /**
     * 显示分组情况，
     * @param event
     */
    @FXML
    void displayTeam(ActionEvent event) {
        //存放运动员信息
        List<Team> teams = manager.getPtDaoImpl().findTeamINfo();
        ObservableList<Team> data = FXCollections.observableList(teams);

        String[] list = {"id","membersDesc"};
        for(int i=0;i<2;i++){
            ((TableColumn)teamTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<Team, String>(list[i]));
        }
        teamTable.setItems(data);
    }

    /**
     * 根据姓名和ID查询、显示各种类型比赛的成绩
     * @param
     */
    @FXML
    public void queryResult(ActionEvent event){
        //查询对象信息
        String name = resultName.getText();
        int pId = Integer.parseInt(resultId.getText());

        List<PersonScore> score = manager.getPtDaoImpl().findPlayerGrade(pId,name);

        ObservableList<PersonScore> data = FXCollections.observableList(score);
        String[] tableList = {"gameType","pid","tid","name","score1","score2","score3","score4","score5","score6","score7","score8","score9","score10","fouls","totalScore"};
        for(int i=0;i<16;i++){
            ((TableColumn)resultTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<GameScore, String>(tableList[i]));
        }
        resultTable.setItems(data);
    }


    /**
     * 获得比赛的类型，并显示相应比赛类型的结果
     * @param event
     */
    @FXML
    void startGame(ActionEvent event) {
        //获取选择比赛类型
        ContestType contestType = toContestType((String) gameType.getSelectionModel().getSelectedItem());

        //比赛前分组

        manager.group(contestType);

        //后台模拟选手击打保龄球后，裁判统计分数
        manager.countScore();

        List<GameScore> gameScores = manager.getPtDaoImpl().findTeamGrade(contestType);

        ObservableList<GameScore> data = FXCollections.observableList(gameScores);
        String[] list = {"tid","name","pid","score","rank"};
        for(int i=0;i<5;i++){
            ((TableColumn) gameTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<GameScore, String>(list[i]));
        }
        gameTable.setItems(data);
    }

    /**
     * 将右侧所有的面板关闭
     */
    void closeAll(){
        informationPane.setVisible(false);
        gamePane.setVisible(false);
        resultPane.setVisible(false);
        teamPane.setVisible(false);
    }

    public void showInfoMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showErroMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * 将接受的比赛类型有String转换成ConTestType
     * @param game
     * @return
     */


    /**
     * 将接受到的字符串
     * @param game
     * @return
     */
    public static ContestType toContestType(String game){
        ContestType contestType = null;
        switch (game){
            case "单人赛" : contestType = ContestType.SINGLE;break;
            case "双人赛" :contestType = ContestType.DOUBLE;break;
            case "三人赛" :contestType = ContestType.TRIPLE;break;
            case "五人赛" :contestType = ContestType.QUINTUPLE;break;
        }
        return contestType;
    }
}
