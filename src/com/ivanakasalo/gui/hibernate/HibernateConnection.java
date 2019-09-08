/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui.hibernate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ivy
 */
public class HibernateConnection {

    private static final String GET_TRAVEL_ORDER = "select * from TravelOrder";

    private EntityManagerFactory emf;

    public HibernateConnection() {
        emf = Persistence.createEntityManagerFactory("TravelOrdersPU");
    }

    public List<TravelOrder> getTravelOrders() {
        List<TravelOrder> list = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Query getTravelOrders = em.createNativeQuery(GET_TRAVEL_ORDER, TravelOrder.class);
            list = getTravelOrders.getResultList();

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return list;
    }

    public void addFuel(Fuel fuel) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(fuel);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

}
