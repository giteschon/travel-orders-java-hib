/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.report;

import com.ivanakasalo.gui.hibernate.Fuel;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Ivy
 */
public class XmlReader {

    private String path;

    public XmlReader(String path) {
        this.path = path;
    }

    public Fuel readInvoiceFuel() {
        try {
            File xmlFile = new File(path);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(xmlFile);
            //mice sve komentare i prazne elemente, tj. cita samo cvorove
            doc.getDocumentElement().normalize();

            Fuel fuel = new Fuel();
            String timeOfPurchase = getElementByTagName("timeOfPurchase", doc);
            fuel.setTimeOfPurchase(Date.valueOf(timeOfPurchase));

            String gasStation = getElementByTagName("gasStation", doc);
            fuel.setGasStation(gasStation);

            Double litre = Double.valueOf(getElementByTagName("litre", doc));
            fuel.setLitre(litre);

            BigDecimal price = BigDecimal.valueOf(Double.valueOf(getElementByTagName("price", doc)));
            fuel.setPrice(price);

            return fuel;

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private String getElementByTagName(String tagName, Document doc) {
        return doc.getElementsByTagName(tagName).item(0).getTextContent();
    }

}
