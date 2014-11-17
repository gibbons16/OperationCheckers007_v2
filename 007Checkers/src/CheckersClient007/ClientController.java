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
	
	private String userName;
	
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
	        	this.userName = clientID;
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
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Override
	public void connectionOK() throws RemoteException // TCP: 200
	{
		
	}

	@Override
	public void youInLobby() throws RemoteException // TCP: 218
	{
		guiRender.joinLobby();
		guiRender.newSystemMessage("You are now in the lobby.");
	}

	@Override
	public void youLeftLobby() throws RemoteException // TCP: 213
	{
		guiRender.newSystemMessage("You have left the lobby.");
	}

	@Override
	public void newMsg(String user, String msg, boolean pm) // TCP: 201
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
	public void nowJoinedLobby(String user) throws RemoteException // TCP: 214
	{
		guiRender.addNewPlayer(user);
	}

	@Override
	public void nowLeftLobby(String user) throws RemoteException // TCP: 217
	{
		guiRender.removePlayer(user);
		
	}

	@Override
	public void newTable(int tid) throws RemoteException // TCP: 202
	{
		guiRender.addNewTable(tid);
	}

	@Override
	public void joinedTable(int tid) throws RemoteException // TCP: 210
	{
		guiRender.joinTable(tid);
		guiRender.newSystemMessage("You have joined table ".concat(String.valueOf(tid)).concat("."));
	}

	@Override
	public void alertLeftTable() throws RemoteException // TCP: 222
	{
	}

	@Override
	public void gameStart() throws RemoteException // TCP: 203
	{
		guiRender.newSystemMessage("The game has started.");
		guiRender.gameStart();
	}

	@Override
	public void colorBlack() throws RemoteException // TCP: 204
	{
		guiRender.newSystemMessage("You are playing as black.");
		guiRender.setPlayerColor(true);
		
	}

	@Override
	public void colorRed() throws RemoteException // TCP: 205
	{
		guiRender.newSystemMessage("You are playing as red.");
		guiRender.setPlayerColor(false);
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) throws RemoteException // TCP: 206
	{
		// not implemented, board updated through 207
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState) //TCP: 207
			throws RemoteException
	{
		guiRender.updateGameBoard(tid, boardState);
	}

	@Override
	public void youWin() throws RemoteException // TCP: 208
	{
		
		guiRender.clientWon();
	}

	@Override
	public void youLose() throws RemoteException // TCP: 209
	{
		guiRender.clientLost();
	}

	@Override
	public void onTable(int tid, String blackSeat, String redSeat) // TCP: 219
			throws RemoteException
	{
		if(blackSeat.equals("-1") || redSeat.equals("-1"))
		{
			guiRender.setTableFull(false);
		}
		else
		{
			guiRender.setTableFull(true);
		}
		if(blackSeat.equals(this.userName) && !redSeat.equals("-1"))
		{
			guiRender.newSystemMessage("You are playing against ".concat(redSeat));
		}
		else if(redSeat.equals(this.userName) && !blackSeat.equals("-1"))
		{
			guiRender.newSystemMessage("You are playing against ".concat(blackSeat));
		}
		guiRender.updateGameDescription(tid, blackSeat, redSeat);
	}

	@Override
	public void tableList(int[] tids) throws RemoteException // TCP: 216
	{
		guiRender.updateTableList(tids);
	}

	@Override
	public void yourTurn() throws RemoteException // TCP: 221
	{
		guiRender.newSystemMessage("It is now your turn.");
		guiRender.allowClientMoves(true);
	}

	@Override
	public void nowObserving(int tid) throws RemoteException // TCP: 230
	{
		guiRender.nowObserving(tid);
		guiRender.newSystemMessage("You are now observing table ".concat(String.valueOf(tid)).concat("."));
	}

	@Override
	public void stoppedObserving(int tid) throws RemoteException // TCP: 235
	{
		guiRender.stopObserving(tid);
		guiRender.newSystemMessage("You are no longer observing ".concat(String.valueOf(tid)).concat("."));
	}

	@Override
	public void networkException(String msg) throws RemoteException // TCP: 400
	{
		
	}

	@Override
	public void nameInUseError() throws RemoteException // TCP: 401
	{
		guiRender.showError("That name is already in use.");
	}

	@Override
	public void nameIllegal() throws RemoteException // TCP: 408
	{
		guiRender.showError("Username is not allowed to have whitespaces.");
	}

	@Override
	public void illegalMove() throws RemoteException // TCP: 402
	{
		guiRender.showError("Illegal move.");
		guiRender.allowClientMoves(true);
	}

	@Override
	public void tableFull() throws RemoteException // TCP: 403
	{
		guiRender.showError("Table full.");
	}

	@Override
	public void tblNotExists() throws RemoteException // TCP: 411
	{
		
	}

	@Override
	public void gameNotCreatedYet() throws RemoteException // TCP: 412
	{
		
	}

	@Override
	public void notYourTurn() throws RemoteException // TCP: 410
	{
		guiRender.showError("It is the opponent's turn.");
	}

	@Override
	public void notObserving() throws RemoteException // TCP: 415
	{
		
	}

	@Override
	public void oppNotReady() throws RemoteException // TCP: 409
	{
		
	}

	@Override
	public void errorInLobby() throws RemoteException // TCP: 406
	{
		
	}

	@Override
	public void badMessage() throws RemoteException // TCP: 405
	{
		
	}

	@Override
	public void oppLeftTable() throws RemoteException // TCP: 220
	{
		guiRender.newSystemMessage("The opponent has left the game.");
	}

	@Override
	public void notInLobby() throws RemoteException // TCP: 404
	{
		
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
		if(targetUser.equals(this.userName))
		{
			connection.sendMsg(targetUser, message);
		}
		else
		{
			guiRender.showError("Don't message yourself!");
		}
	}
	
	public void sendGlobalMessage(String message) // TCP: 101
	{
		connection.sendMsg_All(message);
	}
	
	// GAME PLAYING METHODS
	// Note: we are throwing RemoteException, should we change this later?
	
	public void getTableStatus(int tableId) throws RemoteException // TCP: 109
	{
		connection.getTblStatus(this.userName, tableId);
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
	
	public void observeTable(int tableId) throws RemoteException // TCP: 110
	{
		connection.observeTable(this.userName, tableId);
	}
	public void removeObserving(int tableId)
	{
		guiRender.removeObserver(tableId);
	}
	public void repaintBoards()
	{
		guiRender.repaintAllBoards();
	}
	
}