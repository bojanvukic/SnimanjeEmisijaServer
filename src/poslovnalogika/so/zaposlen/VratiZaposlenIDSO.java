/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika.so.zaposlen;

import dbbr.DBBroker;
import domen.Zaposlen;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bojan
 */
public class VratiZaposlenIDSO extends OpstaSO{
    private String zaposlenID;

    public String getZaposlenID() {
        return zaposlenID;
    }

    public void setZaposlenID(String zaposlenID) {
        this.zaposlenID = zaposlenID;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        System.out.println("Nema preduslov!");
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        zaposlenID = DBBroker.getInstance().vratiID((Zaposlen)obj);
    }
    
}
