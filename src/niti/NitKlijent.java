/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Emisija;
import domen.OpstiDomenskiObjekat;
import domen.Planer;
import domen.Zaduzenje;
import domen.Zaposlen;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import poslovnalogika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author student1
 */
public class NitKlijent extends Thread {

    private Socket socket;
    private boolean kraj;

    public NitKlijent(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            izvrsenjeOperacija();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (!kraj) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toZahtev = (TransferObjekatZahtev) inSocket.readObject();
            TransferObjekatOdgovor toOdogovor = new TransferObjekatOdgovor();
            Zaposlen z = new Zaposlen();
            Emisija e = new Emisija();
            Zaduzenje zad = new Zaduzenje();
            String kriterijum;
            List<OpstiDomenskiObjekat> lodo;
            try {
                switch (toZahtev.getOperacija()) {
                    case Konstante.VRATI_MESTA:
                        System.out.println("O: " + Konstante.VRATI_MESTA);
                        lodo = Kontroler.vratiObjekat().vratiMesta();
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_MESTA_OK);
                        break;
                    case Konstante.SACUVAJ_ZAPOSLENOG: 
                        System.out.println("O: " + Konstante.SACUVAJ_ZAPOSLENOG);
                        z = (Zaposlen) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajZaposlenog(z);
                        toOdogovor.setRezultat(Konstante.SACUVAJ_ZAPOSLENOG_OK);
                        break;
                    case Konstante.VRATI_ZAPOSLENID: 
                        System.out.println("O: " + Konstante.VRATI_ZAPOSLENID);
                        String zaposlenID = Kontroler.vratiObjekat().vratiZaposlenID();
                        toOdogovor.setOdgovor(zaposlenID);
                        toOdogovor.setRezultat(Konstante.VRATI_ZAPOSLENID_OK);
                        break;
                    case Konstante.VRATI_PLANERA: 
                        System.out.println("O: " + Konstante.VRATI_PLANERA);
                        kriterijum = (String) toZahtev.getParametar();
                        String[] sniz = kriterijum.split(" ");
                        Planer p = new Planer();
                        p.setKorisnickoIme(sniz[0]);
                        p.setKorisnickaSifra(sniz[1]);
                        lodo = Kontroler.vratiObjekat().vratiPlanera(p);
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_PLANERA_OK);
                        break;
                    case Konstante.IZMENI_ZAPOSLENOG: 
                        System.out.println("O: " + Konstante.IZMENI_ZAPOSLENOG);
                        z = (Zaposlen) toZahtev.getParametar();
                        Kontroler.vratiObjekat().izmeniZaposlenog(z);
                        toOdogovor.setRezultat(Konstante.IZMENI_ZAPOSLENOG_OK);
                        break;
                    case Konstante.OBRISI_ZAPOSLENOG: 
                        System.out.println("O: " + Konstante.OBRISI_ZAPOSLENOG);
                        z = (Zaposlen) toZahtev.getParametar();
                        Kontroler.vratiObjekat().obrisiZaposlenog(z);
                        toOdogovor.setRezultat(Konstante.OBRISI_ZAPOSLENOG_OK);
                        break;
                    case Konstante.VRATI_ZAPOSLENE_KRITERIJUM:
                        System.out.println("O: " + Konstante.VRATI_ZAPOSLENE_KRITERIJUM);
                        kriterijum = (String) toZahtev.getParametar();
                        z.setIme(kriterijum);
                        z.setPrezime(kriterijum);
                        lodo = Kontroler.vratiObjekat().vratiZaposlenePoKriterijumu(z);
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_ZAPOSLENE_KRITERIJUM_OK);
                        break;
                    case Konstante.VRATI_ZAPOSLENE:
                        System.out.println("O: " + Konstante.VRATI_ZAPOSLENE);
                        lodo = Kontroler.vratiObjekat().vratiZaposlene();
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_ZAPOSLENE_OK);
                        break;
                    case Konstante.VRATI_ZADUZENJEID: 
                        System.out.println("O: " + Konstante.VRATI_ZADUZENJEID);
                        String zaduzenjeID = Kontroler.vratiObjekat().vratiZaduzenjeID();
                        toOdogovor.setOdgovor(zaduzenjeID);
                        toOdogovor.setRezultat(Konstante.VRATI_ZADUZENJEID_OK);
                        break;
                    case Konstante.SACUVAJ_ZADUZENJA: 
                        System.out.println("O: " + Konstante.SACUVAJ_ZADUZENJA);
                        List<Zaduzenje> lzad = (List<Zaduzenje>) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajZaduzenja(lzad);
                        toOdogovor.setRezultat(Konstante.SACUVAJ_ZADUZENJA_OK);
                        break;
                    case Konstante.VRATI_TRENUTNA_ZADUZENJA:
                        System.out.println("O: " + Konstante.VRATI_TRENUTNA_ZADUZENJA);
                        z = (Zaposlen) toZahtev.getParametar();
                        zad.setZaposlen(z);
                        lodo = Kontroler.vratiObjekat().vratiTrenutnaZaduzenja(zad);
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_TRENUTNA_ZADUZENJA_OK);
                        break;
                    case Konstante.VRATI_SVA_ZADUZENJA:
                        System.out.println("O: " + Konstante.VRATI_SVA_ZADUZENJA);
                        z = (Zaposlen) toZahtev.getParametar();
                        zad.setZaposlen(z);
                        lodo = Kontroler.vratiObjekat().vratiSvaZaduzenja(zad);
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_SVA_ZADUZENJA_OK);
                        break;
                    case Konstante.IZMENI_ZADUZENJE: 
                        System.out.println("O: " + Konstante.IZMENI_ZADUZENJE);
                        zad = (Zaduzenje) toZahtev.getParametar();
                        System.out.println(zad);
                        Kontroler.vratiObjekat().izmeniZaduzenje(zad);
                        toOdogovor.setRezultat(Konstante.IZMENI_ZADUZENJE_OK);
                        break;
                    case Konstante.VRATI_OPREMU:
                        System.out.println("O: " + Konstante.VRATI_OPREMU);
                        lodo = Kontroler.vratiObjekat().vratiOpremu();
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_OPREMU_OK);
                        break;
                    case Konstante.SACUVAJ_EMISIJU: 
                        System.out.println("O: " + Konstante.SACUVAJ_EMISIJU);
                        e = (Emisija) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajEmisiju(e);
                        toOdogovor.setRezultat(Konstante.SACUVAJ_EMISIJU_OK);
                        break;
                    case Konstante.VRATI_EMISIJE:
                        System.out.println("O: " + Konstante.VRATI_EMISIJE);
                        lodo = Kontroler.vratiObjekat().vratiEmisije();
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_EMISIJE_OK);
                        break;
                    case Konstante.VRATI_EMISIJE_KRITERIJUM:
                        System.out.println("O: " + Konstante.VRATI_EMISIJE_KRITERIJUM);
                        kriterijum = (String) toZahtev.getParametar();
                        e.setNazivEmisije(kriterijum);
                        e.setTipEmisije(kriterijum);
                        lodo = Kontroler.vratiObjekat().vratiEmisijePoKriterijumu(e);
                        toOdogovor.setOdgovor(lodo);
                        toOdogovor.setRezultat(Konstante.VRATI_EMISIJE_KRITERIJUM_OK);
                        break;
                    case Konstante.VRATI_EMISIJAID: 
                        System.out.println("O: " + Konstante.VRATI_EMISIJAID);
                        String emisijaID = Kontroler.vratiObjekat().vratiEmisijaID();
                        toOdogovor.setOdgovor(emisijaID);
                        toOdogovor.setRezultat(Konstante.VRATI_EMISIJAID_OK);
                        break;
                    case Konstante.KRAJ:
                        kraj = true;
                        System.out.println("O: " + Konstante.KRAJ);
                        toOdogovor.setRezultat("Kraj rada!");
                        break;
                }
            } catch (Exception ex) {
                toOdogovor.setIzuzetak(ex);
                toOdogovor.setRezultat(ex.getMessage());
            }
            posaljiOdgovor(toOdogovor);
        }
    }

    private void posaljiOdgovor(TransferObjekatOdgovor toOdogovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toOdogovor);
    }

}
