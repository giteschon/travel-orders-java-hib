/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui.hibernate;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivy
 */
@Entity
@Table(name = "TravelOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelOrder.findAll", query = "SELECT t FROM TravelOrder t")
    , @NamedQuery(name = "TravelOrder.findByIDTravelOrder", query = "SELECT t FROM TravelOrder t WHERE t.iDTravelOrder = :iDTravelOrder")
    , @NamedQuery(name = "TravelOrder.findByTravelOrderNo", query = "SELECT t FROM TravelOrder t WHERE t.travelOrderNo = :travelOrderNo")
    , @NamedQuery(name = "TravelOrder.findByDestinationStart", query = "SELECT t FROM TravelOrder t WHERE t.destinationStart = :destinationStart")
    , @NamedQuery(name = "TravelOrder.findByDestinationEnd", query = "SELECT t FROM TravelOrder t WHERE t.destinationEnd = :destinationEnd")
    , @NamedQuery(name = "TravelOrder.findByBeginningCounterStatus", query = "SELECT t FROM TravelOrder t WHERE t.beginningCounterStatus = :beginningCounterStatus")
    , @NamedQuery(name = "TravelOrder.findByEndCounterStatus", query = "SELECT t FROM TravelOrder t WHERE t.endCounterStatus = :endCounterStatus")})
public class TravelOrder implements Serializable {

    @Column(name = "TravelOrderDate")
    @Temporal(TemporalType.DATE)
    private Date travelOrderDate;
    @Column(name = "DateOfDeparture")
    @Temporal(TemporalType.DATE)
    private Date dateOfDeparture;
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @OneToMany(mappedBy = "travelOrderID")
    private List<Fuel> fuelList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDTravelOrder")
    private Integer iDTravelOrder;
    @Column(name = "TravelOrderNo")
    private String travelOrderNo;
    @Column(name = "DestinationStart")
    private String destinationStart;
    @Column(name = "DestinationEnd")
    private String destinationEnd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BeginningCounterStatus")
    private Double beginningCounterStatus;
    @Column(name = "EndCounterStatus")
    private Double endCounterStatus;
    @JoinColumn(name = "DailyAllowanceID", referencedColumnName = "IDDailyAllowance")
    @ManyToOne
    private DailyAllowance dailyAllowanceID;
    @JoinColumn(name = "DriverID", referencedColumnName = "IDDriver")
    @ManyToOne
    private Driver driverID;
    @JoinColumn(name = "TravelOrderTypeID", referencedColumnName = "IDTravelOrderType")
    @ManyToOne
    private TravelOrderType travelOrderTypeID;
    @JoinColumn(name = "VehicleID", referencedColumnName = "IDVehicle")
    @ManyToOne
    private Vehicle vehicleID;

    public TravelOrder() {
    }

    public TravelOrder(Integer iDTravelOrder) {
        this.iDTravelOrder = iDTravelOrder;
    }

    public Integer getIDTravelOrder() {
        return iDTravelOrder;
    }

    public void setIDTravelOrder(Integer iDTravelOrder) {
        this.iDTravelOrder = iDTravelOrder;
    }

    public String getTravelOrderNo() {
        return travelOrderNo;
    }

    public void setTravelOrderNo(String travelOrderNo) {
        this.travelOrderNo = travelOrderNo;
    }

    public String getDestinationStart() {
        return destinationStart;
    }

    public void setDestinationStart(String destinationStart) {
        this.destinationStart = destinationStart;
    }

    public String getDestinationEnd() {
        return destinationEnd;
    }

    public void setDestinationEnd(String destinationEnd) {
        this.destinationEnd = destinationEnd;
    }

    public Double getBeginningCounterStatus() {
        return beginningCounterStatus;
    }

    public void setBeginningCounterStatus(Double beginningCounterStatus) {
        this.beginningCounterStatus = beginningCounterStatus;
    }

    public Double getEndCounterStatus() {
        return endCounterStatus;
    }

    public void setEndCounterStatus(Double endCounterStatus) {
        this.endCounterStatus = endCounterStatus;
    }

    public DailyAllowance getDailyAllowanceID() {
        return dailyAllowanceID;
    }

    public void setDailyAllowanceID(DailyAllowance dailyAllowanceID) {
        this.dailyAllowanceID = dailyAllowanceID;
    }

    public Driver getDriverID() {
        return driverID;
    }

    public void setDriverID(Driver driverID) {
        this.driverID = driverID;
    }

    public TravelOrderType getTravelOrderTypeID() {
        return travelOrderTypeID;
    }

    public void setTravelOrderTypeID(TravelOrderType travelOrderTypeID) {
        this.travelOrderTypeID = travelOrderTypeID;
    }

    public Vehicle getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Vehicle vehicleID) {
        this.vehicleID = vehicleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTravelOrder != null ? iDTravelOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravelOrder)) {
            return false;
        }
        TravelOrder other = (TravelOrder) object;
        if ((this.iDTravelOrder == null && other.iDTravelOrder != null) || (this.iDTravelOrder != null && !this.iDTravelOrder.equals(other.iDTravelOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ivanakasalo.gui.hibernate.TravelOrder[ iDTravelOrder=" + iDTravelOrder + " ]";
    }

    public Date getTravelOrderDate() {
        return travelOrderDate;
    }

    public void setTravelOrderDate(Date travelOrderDate) {
        this.travelOrderDate = travelOrderDate;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public List<Fuel> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<Fuel> fuelList) {
        this.fuelList = fuelList;
    }
    
}
