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

public class WeightController implements Initializable {

    @FXML
    private Label welcomeText;
    @FXML
    private Button conventerMainBtn3;
    @FXML
    private Button lengBtn3;
    @FXML
    private Button weightBtn3;
    @FXML
    private TextField tfamount3;
    @FXML
    private TextField tffrom3;
    @FXML
    private TextField tfTo3;
    @FXML
    private TextField tfResult3;
    @FXML
    private Button cancelBtn3;
    @FXML
    private Button conventer3Btn;
    @FXML
    private Button insertBtn3;


    @FXML
    private TableView<WeightConverter> tvconvter3;
    @FXML
    private TableColumn<WeightConverter,Integer> colid3;
    @FXML
    private TableColumn<WeightConverter,Double> colamount3;
    @FXML
    private TableColumn<WeightConverter,String> colfrom3;
    @FXML
    private TableColumn <WeightConverter,String>colto3;
    @FXML
    private TableColumn <WeightConverter,Double>colreslut3;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showConverter();


    }

    @FXML

    private void insertButtonOnAction() {


        insertRecord();

    }

    public void convertWeightKGTOPOUNDAction(ActionEvent event) {
        Double kg;
        Double pound = Double.parseDouble(tfamount3.getText());
        if (tffrom3.getText().equals("KG") && tfTo3.getText().equals("POUND")) {
            kg = pound * 0.453592 ;



            tfResult3.setText(String.valueOf(kg));

        } else if (tffrom3.getText().equals("POUND") && tfTo3.getText().equals("KG")) {
            kg = pound * 0.453592;

            tfResult3.setText(String.valueOf(kg));

        }
    }


    public void convertWeightTonTOKGAction(ActionEvent event) {
        Double ton;
        Double KG = Double.parseDouble(tfamount3.getText());
        if (tffrom3.getText().equals("KG") && tfTo3.getText().equals("TON")) {
            ton = KG * 1000  ;



            tfResult3.setText(String.valueOf(ton));

        } else if (tffrom3.getText().equals("TON") && tfTo3.getText().equals("KG")) {
            ton = KG * 0.453592;

            tfResult3.setText(String.valueOf(ton));

        }
    }

    public ObservableList<WeightConverter> getConveterList(){
        ObservableList<WeightConverter> convertList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="SELECT * FROM weightconventer";
        Statement st;
        ResultSet rs;

        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            WeightConverter conver;
            while (rs.next()){
                conver=new WeightConverter(rs.getInt("id"),
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
        ObservableList<WeightConverter> list=getConveterList();
        colid3.setCellValueFactory(new PropertyValueFactory<WeightConverter,Integer>("id"));
        colamount3.setCellValueFactory(new PropertyValueFactory<WeightConverter,Double>("amount"));
        colfrom3.setCellValueFactory(new PropertyValueFactory<WeightConverter,String>("from"));
        colto3.setCellValueFactory(new PropertyValueFactory<WeightConverter,String >("to"));
        colreslut3.setCellValueFactory(new PropertyValueFactory<WeightConverter,Double >("reslut"));


        tvconvter3.setItems(list);
    }

    private void insertRecord(){
        String query="INSERT INTO weightconventer(amount,fromdb,todb,result) VALUES(" + Double.parseDouble(tfamount3.getText()) + ",'"
                + tffrom3.getText() + "','"
                + tfTo3.getText() + "'," + tfResult3.getText() + ")";

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

    public void cancelButtonOnAction3(ActionEvent event){
        Stage stage=(Stage)  cancelBtn3.getScene().getWindow();
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
