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
@Table(name = "Driver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d")
    , @NamedQuery(name = "Driver.findByIDDriver", query = "SELECT d FROM Driver d WHERE d.iDDriver = :iDDriver")
    , @NamedQuery(name = "Driver.findByName", query = "SELECT d FROM Driver d WHERE d.name = :name")
    , @NamedQuery(name = "Driver.findBySurname", query = "SELECT d FROM Driver d WHERE d.surname = :surname")
    , @NamedQuery(name = "Driver.findByMobile", query = "SELECT d FROM Driver d WHERE d.mobile = :mobile")
    , @NamedQuery(name = "Driver.findByLicenceNo", query = "SELECT d FROM Driver d WHERE d.licenceNo = :licenceNo")})
public class Driver implements Serializable {

    @OneToMany(mappedBy = "driverID")
    private List<Fuel> fuelList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDDriver")
    private Integer iDDriver;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Mobile")
    private String mobile;
    @Column(name = "LicenceNo")
    private String licenceNo;
    @OneToMany(mappedBy = "driverID")
    private List<TravelOrder> travelOrderList;

    public Driver() {
    }

    public Driver(Integer iDDriver) {
        this.iDDriver = iDDriver;
    }

    public Integer getIDDriver() {
        return iDDriver;
    }

    public void setIDDriver(Integer iDDriver) {
        this.iDDriver = iDDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
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
        hash += (iDDriver != null ? iDDriver.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.iDDriver == null && other.iDDriver != null) || (this.iDDriver != null && !this.iDDriver.equals(other.iDDriver))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @XmlTransient
    public List<Fuel> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<Fuel> fuelList) {
        this.fuelList = fuelList;
    }
    
}
