/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.gui.hibernate.Fuel;
import com.ivanakasalo.gui.hibernate.HibernateConnection;
import com.ivanakasalo.gui.hibernate.TravelOrder;
import com.ivanakasalo.report.IReport;
import com.ivanakasalo.report.PdfReport;
import com.ivanakasalo.report.XmlReader;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ivy
 */
public class TravelOrderController implements Initializable {
    
    private TravelOrder travelOrder;
    private Fuel fuel;
    private boolean toSelected = false;
    private static final String PDF = ".pdf";    
    
    private HibernateConnection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new HibernateConnection();
        fillTravelOrdersTable();
    }    
    
    @FXML
    private TableView<TravelOrder> tblTravelOrders;
    @FXML
    private TableColumn<TravelOrder, String> colToNo;
    
    @FXML
    private TextArea txtFuelInvoice;
    
    @FXML
    private TextField txtTravelorder;
    
    @FXML
    private Button btnAddFuel;
    
    @FXML
    void addInvoiceToTOOnAction(ActionEvent event) {
        if (travelOrder != null) {
            fuel.setTravelOrderID(travelOrder);
            fuel.setDriverID(travelOrder.getDriverID());
            con.addFuel(fuel);
            
            btnAddFuel.setDisable(true);
            txtFuelInvoice.setText("");
        }
        
    }
    
    @FXML
    void openInvoiceOnAction(ActionEvent event) {
        openFileDialog();
        
        if (fuel != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Time of purchase: ").append(fuel.getTimeOfPurchase()).append("\n");
            sb.append("Gas station:").append(fuel.getGasStation()).append("\n");
            sb.append("Litre: ").append(fuel.getLitre()).append("\n");
            sb.append("Price: ").append(fuel.getPrice()).append("\n");
            
            txtFuelInvoice.setText(sb.toString());
            
            btnAddFuel.setDisable(false);
            
        }
    }
    
    @FXML
    void saveToPdfOnAction(ActionEvent event) {
        
        if (travelOrder != null) {
            saveReport(travelOrder);            
        }
    }
    
    @FXML
    void viewTravelOrdersOnAction(ActionEvent event) {        
        
        fillTravelOrdersTable();
        
    }
    
    @FXML
    void tblToRowClicked(MouseEvent event) {
        try {
            travelOrder = tblTravelOrders.getSelectionModel().getSelectedItem();            
            txtTravelorder.setText(travelOrder.getTravelOrderNo());
            toSelected = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private void fillTravelOrdersTable() {
        try {
            ObservableList<TravelOrder> list = FXCollections.observableArrayList(con.getTravelOrders());
            colToNo.setCellValueFactory(new PropertyValueFactory<>("travelOrderNo"));
            tblTravelOrders.setItems(list);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void saveReport(TravelOrder travelOrder) {
        FileChooser saveDialog = new FileChooser();        
        saveDialog.setTitle("Save report");
        saveDialog.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*" + PDF),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        
        File file = saveDialog.showSaveDialog(null);        
        if (file != null) {
            String path = file.getPath();
            IReport report = new PdfReport(travelOrder, con);

            //dodavanje ekstenzije
            if (!path.endsWith(PDF)) {
                path += PDF;                
            }
            report.save(path);
        } else {
            System.out.println("Couldn't save!");
        }
    }
    
    private void openFileDialog() {
        FileChooser openDialog = new FileChooser();
        openDialog.setTitle("Open xml  fuel invoice");
        openDialog.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Xml file", "*.xml"));
        
        File file = openDialog.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath();            
            XmlReader reader = new XmlReader(path);
            fuel = reader.readInvoiceFuel();
            
        } else {
            System.out.println("Action canceled.");
        }
        
    }
    
}
