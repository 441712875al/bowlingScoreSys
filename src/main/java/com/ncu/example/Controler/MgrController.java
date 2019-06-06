package com.ncu.example.Controler;

import com.ncu.example.pojo.Manager;
import com.ncu.example.dao.PlayerDaoImpl;
import com.ncu.example.pojo.Player;
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
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;



@FXMLController
public class MgrController {
    /**
     * 三个主面板
     */
    @FXML
    private Pane informationPane;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane resultPane;

    /**
     *信息管理界面的文本控件
     */
    @FXML
    private TextField InfoName;

    @FXML
    private TextField InfoId;

    /**
     * 赛事管理界面控件
     */
    @FXML
    private ChoiceBox gameType;
    @FXML
    private TableView gameTable;

    @Autowired
    private PlayerDaoImpl playerDao;

    @Autowired
    private Manager manager;

    /**
     * 对信息管理进行响应，切换右侧界面
     */
    @FXML
    protected void Info(){
        closeAll();
        informationPane.setVisible(true);
    }

    /**
     * 对信赛事管理进行响应，切换右侧界面
     */
    @FXML
    protected void Game(){
        closeAll();
        gamePane.setVisible(true);
    }

    /**
     * 对信结果查询进行响应，切换右侧界面
     */
    @FXML
    protected void Result(){
        closeAll();
        resultPane.setVisible(true);
    }

//    private Manager manager = Manager.getInstance();

    /**
     * 关闭右侧所有面板
     */
    public void closeAll(){
        informationPane.setVisible(false);
        gamePane.setVisible(false);
        resultPane.setVisible(false);
    }

    /**
     * 对增加player事件进行响应
     */
    @FXML
    public void addPlayer(){
        String name = InfoName.getText();
        int pId = Integer.parseInt(InfoId.getText());
        manager.register(pId,name);
    }

    /**
     * 删除player
     */
    @FXML
    public void deletePlayer(){
        String name = InfoName.getText();
        int pId = Integer.parseInt(InfoId.getText());
        manager.getPlayerDaoImpl().deletePlayer(new Player(pId,name));
    }


    @FXML
    private TableView infoTable;

    /**
     * 显示所有运动员的个人信息
     * @param event
     */
    @FXML
<<<<<<< HEAD:src/main/java/com/ncu/example/view/Controller.java
    void displayAllPlayer(ActionEvent event) {

        //将查询到的信息返回LIST 即可！！！！
        List<Player> players = new ArrayList<>();
        ObservableList<Player> data = FXCollections.observableList(players);

        ((TableColumn) infoTable.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        ((TableColumn) infoTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<Player, String>("id"));
        infoTable.setItems(data);
=======
    public void deletePlayer(){
        String name = InfoName.getText();
        int pId = Integer.parseInt(InfoId.getText());
        Player tmp = new Player(pId,name);
        if(manager.getPlayerDaoImpl().deletePlayer(tmp)==0){
            showErroMessage("fail to delete this player or don't have this player!");
            return ;
        }
        showInfoMessage("delete player successfully");
>>>>>>> 657c21dec8c60f01e468d0e98e7955162269162a:src/main/java/com/ncu/example/Controler/MgrController.java
    }

    @FXML
    public void startGame(){
        //比赛类型，个人，双人赛，3人赛
        String contestType = (String) gameType.getSelectionModel().getSelectedItem();
        List<GameScore> gameScores = manager.getPtDaoImpl().findTeamGrade(contestType);
        ObservableList<GameScore> data = FXCollections.observableArrayList(gameScores);

        ((TableColumn) gameTable.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<GameScore, String>("tid"));
        ((TableColumn) gameTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<GameScore, String>("name"));
        ((TableColumn) gameTable.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<GameScore, String>("pid"));
        ((TableColumn) gameTable.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<GameScore, String>("score"));
        ((TableColumn) gameTable.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory<GameScore, String>("rank"));

        gameTable.setItems(data);
    }

    @FXML
    private TextField resultName;
    @FXML
    private TextField resultId;
    @FXML
    private TableView resultTable;
    @FXML
    public void queryResult(){
        //查询对象信息
        String name = resultName.getText();
        int pId = Integer.parseInt(resultId.getText());

        List<PersonScore> score = manager.getPtDaoImpl().findPlayerGrade(pId,name);

        ObservableList<PersonScore> data = FXCollections.observableList(score);
        String[] tableList = {"gameType","pid","tid","name","score1","score2","score3","score4","score5","score6","score7","score8","score9","score10","totalScore"};
        for(int i=0;i<15;i++){
            ((TableColumn)resultTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<GameScore, String>(tableList[i]));
        }
        resultTable.setItems(data);
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
}