package com.example.conventer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LengthControl implements Initializable {


    @FXML
    private TextField tfamount2;
    @FXML
    private TextField tffrom2;
    @FXML
    private TextField tfTo2;
    @FXML
    private TextField tfResult2;
    @FXML
    private Button cancelBtn2;
    @FXML
    private Button conventerLen2Btn;
    @FXML
    private Button insertBtn2;

    @FXML
    private TableView<LengthConventer> tvconvter2;
    @FXML
    private TableColumn<LengthConventer,Integer> colid2;
    @FXML
    private TableColumn<LengthConventer,Double> colamount2;
    @FXML
    private TableColumn<LengthConventer,String> colfrom2;
    @FXML
    private TableColumn <LengthConventer,String>colto2;
    @FXML
    private TableColumn <LengthConventer,Double>colreslut2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     showConverter2();


    }

    @FXML

    private void insertButtonOnAction2() {

     //if (e.getSource() == insertBtn2) {
        insertRecord2();
      //}
    }


    public void converLengthMilToKILAction(ActionEvent event) {
        Double milesVal;
        Double KM = Double.parseDouble(tfamount2.getText());
        if (tffrom2.getText().equals("KILO") && tfTo2.getText().equals("MILS")) {
            milesVal = KM * 0.6;

            tfResult2.setText(String.valueOf(milesVal));

        } else if (tffrom2.getText().equals("MILS") && tfTo2.getText().equals("KILO")) {
            milesVal = KM / 0.6;

            tfResult2.setText(String.valueOf(milesVal));

        }
    }


    public void converLengthKILOTOMILAction(ActionEvent event) {
        Double kiloVal;
        Double mileVal = Double.parseDouble(tfamount2.getText());
        if (tffrom2.getText().equals("MILS") && tfTo2.getText().equals("KILO")) {
            kiloVal = mileVal * 1.6;

            tfResult2.setText(String.valueOf(kiloVal));

        } else if (tffrom2.getText().equals("KILO") && tfTo2.getText().equals("MILS")) {
            kiloVal = mileVal / 1.6;

            tfResult2.setText(String.valueOf(kiloVal));

        } else if (tffrom2.getText().equals("FOOT") && tfTo2.getText().equals("MILS")) {
            kiloVal = mileVal / 1.6;

            tfResult2.setText(String.valueOf(kiloVal));
        }
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



    public ObservableList<LengthConventer> getConveterList2(){
        ObservableList<LengthConventer> convertList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="SELECT * FROM lenconventer";
        Statement st;
        ResultSet rs;

        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            LengthConventer conver;
            while (rs.next()){
                conver=new LengthConventer(rs.getInt("id"),
                        rs.getDouble("amount"),rs.getString("fromdb"),
                        rs.getString("todb") ,rs.getDouble("result"));
                convertList.add(conver);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return convertList;
    }


    public void showConverter2()  {
        ObservableList<LengthConventer> list2= getConveterList2();
        colid2.setCellValueFactory(new PropertyValueFactory<LengthConventer,Integer>("id"));
        colamount2.setCellValueFactory(new PropertyValueFactory<LengthConventer,Double>("amount"));
        colfrom2.setCellValueFactory(new PropertyValueFactory<LengthConventer,String>("from"));
        colto2.setCellValueFactory(new PropertyValueFactory<LengthConventer,String >("to"));
        colreslut2.setCellValueFactory(new PropertyValueFactory<LengthConventer,Double >("reslut"));


        tvconvter2.setItems(list2);
    }

    private void insertRecord2(){
        String query="INSERT INTO lenconventer(amount,fromdb,todb,result) VALUES(" + Double.parseDouble(tfamount2.getText()) + ",'"
                + tffrom2.getText() + "','"
                + tfTo2.getText() + "'," + tfResult2.getText() + ")";

        executeQuery(query);

        showConverter2();



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


    public void cancelButtonOnAction2(ActionEvent event){
        Stage stage=(Stage)  cancelBtn2.getScene().getWindow();
        stage.close();
    }
}
