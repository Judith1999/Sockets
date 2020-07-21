/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ELMA
 */
public class Cliente {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}



class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
                EnviaTexto mievento =new EnviaTexto();
                miboton.addActionListener(mievento);
		add(miboton);	
		
	}
	
    private class EnviaTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                //System.out.println(campo1.getText());
                Socket misocket=new Socket("192.168.1.80",9999);
                
            } catch (IOException ex) {
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
            
            
            
        }
    
    
    }		
		
	private JTextField campo1;
	
	private JButton miboton;
	
}