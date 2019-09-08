/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DailyAllowance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DailyAllowance.findAll", query = "SELECT d FROM DailyAllowance d")
    , @NamedQuery(name = "DailyAllowance.findByIDDailyAllowance", query = "SELECT d FROM DailyAllowance d WHERE d.iDDailyAllowance = :iDDailyAllowance")
    , @NamedQuery(name = "DailyAllowance.findByDaysHoursValue", query = "SELECT d FROM DailyAllowance d WHERE d.daysHoursValue = :daysHoursValue")
    , @NamedQuery(name = "DailyAllowance.findByPrice", query = "SELECT d FROM DailyAllowance d WHERE d.price = :price")})
public class DailyAllowance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDDailyAllowance")
    private Integer iDDailyAllowance;
    @Column(name = "DaysHoursValue")
    private String daysHoursValue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @OneToMany(mappedBy = "dailyAllowanceID")
    private List<TravelOrder> travelOrderList;

    public DailyAllowance() {
    }

    public DailyAllowance(Integer iDDailyAllowance) {
        this.iDDailyAllowance = iDDailyAllowance;
    }

    public Integer getIDDailyAllowance() {
        return iDDailyAllowance;
    }

    public void setIDDailyAllowance(Integer iDDailyAllowance) {
        this.iDDailyAllowance = iDDailyAllowance;
    }

    public String getDaysHoursValue() {
        return daysHoursValue;
    }

    public void setDaysHoursValue(String daysHoursValue) {
        this.daysHoursValue = daysHoursValue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        hash += (iDDailyAllowance != null ? iDDailyAllowance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyAllowance)) {
            return false;
        }
        DailyAllowance other = (DailyAllowance) object;
        if ((this.iDDailyAllowance == null && other.iDDailyAllowance != null) || (this.iDDailyAllowance != null && !this.iDDailyAllowance.equals(other.iDDailyAllowance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ivanakasalo.gui.hibernate.DailyAllowance[ iDDailyAllowance=" + iDDailyAllowance + " ]";
    }
    
}
