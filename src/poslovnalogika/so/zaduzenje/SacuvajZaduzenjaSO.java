/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika.so.zaduzenje;

import dbbr.DBBroker;
import domen.Zaduzenje;
import java.util.List;

import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bojan
 */
public class SacuvajZaduzenjaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        System.out.println("Nema preduslov!");
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        try {
            List<Zaduzenje> lz = (List<Zaduzenje>) obj;
            for (Zaduzenje z : lz) {
                DBBroker.getInstance().sacuvaj(z);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
