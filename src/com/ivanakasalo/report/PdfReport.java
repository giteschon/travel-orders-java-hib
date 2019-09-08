/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.report;

import com.ivanakasalo.gui.hibernate.Fuel;
import com.ivanakasalo.gui.hibernate.HibernateConnection;
import com.ivanakasalo.gui.hibernate.TravelOrder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Ivy
 */
public class PdfReport implements IReport {

    private PDPage page;
    private final TravelOrder travelOrder;
    private HibernateConnection con;

    public PdfReport(TravelOrder travelOrder, HibernateConnection con) {
        this.travelOrder = travelOrder;
        this.con = con;
    }

    @Override
    public void save(String path) {
        try (PDDocument doc = new PDDocument();) {

            page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(doc, page);) {
                reportData(stream);
            }

            doc.save(path);

        } catch (IOException ex) {
            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reportData(PDPageContentStream stream) {

        try {
            stream.beginText();
            stream.setFont(PDType1Font.TIMES_ROMAN, 12);
            stream.setLeading(14.5f);

            float y = page.getTrimBox().getHeight() - 20;
            stream.newLineAtOffset(20, y);

            String text = "Travel Order No: " + travelOrder.getTravelOrderNo();
            stream.showText(text);
            text = "Travel Order Date: " + travelOrder.getTravelOrderDate().toString();
            stream.newLine();
            stream.showText(text);
            stream.newLine();
            stream.newLine();
            text = "Driver: " + travelOrder.getDriverID().toString();
            stream.showText(text);
            stream.newLine();
            text = "Vehicle: " + travelOrder.getVehicleID().toString();
            stream.showText(text);
            stream.newLine();
            stream.newLine();
            text = "Date of Departute: " + travelOrder.getDateOfDeparture().toString();
            stream.showText(text);
            stream.newLine();
            text = "Beginning time: " + travelOrder.getStartTime().toString();
            stream.showText(text);
            stream.newLine();
            text = "End time: " + travelOrder.getEndTime().toString();
            stream.showText(text);
            stream.newLine();
            stream.newLine();
            text = "Starting destination: " + travelOrder.getDestinationStart();
            stream.showText(text);
            stream.newLine();
            text = "Ending destination: " + travelOrder.getDestinationEnd();
            stream.showText(text);
            stream.newLine();
            stream.newLine();
            text = "Km counter at the beginning " + travelOrder.getBeginningCounterStatus();
            stream.showText(text);
            stream.newLine();
            text = "Km counter at the end: " + travelOrder.getEndCounterStatus();
            stream.showText(text);
            stream.newLine();
            stream.newLine();
            text = "Travel order type: " + travelOrder.getTravelOrderTypeID().getType();
            stream.showText(text);
            stream.newLine();
            text = "Daily Allowance: " + travelOrder.getDailyAllowanceID().getPrice();
            stream.showText(text);
            stream.newLine();
            stream.newLine();

            text = "Fuel invoice:";
            stream.showText(text);
            stream.newLine();
            for (Fuel fuel : travelOrder.getFuelList()) {

                text = "Time of purchase: " + fuel.getTimeOfPurchase();
                stream.showText(text);
                stream.newLine();
                text = "Gas station:  " + fuel.getGasStation();
                stream.showText(text);
                stream.newLine();
                text = "Litre: " + fuel.getLitre();
                stream.showText(text);
                stream.newLine();
                text = "Price: " + fuel.getPrice();
                stream.showText(text);
                stream.newLine();
                stream.newLine();

            }

            stream.endText();
        } catch (IOException ex) {
            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
