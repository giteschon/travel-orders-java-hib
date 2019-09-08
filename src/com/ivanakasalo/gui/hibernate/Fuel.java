/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivy
 */
@Entity
@Table(name = "Fuel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fuel.findAll", query = "SELECT f FROM Fuel f")
    , @NamedQuery(name = "Fuel.findByIDFuel", query = "SELECT f FROM Fuel f WHERE f.iDFuel = :iDFuel")
    , @NamedQuery(name = "Fuel.findByTimeOfPurchase", query = "SELECT f FROM Fuel f WHERE f.timeOfPurchase = :timeOfPurchase")
    , @NamedQuery(name = "Fuel.findByGasStation", query = "SELECT f FROM Fuel f WHERE f.gasStation = :gasStation")
    , @NamedQuery(name = "Fuel.findByLitre", query = "SELECT f FROM Fuel f WHERE f.litre = :litre")
    , @NamedQuery(name = "Fuel.findByPrice", query = "SELECT f FROM Fuel f WHERE f.price = :price")})
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFuel")
    private Integer iDFuel;
    @Column(name = "TimeOfPurchase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfPurchase;
    @Column(name = "GasStation")
    private String gasStation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Litre")
    private Double litre;
    @Column(name = "Price")
    private BigDecimal price;
    @JoinColumn(name = "DriverID", referencedColumnName = "IDDriver")
    @ManyToOne
    private Driver driverID;
    @JoinColumn(name = "TravelOrderID", referencedColumnName = "IDTravelOrder")
    @ManyToOne
    private TravelOrder travelOrderID;

    public Fuel() {
    }

    public Fuel(Integer iDFuel) {
        this.iDFuel = iDFuel;
    }

    public Integer getIDFuel() {
        return iDFuel;
    }

    public void setIDFuel(Integer iDFuel) {
        this.iDFuel = iDFuel;
    }

    public Date getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(Date timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public Double getLitre() {
        return litre;
    }

    public void setLitre(Double litre) {
        this.litre = litre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Driver getDriverID() {
        return driverID;
    }

    public void setDriverID(Driver driverID) {
        this.driverID = driverID;
    }

    public TravelOrder getTravelOrderID() {
        return travelOrderID;
    }

    public void setTravelOrderID(TravelOrder travelOrderID) {
        this.travelOrderID = travelOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFuel != null ? iDFuel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fuel)) {
            return false;
        }
        Fuel other = (Fuel) object;
        if ((this.iDFuel == null && other.iDFuel != null) || (this.iDFuel != null && !this.iDFuel.equals(other.iDFuel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ivanakasalo.gui.hibernate.Fuel[ iDFuel=" + iDFuel + " ]";
    }
    
}
