package CheckersClient007;

import javax.swing.JOptionPane;

public class GUIRender {
	
	private ClientController clientController;
	private ClientLobbyGUIFrame clientGUI;
	
	public GUIRender(ClientController controller, ClientLobbyGUIFrame gui)
	{
		clientController = controller;
		clientGUI = gui;
	}
	
	public void joinLobby()
	{
		clientGUI.switchToTable();
	}
	
	public void newGlobalMessage(String who, String message)
	{
		clientGUI.getLobbyPanel().getChatPanel().addNewMessage( message, who, true);
		clientGUI.getBoardPanel().getChatPanel().addNewMessage( message, who, true);
	}
	
	public void newPMMessage(String who, String message)
	{
		clientGUI.getLobbyPanel().getChatPanel().addNewMessage( message, who, false);
		clientGUI.getBoardPanel().getChatPanel().addNewMessage( message, who, false);
	}
	
	public void newSystemMessage(String message)
	{
		clientGUI.getLobbyPanel().getChatPanel().addNewMessage( message, "SYSTEM MESSAGE", true);
		clientGUI.getBoardPanel().getChatPanel().addNewMessage( message, "SYSTEM MESSAGE", true);
	}
	
	public void showError(String error)
	{
		JOptionPane.showMessageDialog(clientGUI
                , error
                , "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public void updatePlayerList(String[] playerList)
	{
		clientGUI.getLobbyPanel().setPlayerList(playerList);
		clientGUI.getLobbyPanel().getChatPanel().updatePlayerList(playerList);
		clientGUI.getBoardPanel().getChatPanel().updatePlayerList(playerList);
	}
	
	public void updateTableList(int[] tableIdList)
	{
		clientGUI.getLobbyPanel().setTableList(tableIdList);
		
	}
	
	public void joinTable()
	{
		clientGUI.switchToCheckersBoard();
	}
	
	// will change the text to "Waiting on opponent. . . "
	public void clientReadys()
	{
		
	}
	
	// will change the text to "Opponent is ready to play."
	public void opponentReadys()
	{
		this.clientGUI.getBoardPanel().updateStatus("Opponent is ready to play.");		
	}
	
	public void startGame(Boolean ourColor /*red=0,black=1*/, byte[][] board)
	{
		// other crap on start up should probably go here
		// call updateGameBoard at some point
		updateGameBoard(board);
	}
	
	public void updateGameBoard(byte[][] board /*0=empty, 1=black, 2=red*/)
	{
		
		Board gameBoard = this.clientGUI.getBoardPanel().getBoard();
		gameBoard.clearAllPieces();
		for(int row = 0; row < board.length; row++)
		{
			for(int col = 0; col < board.length; col++)
			{
				if (board[row][col] == 1) {
					gameBoard.setPiece(row, col, PieceType.BLACK);
				}
				else if(board[row][col] == 2)
				{
					gameBoard.setPiece(row, col, PieceType.RED);
				}
			}
		}
		gameBoard.repaint();
	}
	
	public void clientWon()
	{
		
	}
	
	public void clientLost()
	{
		
	}
	
	public void updatePlayerTurn(Boolean clientsTurn/*true=client's turn, else opponents turn*/)
	{
		Board gameBoard = this.clientGUI.getBoardPanel().getBoard();
		gameBoard.setMoveStatus(clientsTurn);
	}
	
	
	public void observingBoard(byte[][] board /*0=empty, 1=black, 2=red*/, int tableId)
	{
		
		Board gameBoard = this.clientGUI.getBoardPanel().getBoard();
		gameBoard.setMoveStatus(false);
		updateGameBoard(board);
	}
	
	public void updateObservationBoard(byte[][] board /*0=empty, 1=black, 2=red*/, int tableId)
	{
		updateGameBoard(board);
	}
	
	public void addNewTable(int tableId)
	{
		this.clientGUI.getLobbyPanel().addNewTable(tableId);
	}
	
	public void addNewPlayer(String user)
	{
		clientGUI.getLobbyPanel().addNewPlayer(user);
		clientGUI.getLobbyPanel().getChatPanel().addPlayer(user);
		clientGUI.getBoardPanel().getChatPanel().addPlayer(user);
	
	}
	
	public void removePlayer(String user)
	{
		clientGUI.getLobbyPanel().removePlayer(user);
		clientGUI.getLobbyPanel().getChatPanel().removePlayer(user);
		clientGUI.getBoardPanel().getChatPanel().removePlayer(user);
	}
	
	public void gameStart()
	{
		clientGUI.getBoardPanel().setReadyUp(false);
		clientGUI.getBoardPanel().setStatus("Game Started");
		clientGUI.getBoardPanel().getBoard().initialize();
	}
	
	public void setTableJoinable(boolean isJoinable)
	{
		clientGUI.getLobbyPanel().makeTableJoinable(isJoinable);
	}
	
	public void setPlayerColor(boolean isBlack)
	{
		PieceType playerColor;
		if(isBlack)
		{
			playerColor = PieceType.BLACK;
		}
		else
		{
			playerColor = PieceType.RED;
		}
		clientGUI.getBoardPanel().getBoard().setPlayerColor(playerColor);
	}
	
	public void allowClientMoves(boolean isAllowed)
	{
		clientGUI.getBoardPanel().getBoard().setMoveStatus(isAllowed);
	}
	
	
}
