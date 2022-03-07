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

public class AreaController implements Initializable {


    @FXML
    private TextField tfamount4;
    @FXML
    private TextField tffrom4;
    @FXML
    private TextField tfTo4;
    @FXML
    private TextField tfResult4;
    @FXML
    private Button cancelBtn4;
    @FXML
    private Button conventer4Btn;
    @FXML
    private Button insertBtn4;


    @FXML
    private TableView<AreaConvter> tvconvter4;
    @FXML
    private TableColumn<AreaConvter,Integer> colid4;
    @FXML
    private TableColumn<AreaConvter,Double> colamount4;
    @FXML
    private TableColumn<AreaConvter,String> colfrom4;
    @FXML
    private TableColumn <AreaConvter,String>colto4;
    @FXML
    private TableColumn <AreaConvter,Double>colreslut4;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showConverter();


    }

    @FXML

    private void insertButtonOnAction4() {


        insertRecord();

    }



    public void convertWeisquarekilometerstosquaremilesction(ActionEvent event) {
        Double sqarekM;
        Double sqareMile = Double.parseDouble(tfamount4.getText());
        if (tffrom4.getText().equals("SQKM") && tfTo4.getText().equals("SQMILE")) {
            sqarekM = sqareMile /2.59;



            tfResult4.setText(String.valueOf(sqarekM));

        } else if (tffrom4.getText().equals("SQMILE") && tfTo4.getText().equals("SQKM")) {
            sqarekM = sqareMile /2.59;

            tfResult4.setText(String.valueOf(sqarekM));

        }
    }


    public void convertsquaremeterstosquaremiles(ActionEvent event) {
        Double sqareMeter;
        Double sqareMile = Double.parseDouble(tfamount4.getText());
        if (tffrom4.getText().equals("SQMETER") && tfTo4.getText().equals("SQMILE")) {
            sqareMeter = sqareMile /2.59e+6;




            tfResult4.setText(String.valueOf(sqareMeter));

        } else if (tffrom4.getText().equals("SQMILE") && tfTo4.getText().equals("SQMETER")) {
            sqareMeter = sqareMile /2.59e+6;

            tfResult4.setText(String.valueOf(sqareMeter));

        }
    }




    public ObservableList<AreaConvter> getConveterList(){
        ObservableList<AreaConvter> convertList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="SELECT * FROM areaconventer";
        Statement st;
        ResultSet rs;

        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            AreaConvter conver;
            while (rs.next()){
                conver=new AreaConvter(rs.getInt("id"),
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
        ObservableList<AreaConvter> list=getConveterList();
        colid4.setCellValueFactory(new PropertyValueFactory<AreaConvter,Integer>("id"));
        colamount4.setCellValueFactory(new PropertyValueFactory<AreaConvter,Double>("amount"));
        colfrom4.setCellValueFactory(new PropertyValueFactory<AreaConvter,String>("from"));
        colto4.setCellValueFactory(new PropertyValueFactory<AreaConvter,String >("to"));
        colreslut4.setCellValueFactory(new PropertyValueFactory<AreaConvter,Double >("reslut"));


        tvconvter4.setItems(list);
    }

    private void insertRecord(){
        String query="INSERT INTO areaconventer(amount,fromdb,todb,result) VALUES(" + Double.parseDouble(tfamount4.getText()) + ",'"
                + tffrom4.getText() + "','"
                + tfTo4.getText() + "'," + tfResult4.getText() + ")";

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
        Stage stage=(Stage)  cancelBtn4.getScene().getWindow();
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
