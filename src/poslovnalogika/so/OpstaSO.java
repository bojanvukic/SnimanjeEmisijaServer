/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika.so;

import dbbr.DBBroker;

/**
 *
 * @author Bojan
 */
public abstract class OpstaSO {
    
    public final void izvrsiOperaciju(Object obj) throws RuntimeException {
        try {
            DBBroker.getInstance().ucitajDriver();
            DBBroker.getInstance().otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            DBBroker.getInstance().commitTransakcije();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            DBBroker.getInstance().rollbackTransakcije();
            throw ex;
        } finally {
            DBBroker.getInstance().zatvoriKonekciju();
        }
    }
    

    protected abstract void proveriPreduslov(Object obj) throws RuntimeException;

    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException;
}
