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
@Table(name = "Brand")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")
    , @NamedQuery(name = "Brand.findByIDBrand", query = "SELECT b FROM Brand b WHERE b.iDBrand = :iDBrand")
    , @NamedQuery(name = "Brand.findByName", query = "SELECT b FROM Brand b WHERE b.name = :name")})
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDBrand")
    private Integer iDBrand;
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "brandID")
    private List<Vehicle> vehicleList;

    public Brand() {
    }

    public Brand(Integer iDBrand) {
        this.iDBrand = iDBrand;
    }

    public Integer getIDBrand() {
        return iDBrand;
    }

    public void setIDBrand(Integer iDBrand) {
        this.iDBrand = iDBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBrand != null ? iDBrand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brand)) {
            return false;
        }
        Brand other = (Brand) object;
        if ((this.iDBrand == null && other.iDBrand != null) || (this.iDBrand != null && !this.iDBrand.equals(other.iDBrand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
