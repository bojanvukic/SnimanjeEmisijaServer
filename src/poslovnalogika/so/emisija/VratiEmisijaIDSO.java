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
public class VratiEmisijaIDSO extends OpstaSO{
    private String emisijaID;

    public String getEmisijaID() {
        return emisijaID;
    }

    public void setEmisijaID(String emisijaID) {
        this.emisijaID = emisijaID;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        System.out.println("Nema preduslov!");
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        emisijaID = DBBroker.getInstance().vratiID((Emisija)obj);
    }
}
