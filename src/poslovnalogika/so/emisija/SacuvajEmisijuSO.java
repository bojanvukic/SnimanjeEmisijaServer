/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika.so.emisija;

import dbbr.DBBroker;
import domen.Emisija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bojan
 */
public class SacuvajEmisijuSO extends OpstaSO{
    
    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        System.out.println("Nema preduslov!");
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        try {
            DBBroker.getInstance().sacuvaj((Emisija)obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
