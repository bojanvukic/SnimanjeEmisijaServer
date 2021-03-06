/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika.so.zaduzenje;

import dbbr.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Zaduzenje;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bojan
 */
public class VratiZaduzenjaSO extends OpstaSO{
    private List<OpstiDomenskiObjekat> lista;
    
    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public void setLista(List<OpstiDomenskiObjekat> lista) {
        this.lista = lista;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        System.out.println("Nema preduslov!");
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        lista = DBBroker.getInstance().vratiListu((Zaduzenje)obj);
    }
}
