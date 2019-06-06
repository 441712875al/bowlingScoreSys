package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.tableInfo.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private VBox Menu;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnTeam;

    @FXML
    private Button btnGame;

    @FXML
    private Button btnScore;

    @FXML
    private Pane gamePane;

    @FXML
    private TableView<GameScore> gameTable;

    @FXML
    private ChoiceBox<?> gameType;

    @FXML
    private Label hint;

    @FXML
    private Button btnInitGame;

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
        int id = Integer.parseInt(InfoId.getText());


    }

    /**
     * 依据输入信息，删除运动员
     * @param event
     */
    @FXML
    void deletePlayer(ActionEvent event) {
        //运动员信息
        String name = InfoName.getText();
        int id = Integer.parseInt(InfoId.getText());

    }

    /**
     * 显示所有运动员的个人信息
     * @param event
     */
    @FXML
    void displayPlayers(ActionEvent event) {
        //存放运动员信息
        List<Player> players = new ArrayList<>();

        //模拟生成！！
        for(int i=0;i<100;i++){
            players.add(new Player(i,"hh"+i));
        }

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
        List<Team> teams = new ArrayList<>();

        //模拟生成
        List<Player> players = new ArrayList<>();
        for(int i=0;i<3;i++){
            players.add(new Player(i,"hh"+i));
        }
        for(int i=0;i<100;i++){
            teams.add(new Team(i,players, ContestType.TRIPLE));
        }

        ObservableList<Team> data = FXCollections.observableList(teams);
        String[] list = {"id","contestType","members"};
        for(int i=0;i<3;i++){
            ((TableColumn)teamTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<Team, String>(list[i]));
        }
        teamTable.setItems(data);
    }

    /**
     * 根据姓名和ID查询、显示各种类型比赛的成绩
     * @param event
     */
    @FXML
    void queryResult(ActionEvent event) {
        String name = resultName.getText();
        int pId = Integer.parseInt(resultId.getText());

        //将从数据库查询到的结果返回到下面的LISt中！！！！！！！！
        List<PersonScore> score = new ArrayList<>();

        //模拟生成数据
        for(int i=0;i<100;i++){
            score.add(new PersonScore("a","个人赛",1,2,2,3,4,5,6,7,8,9,10,11,12,1));
        }

        ObservableList<PersonScore> data = FXCollections.observableList(score);
        String[] tableList = {"gameType","pid","tid","name","score1","score2","score3","score4","score5","score6","score7","score8","score9","score10","totalScore","foulNum"};
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
        String contestType = (String) gameType.getSelectionModel().getSelectedItem();

        //将从数据库查询到的结果返回到下面的LISt中！！！！！！！！
        List<GameScore> gameScores = new ArrayList<>();

        //模拟数据
        for(int i=0;i<100;i++){
            gameScores.add(new GameScore(1,1,"haha"+i,10,10));
        }

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
}
