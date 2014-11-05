package CheckersClient007;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.SwingUtilities;

import RMIConnection.ClientConnection;
import RMIConnection.Interfaces.CheckersClient;
import RMIConnection.Interfaces.RMIServerInterface;

public class ClientController implements CheckersClient
{
	
	private static GUIRender guiRender;
	private static ClientConnection connection;
	private static RMIServerInterface serverConnection;
	
	private static ClientController thisController;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {    
				ClientController tester = new ClientController();
				
//				tester.setLocationRelativeTo(null);
//				tester.setVisible(true);
				System.setProperty("java.security.policy","file:./src/CheckersClient007/client.policy");
				System.setProperty("java.rmi.server.codebase", "file:./bin/");
				
				//now establish a presence in the RMI registry and try to get the checkers server connector.
				try{   
					connection = new ClientConnection();
		    		//generate a random registry id for this player
			    	String name = "CheckersClient"+(int)(Math.random()*10000);
			    		//export the player to the registry. Stub is a reference to the object in the reg.
			        CheckersClient stub =
			            (CheckersClient) UnicastRemoteObject.exportObject((CheckersClient)tester, 0);
			        //get the registry
			        Registry registry = LocateRegistry.getRegistry();

			        //bind the object in registry to the unique registry id we generated
			        registry.rebind(name, stub);
			        connection.registerPlayer(name, "127.0.0.1");
			        
			        // TESTING THE GUI:
			        ClientLobbyGUIFrame frame = new ClientLobbyGUIFrame(tester);
					frame.setVisible(true);
					guiRender = new GUIRender(tester, frame);
					
			        System.out.println("TestClient bound to registry!");
			        //connect to the RMI server connection on this pc (localhost) and give it the id of this client.
			    	tester.getServerConnection("localhost", name);
			    	
			    	//add a hook to disconnect for when the user force quits / alt+f4 / cmd+q's
			    	Runtime.getRuntime().addShutdownHook(new Thread()
			    	{
			    	    @Override
			    	    public void run()
			    	    {
							try {
								
								serverConnection.disconnect(false);
							} catch (RemoteException e) {
								/** 
								 * Now cracks a noble heart. Good-night, sweet prince;
								 * And flights of angels sing thee to thy rest.
								**/
							}
			    	    }
			    	});
			    	
			    }catch(RemoteException e){
			    	System.out.println("Error binding client to registry.");
			    	System.out.println(e.getMessage());
			    }			
			}
		});
	}
//	public ClientConnection getClientConnection()
//	{
//		return connection;
//	}
	private void getServerConnection(String host, String clientID) {
		if (System.getSecurityManager() == null) {
			 System.setSecurityManager(new SecurityManager());
	    }
	    try {
	        String name = "CheckersServerInterface";
	        Registry registry = LocateRegistry.getRegistry(host);
	        serverConnection = (RMIServerInterface) registry.lookup(name);
	        if(serverConnection != null){
	        	System.out.println("Server connection found in registry!");
	        	serverConnection.registerPlayer(clientID, host);
	        }
	        else{
	        	System.out.println("Could not register with the server");	        	
	        	System.exit(0);
	        }
	    } catch (Exception e) {
	        System.err.println("TestClient Exception:");
	        e.printStackTrace();
	    }
	}	
	
	public ClientController()
	{
		
	}
	
	public static ClientController getInstance()
	{
		if(thisController == null)
		{
			thisController = new ClientController();
		}
		return thisController;
	}
	
	public ClientConnection getClientConnection()
	{
		return this.connection;
	}

	@Override
	public void connectionOK() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youInLobby() throws RemoteException // TCP: 218
	{
		guiRender.joinLobby();
	}

	@Override
	public void youLeftLobby() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMsg(String user, String msg, boolean pm)
			throws RemoteException 
	{
		if(pm)
		{
			guiRender.newPMMessage(user, msg);
		}
		else
		{
			guiRender.newGlobalMessage(user, msg);
		}
		
	}

	@Override
	public void usersInLobby(String[] users) throws RemoteException // TCP: 212
	{
		guiRender.updatePlayerList(users);
		
	}

	@Override
	public void nowJoinedLobby(String user) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowLeftLobby(String user) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newTable(int tid) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinedTable(int tid) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alertLeftTable() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameStart() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void colorBlack() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void colorRed() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState)
			throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youWin() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youLose() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTable(int tid, String blackSeat, String redSeat)
			throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableList(int[] tids) throws RemoteException
	{
		guiRender.updateTableList(tids);
		
	}

	@Override
	public void yourTurn() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowObserving(int tid) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoppedObserving(int tid) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void networkException(String msg) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameInUseError() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameIllegal() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void illegalMove() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableFull() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tblNotExists() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameNotCreatedYet() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notYourTurn() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notObserving() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppNotReady() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorInLobby() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void badMessage() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppLeftTable() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notInLobby() throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}
	
	public void disconnect(boolean endProcess/*if d/c from user exiting*/) // TCP: 108
	{
		connection.disconnect(endProcess);
	}
	
	public void joinTable(int tableId) // TCP: 104
	{
		connection.joinTable(tableId);
	}
	
	public void leaveTable() // TCP: 107
	{
		connection.leaveTable();
	}
	
	public void makeTable() // TCP: 103
	{
		connection.makeTable();
	}
	
	public void move(int fromRow, int fromColumn, int toRow, int toColumn) // TCP: 106
	{
		connection.move(fromRow, fromColumn, toRow, toColumn);
	}
	
	public void ready() // TCP: 105
	{
		connection.ready();
	}
	
	public void sendMessage(String targetUser, String message) // TCP: 102
	{
		connection.sendMsg(targetUser, message);
	}
	
	public void sendGlobalMessage(String message) // TCP: 101
	{
		connection.sendMsg_All(message);
	}
	
	// GAME PLAYING METHODS
	// Note: we are throwing RemoteException, should we change this later?
	
	public void getTableStatus(String user, int tableId) throws RemoteException // TCP: 109
	{
		connection.getTblStatus(user, tableId);
	}
	
	public void joinTable(String user, int tableId) throws RemoteException // TCP: 104
	{
		connection.joinTable(user, tableId);
	}
	
	public void leaveTable(String user) throws RemoteException // TCP: 107
	{
		connection.leaveTable(user);
	}
	
	public void makeTable(String user) throws RemoteException // TCP: 103
	{
		connection.makeTable(user);
	}
	
}