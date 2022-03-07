package com.example.conventer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CurrencyController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button conventerMainBtn;
    @FXML
    private Button lengBtn;
    @FXML
    private Button weightBtn;
    @FXML
    private TextField tfamount;
    @FXML
    private TextField tffrom;
    @FXML
    private TextField tfTo;
    @FXML
    private TextField tfResult;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button conventer2Btn;
    @FXML
    private Button insertBtn;


    @FXML
    private TableView<CurrencyConventer> tvconvter;
    @FXML
    private TableColumn<CurrencyConventer,Integer> colid;
    @FXML
    private TableColumn<CurrencyConventer,Double> colamount;
    @FXML
    private TableColumn<CurrencyConventer,String> colfrom;
    @FXML
    private TableColumn <CurrencyConventer,String>colto;
    @FXML
    private TableColumn <CurrencyConventer,Double>colreslut;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showConverter();


    }

    @FXML

    private void insertButtonOnAction() {

//        if (e.getSource() == insertBtn) {
            insertRecord();
//        }
    }


    public void converAction(ActionEvent event) {
        Double tot;
        Double amount = Double.parseDouble(tfamount.getText());
        if (tffrom.getText().equals("USD") && tfTo.getText().equals("JOD")) {
            tot = amount / 0.91;

            tfResult.setText(String.valueOf(tot));

        } else if (tffrom.getText().equals("JOD") && tfTo.getText().equals("USD")) {
            tot = amount / 0.71;

            tfResult.setText(String.valueOf(tot));

        }else if (tffrom.getText().equals("EURO") && tfTo.getText().equals("USD")) {
            tot = amount / 0.80;

            tfResult.setText(String.valueOf(tot));

        }else if (tffrom.getText().equals("EURO") && tfTo.getText().equals("POUND")) {
            tot = amount / 0.88;
            tfResult.setText(String.valueOf(tot));

        }
    }



    public ObservableList<CurrencyConventer> getConveterList(){
        ObservableList<CurrencyConventer> convertList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="SELECT * FROM conventer";
        Statement st;
        ResultSet rs;

        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            CurrencyConventer conver;
            while (rs.next()){
                conver=new CurrencyConventer(rs.getInt("id"),
                        rs.getDouble("amount"),rs.getString("fromdb"),
                        rs.getString("todb") ,rs.getDouble("result"));
                convertList.add(conver);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return convertList;
    }

    public void showConverter()  {
        ObservableList<CurrencyConventer> list=getConveterList();
        colid.setCellValueFactory(new PropertyValueFactory<CurrencyConventer,Integer>("id"));
        colamount.setCellValueFactory(new PropertyValueFactory<CurrencyConventer,Double>("amount"));
        colfrom.setCellValueFactory(new PropertyValueFactory<CurrencyConventer,String>("from"));
        colto.setCellValueFactory(new PropertyValueFactory<CurrencyConventer,String >("to"));
        colreslut.setCellValueFactory(new PropertyValueFactory<CurrencyConventer,Double >("reslut"));


        tvconvter.setItems(list);
    }

    private void insertRecord(){
        String query="INSERT INTO conventer(amount,fromdb,todb,result) VALUES(" + Double.parseDouble(tfamount.getText()) + ",'"
                + tffrom.getText() + "','"
                + tfTo.getText() + "'," + tfResult.getText() + ")";

        executeQuery(query);

        showConverter();



    }

    public Connection getConnection(){
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user1\\IdeaProjects\\Conventer\\db2.db");
            return con;

        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage)  cancelBtn.getScene().getWindow();
        stage.close();
    }

    private void executeQuery(String query) {
        Connection conn=getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
