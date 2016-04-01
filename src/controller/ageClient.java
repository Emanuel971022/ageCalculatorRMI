package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

public class ageClient {
	public static void main(String [] arg){
		AgeRMI rmiServer;
		
		Registry registry;
		String ADDRESS = "172.18.22.1";
		String PORT = "3232";
		int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su fecha de nacimiento"));
		
		try{
			registry = LocateRegistry.getRegistry(ADDRESS, (new Integer(PORT).intValue()));
			rmiServer = (AgeRMI) (registry.lookup("server"));
			
			String mensaje = rmiServer.ageFunction(year);
			System.out.println(mensaje);
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
