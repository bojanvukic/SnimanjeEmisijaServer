/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poslovnalogika;

import dbbr.DBBroker;
import domen.Emisija;
import domen.Mesto;
import domen.Oprema;
import domen.OpstiDomenskiObjekat;
import domen.Planer;
import domen.Zaduzenje;
import domen.Zaposlen;
import java.util.List;
import poslovnalogika.so.emisija.SacuvajEmisijuSO;
import poslovnalogika.so.emisija.VratiEmisijaIDSO;
import poslovnalogika.so.emisija.VratiEmisijePoKriterijumuSO;
import poslovnalogika.so.emisija.VratiEmisijeSO;
import poslovnalogika.so.mesto.VratiMestaSO;
import poslovnalogika.so.oprema.VratiOpremuSO;
import poslovnalogika.so.planer.VratiPlaneraSO;
import poslovnalogika.so.zaduzenje.IzmeniZaduzenjeSO;
import poslovnalogika.so.zaduzenje.SacuvajZaduzenjaSO;
import poslovnalogika.so.zaduzenje.VratiZaduzenjaPoKriterijumuSO;
import poslovnalogika.so.zaduzenje.VratiZaduzenjaSO;
import poslovnalogika.so.zaduzenje.VratiZaduzenjeIDSO;
import poslovnalogika.so.zaposlen.IzmeniZaposlenogSO;
import poslovnalogika.so.zaposlen.ObrisiZaposlenogSo;
import poslovnalogika.so.zaposlen.SacuvajZaposlenogSO;
import poslovnalogika.so.zaposlen.VratiZaposlenIDSO;
import poslovnalogika.so.zaposlen.VratiZaposlenePoKriterijumuSO;
import poslovnalogika.so.zaposlen.VratiZaposleneSO;

/**
 *
 * @author Bojan
 */
public class Kontroler {
    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
    }
    
    public static Kontroler vratiObjekat(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    
    public List<OpstiDomenskiObjekat> vratiMesta() throws RuntimeException {
        VratiMestaSO soVratiMesta = new VratiMestaSO();
        soVratiMesta.izvrsiOperaciju(new Mesto());
        return soVratiMesta.getLista();
    }
    
    public void sacuvajZaposlenog(Zaposlen z) throws RuntimeException {
        SacuvajZaposlenogSO soSacuvajZaposlenog = new SacuvajZaposlenogSO();
        soSacuvajZaposlenog.izvrsiOperaciju(z);
    }
    
    public void izmeniZaposlenog(Zaposlen z) throws RuntimeException {
        IzmeniZaposlenogSO soIzmeniZaposlenog = new IzmeniZaposlenogSO();
        soIzmeniZaposlenog.izvrsiOperaciju(z);
    }
    
    public List<OpstiDomenskiObjekat> vratiZaposlene() throws RuntimeException {
        VratiZaposleneSO soVratiZaposlene = new VratiZaposleneSO();
        soVratiZaposlene.izvrsiOperaciju(new Zaposlen());
        return soVratiZaposlene.getLista();
    }
   
  
    public List<OpstiDomenskiObjekat> vratiPlanera(Planer p) throws RuntimeException {
        VratiPlaneraSO soVratiPlanera = new VratiPlaneraSO();
        soVratiPlanera.izvrsiOperaciju(p);
        return soVratiPlanera.getLista();
    }

    public List<OpstiDomenskiObjekat> vratiOpremu() throws RuntimeException {
        VratiOpremuSO soVratiOpremu = new VratiOpremuSO();
        soVratiOpremu.izvrsiOperaciju(new Oprema());
        return soVratiOpremu.getLista();
    }

    public void sacuvajZaduzenja(List<Zaduzenje> lz) throws RuntimeException {
        SacuvajZaduzenjaSO soSacuvajZaduzenja = new SacuvajZaduzenjaSO();
        soSacuvajZaduzenja.izvrsiOperaciju(lz);
    }

    public List<OpstiDomenskiObjekat> vratiSvaZaduzenja(Zaduzenje zad) throws RuntimeException {
        VratiZaduzenjaSO soVratiZaduzenja = new VratiZaduzenjaSO();
        soVratiZaduzenja.izvrsiOperaciju(zad);
        return soVratiZaduzenja.getLista();
    }

    public String vratiZaduzenjeID() throws RuntimeException {
        VratiZaduzenjeIDSO soVratiZaduzenjeID = new VratiZaduzenjeIDSO();
        soVratiZaduzenjeID.izvrsiOperaciju(new Zaduzenje());
        return soVratiZaduzenjeID.getZaduzenjeID();
    }

    public String vratiZaposlenID() throws RuntimeException {
        VratiZaposlenIDSO soVratiZaposlenID = new VratiZaposlenIDSO();
        soVratiZaposlenID.izvrsiOperaciju(new Zaposlen());
        return soVratiZaposlenID.getZaposlenID();
    }

    public void izmeniZaduzenje(Zaduzenje zad) throws RuntimeException {
        IzmeniZaduzenjeSO soIzmeniZaduzenje = new IzmeniZaduzenjeSO();
        soIzmeniZaduzenje.izvrsiOperaciju(zad);
    }

    public void obrisiZaposlenog(Zaposlen z) throws RuntimeException {
        ObrisiZaposlenogSo soObrisiZaposlenog = new ObrisiZaposlenogSo();
        soObrisiZaposlenog.izvrsiOperaciju(z);
    }

    public List<OpstiDomenskiObjekat> vratiZaposlenePoKriterijumu(Zaposlen z) throws RuntimeException{
        VratiZaposlenePoKriterijumuSO soVratiZaposlenePoKriterijum = new VratiZaposlenePoKriterijumuSO();
        soVratiZaposlenePoKriterijum.izvrsiOperaciju(z);
        return soVratiZaposlenePoKriterijum.getLista();
    }

    public List<OpstiDomenskiObjekat> vratiTrenutnaZaduzenja(Zaduzenje zad) throws RuntimeException{
        VratiZaduzenjaPoKriterijumuSO soVratiZaduzenjaPoKriterijumu = new VratiZaduzenjaPoKriterijumuSO();
        soVratiZaduzenjaPoKriterijumu.izvrsiOperaciju(zad);
        return soVratiZaduzenjaPoKriterijumu.getLista();
    }

    public void sacuvajEmisiju(Emisija e) throws RuntimeException{
        SacuvajEmisijuSO soSacuvajEmisiju = new SacuvajEmisijuSO();
        soSacuvajEmisiju.izvrsiOperaciju(e);
    }

    public List<OpstiDomenskiObjekat> vratiEmisijePoKriterijumu(Emisija e) throws RuntimeException{
        VratiEmisijePoKriterijumuSO soVratiEmisijePoKriterijum = new VratiEmisijePoKriterijumuSO();
        soVratiEmisijePoKriterijum.izvrsiOperaciju(e);
        return soVratiEmisijePoKriterijum.getLista();
    }

    public List<OpstiDomenskiObjekat> vratiEmisije() throws RuntimeException{
        VratiEmisijeSO soVratiEmisije = new VratiEmisijeSO();
        soVratiEmisije.izvrsiOperaciju(new Emisija());
        return soVratiEmisije.getLista();
    }

    public String vratiEmisijaID() throws RuntimeException {
        VratiEmisijaIDSO soVratiEmisijaID = new VratiEmisijaIDSO();
        soVratiEmisijaID.izvrsiOperaciju(new Emisija());
        return soVratiEmisijaID.getEmisijaID();
    }

}
