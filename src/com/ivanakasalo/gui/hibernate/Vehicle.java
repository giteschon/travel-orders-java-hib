/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui.hibernate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivy
 */
@Entity
@Table(name = "Vehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")
    , @NamedQuery(name = "Vehicle.findByIDVehicle", query = "SELECT v FROM Vehicle v WHERE v.iDVehicle = :iDVehicle")
    , @NamedQuery(name = "Vehicle.findByType", query = "SELECT v FROM Vehicle v WHERE v.type = :type")
    , @NamedQuery(name = "Vehicle.findByYearOfProduction", query = "SELECT v FROM Vehicle v WHERE v.yearOfProduction = :yearOfProduction")
    , @NamedQuery(name = "Vehicle.findByInitialKilometers", query = "SELECT v FROM Vehicle v WHERE v.initialKilometers = :initialKilometers")
    , @NamedQuery(name = "Vehicle.findByIsAvailable", query = "SELECT v FROM Vehicle v WHERE v.isAvailable = :isAvailable")})
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDVehicle")
    private Integer iDVehicle;
    @Column(name = "Type")
    private String type;
    @Column(name = "YearOfProduction")
    private Integer yearOfProduction;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "InitialKilometers")
    private Double initialKilometers;
    @Column(name = "IsAvailable")
    private Boolean isAvailable;
    @JoinColumn(name = "BrandID", referencedColumnName = "IDBrand")
    @ManyToOne
    private Brand brandID;
    @OneToMany(mappedBy = "vehicleID")
    private List<TravelOrder> travelOrderList;

    public Vehicle() {
    }

    public Vehicle(Integer iDVehicle) {
        this.iDVehicle = iDVehicle;
    }

    public Integer getIDVehicle() {
        return iDVehicle;
    }

    public void setIDVehicle(Integer iDVehicle) {
        this.iDVehicle = iDVehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getInitialKilometers() {
        return initialKilometers;
    }

    public void setInitialKilometers(Double initialKilometers) {
        this.initialKilometers = initialKilometers;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    @XmlTransient
    public List<TravelOrder> getTravelOrderList() {
        return travelOrderList;
    }

    public void setTravelOrderList(List<TravelOrder> travelOrderList) {
        this.travelOrderList = travelOrderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVehicle != null ? iDVehicle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.iDVehicle == null && other.iDVehicle != null) || (this.iDVehicle != null && !this.iDVehicle.equals(other.iDVehicle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type +", "+ getBrandID().toString();
    }
    
}
