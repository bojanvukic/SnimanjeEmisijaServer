/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package niti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Bojan
 */
public class NitSat extends Thread {
    private JLabel jlSat;

    public NitSat(JLabel jlSat) {
        this.jlSat = jlSat;
    }
    
    @Override
    public void run() {
        pokreniSat();
    }
    
    private void pokreniSat() {
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                String vreme = df.format(new Date());
                jlSat.setText(vreme);
                Thread.sleep(50);
            }
        } catch (InterruptedException ie) {
            System.out.println("Nit sat je zavrsila sa radom");
        }
    }
}
