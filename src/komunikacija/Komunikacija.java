/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package komunikacija;


import niti.NitKlijent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bojan
 */
public class Komunikacija extends Thread{
    private Socket socket;
    
    @Override
    public void run() {
        try {
            pokreniServer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void pokreniServer() throws IOException, ClassNotFoundException {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut i spreman za rad.");
            while (!isInterrupted()) {
                socket = ss.accept();
                System.out.println("Klijent se povezao.");
                NitKlijent nit = new NitKlijent(socket);
                nit.start();
                Thread.sleep(50);
            }
        } catch (InterruptedException ie) {
            System.out.println("Server je zavrsio za radom");
        }
    }
    
}
