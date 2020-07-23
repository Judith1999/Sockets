/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;
import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ELMA
 */
public class Servidor {
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}
class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
                Thread mihilo=new Thread(this);
                mihilo.start();
		
		}
	
	private	JTextArea areatexto;
        

    @Override
    public void run() {
            try {
                //System.out.println("Estoy a la espera");
                ServerSocket servidor =new ServerSocket(9999);
                String nick, ip, mensaje;
                PaqueteEnvio paquete_recibido;
                
                while(true){
                Socket misocket=servidor.accept();
                
                ObjectInputStream paquete_datos =new ObjectInputStream(misocket.getInputStream());
                paquete_recibido=(PaqueteEnvio) paquete_datos.readObject();
                nick=paquete_recibido.getNick();
                ip=paquete_recibido.getIp();
                mensaje=paquete_recibido.getMensaje();
                
               // DataInputStream flujo_entrada=new DataInputStream(misocket.getInputStream());
               // String mensaje_texto=flujo_entrada.readUTF();
                //areatexto.append("\n"+ mensaje_texto);
               areatexto.append("\n" + nick + ":" + mensaje + "para" + ip);
                Socket enviaDestinatario=new Socket(ip, 9090);
                 ObjectOutputStream paqueteReenvio =new ObjectOutputStream(enviaDestinatario.getOutputStream());
                 paqueteReenvio.writeObject(paquete_recibido);
                paqueteReenvio.close();
                 enviaDestinatario.close();
                
             misocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                
            } 
            
    }
    
}
