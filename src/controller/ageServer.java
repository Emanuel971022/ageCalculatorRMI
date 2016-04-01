package controller;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ageServer extends java.rmi.server.UnicastRemoteObject implements AgeRMI{
	private int port;
	private String adress;
	private Registry registry;
	
	public ageServer() throws RemoteException{
	}
	
	public String ageFunction(int age){
		return "Si usted ya cumplió años este año, entonces tiene "+(2016-age)+", de lo contrario tiene "+((2015-age)-1);
	}
	
	public void startServer() throws Exception {
		System.out.println("Server is running now");
		try{
			adress = (InetAddress.getLocalHost().toString());
			System.out.println(adress);
		}catch(Exception ex){
			throw new RemoteException("Can't get inet adress");
		}
		
		port = 3232;
		
		try{
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("server", (AgeRMI) this);
		}catch(Exception ex){
			throw ex;
		}
	}
	
	public static void main(String [] arg){
		try{
			ageServer a = new ageServer();
			a.startServer();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
