package CheckersClient007;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

public class GUIRender {
	private ArrayList<Byte[][]> allBoardStates = new ArrayList<>();
	private ClientLobbyGUIFrame clientGUI;
	private boolean isBlack;
	private  PersonalStatsModel personalStatsModel;
	// actual game played by the user tid
	private int ourTID;
	private HashMap<Integer, ObserveFrame> observations;
	private byte[][] prevState = null;
	public GUIRender(ClientLobbyGUIFrame gui) {
		clientGUI = gui;
		observations = new HashMap<>();
		
	}

	public void joinLobby()
	{
		this.ourTID = -1;
		clientGUI.getLobbyPanel().getChatPanel().setCanGlobalMessage(true);
		clientGUI.getBoardPanel().getChatPanel().setCanGlobalMessage(true);
		clientGUI.switchToTable();
		clientGUI.setStatsEnabled(true);	
	}

	public void newGlobalMessage(String who, String message) {
		clientGUI.getLobbyPanel().getChatPanel()
				.addNewMessage(message, who, true);
		clientGUI.getBoardPanel().getChatPanel()
				.addNewMessage(message, who, true);
	}

	public void newPMMessage(String who, String message) {
		clientGUI.getLobbyPanel().getChatPanel()
				.addNewMessage(message, who, false);
		clientGUI.getBoardPanel().getChatPanel()
				.addNewMessage(message, who, false);
	}

	public void newSystemMessage(String message) {
		clientGUI.getLobbyPanel().getChatPanel().addNewSystemMessage(message);
		clientGUI.getBoardPanel().getChatPanel().addNewSystemMessage(message);
	}

	public void showError(String error) {
		JOptionPane.showMessageDialog(clientGUI, error, "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}

	public void updatePlayerList(String[] playerList) {
		clientGUI.getLobbyPanel().setPlayerList(playerList);
		// clientGUI.getLobbyPanel().getChatPanel().updatePlayerList(playerList);
		// clientGUI.getBoardPanel().getChatPanel().updatePlayerList(playerList);
	}

	public void updateTableList(int[] tableIdList) {
		clientGUI.getLobbyPanel().setTableList(tableIdList);

	}

	public void joinTable(int tid) {
		this.ourTID = tid;
		clientGUI.getLobbyPanel().getChatPanel().setCanGlobalMessage(false);
		clientGUI.getBoardPanel().getChatPanel().setCanGlobalMessage(false);
		clientGUI.switchToCheckersBoard();
	}

	public void opponentReadys()
	{
		this.clientGUI.getBoardPanel().updateStatus(
				"Opponent is ready to play.");
	}

	public void updateGameBoard(int tid, byte[][] board /*
														 * 0=empty, 1=black,
														 * 2=red
														 */) {
		Board gameBoard = null;
		if (observations.containsKey(tid)) {
			gameBoard = observations.get(tid).getBoard();
		}

		else if (tid == ourTID) {
			
			gameBoard = clientGUI.getBoardPanel().getBoard();
	
		}

		gameBoard.clearAllPieces();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col] == 1) {
					gameBoard.setPiece(row, col, PieceType.BLACK);
				} else if (board[row][col] == 2) {
					gameBoard.setPiece(row, col, PieceType.RED);
				} else if (board[row][col] == 3)
					gameBoard.setPiece(row, col, PieceType.BLACK_KING);
				else if (board[row][col] == 4)
					gameBoard.setPiece(row, col, PieceType.RED_KING);
			}
		}
		if( tid == ourTID)
		{
			if(prevState != null )
			{
				int userPieceType = 0;
				int opponentPieceType = 0;
				if(isBlack)
				{
					userPieceType = 1;
					opponentPieceType = 2;
				}
				else{
					userPieceType = 2;
					opponentPieceType = 1;
				}
				// increment if user has jumped an opponent's piece
				if(this.jumpedPiece(board, userPieceType ))
				{
					this.personalStatsModel.setNumberOfPiecesTaken(this.personalStatsModel.getNumberOfPiecesTaken() + 1);
				}
				
				// increment if oppponent has jumped a user's piece
				if(this.jumpedPiece(board, opponentPieceType ))
				{
					this.personalStatsModel.setNumberOfPiecesLost((this.personalStatsModel.getNumberOfPiecesLost() + 1));
				}
				
				// increment if user has promoted a checker's piece
				if(this.isMoreKings(board,userPieceType ))
				{
					this.personalStatsModel.setNumberOfKingsEarned(this.personalStatsModel.getNumberOfKingsEarned() + 1);
					
				}
				// incremnt if opponent has promoted a checker's piece
				if(this.isMoreKings(board, opponentPieceType))
				{
					this.personalStatsModel.setNumberOfKingsAllowed(this.personalStatsModel.getNumberOfKingsAllowed()+1);
				}
				this.prevState = board;
			}
			else
			{
			 this.prevState = board;
			}
		}
		gameBoard.repaint();
	}

	public void clientWon() {
		this.clientGUI.getBoardPanel().reset();
		this.personalStatsModel.setNumberOfGamesWon(this.personalStatsModel.getNumberOfGamesWon() + 1);
		this.personalStatsModel.updateStatsEntries();
		JOptionPane.showMessageDialog(clientGUI, "You're a winner!",
				"You're a winner!", JOptionPane.PLAIN_MESSAGE);
	}

	public void clientLost() {
		
		this.clientGUI.getBoardPanel().reset();
		this.personalStatsModel.setNumberOfGamesLost(this.personalStatsModel.getNumberOfGamesLost()+1);
		this.personalStatsModel.updateStatsEntries();
		JOptionPane.showMessageDialog(clientGUI, "You're a loser!",
				"You're a loser!", JOptionPane.PLAIN_MESSAGE);
	}

	public void updatePlayerTurn(Boolean clientsTurn/*
													 * true=client's turn, else
													 * opponents turn
													 */) {
		Board gameBoard = this.clientGUI.getBoardPanel().getBoard();
		gameBoard.setMoveStatus(clientsTurn);
	}

	public void observingBoard(byte[][] board /* 0=empty, 1=black, 2=red */,
			int tableId) {

		Board gameBoard = this.clientGUI.getBoardPanel().getBoard();
		gameBoard.setMoveStatus(false);
		updateGameBoard(tableId, board);
	}

	public void updateObservationBoard(
			byte[][] board /* 0=empty, 1=black, 2=red */, int tableId) {
		updateGameBoard(tableId, board);
	}

	public void addNewTable(int tableId) {
		this.clientGUI.getLobbyPanel().addNewTable(tableId);
	}

	public void addNewPlayer(String user) {
		clientGUI.getLobbyPanel().addNewPlayer(user);

	}

	public void removePlayer(String user) {
		clientGUI.getLobbyPanel().removePlayer(user);

	}

	public void gameStart() {
		clientGUI.getBoardPanel().setReadyUp(false);
		clientGUI.getBoardPanel().setStatus("Game Started");
		if(this.personalStatsModel == null && ClientController.getInstance().getUserName() != null){
			personalStatsModel = new PersonalStatsModel(ClientController.getInstance().getUserName());
		}
		clientGUI.getBoardPanel().getBoard().initialize();
	}

	public void setTableFull(boolean isFull)
	{
		if(isFull)
		{
			clientGUI.getLobbyPanel().makeTableObservable(true);
			clientGUI.getLobbyPanel().makeTableJoinable(false);
		}
		else
		{
			clientGUI.getLobbyPanel().makeTableJoinable(true);
			clientGUI.getLobbyPanel().makeTableObservable(false);
		}
	}

	public void updateGameDescription(int tableId, String blackClient, String redClient)
	{
		String gameStats = "Table ID = ".concat(String.valueOf(tableId))
				.concat("\n");
		if (!blackClient.equals("-1") && !redClient.equals("-1")) // if both seats are full
		{
			gameStats = gameStats.concat("[Black] ").concat(blackClient).concat("\n");
			gameStats = gameStats.concat("[Red] ").concat(redClient).concat("\n");
		}
		else if (blackClient.equals("-1") ^ redClient.equals("-1"))
		{
			gameStats = gameStats.concat("[Player] ");
			gameStats = blackClient.equals("-1") ? gameStats.concat(redClient)
					: gameStats.concat(blackClient);
			gameStats = gameStats.concat("\n")
					.concat("<i>One seat open.</i>\n");
		} 
		else
		{
			gameStats = gameStats.concat("<i>Game is empty.</i>").concat("\n");
		}
		clientGUI.getLobbyPanel().updateGameDescription(gameStats);
	}

	public void setPlayerColor(boolean isBlack) {
		this.isBlack = isBlack;
		PieceType playerColor;
		boolean invertBoard = false;
		if (isBlack) {
			playerColor = PieceType.BLACK;
			invertBoard = true;
		} else {
			playerColor = PieceType.RED;
			invertBoard = false;
		}
		clientGUI.getBoardPanel().getBoard()
				.setPlayerColor(playerColor.getColor());
		clientGUI.getBoardPanel().getBoard().setViewInverted(invertBoard);;
	}

	public void allowClientMoves(boolean isAllowed) {
		clientGUI.getBoardPanel().getBoard().setMoveStatus(isAllowed);
	}

	public void nowObserving(int tid) {
		if(!observations.containsKey(tid))
		{
			ObserveFrame f = new ObserveFrame(tid);
			f.setSize(400, 400);
			f.setVisible(true);
			f.getBoard().setMoveStatus(false);
			observations.put(tid, f);
		}
		
		clientGUI.getLobbyPanel().getChatPanel().setCanPM(false);
		clientGUI.getBoardPanel().getChatPanel().setCanPM(false);
	}

	public void stopObserving(int tid) {
		ObserveFrame f = observations.get(tid);
		if (f != null) {
			f.dispose();
			observations.remove(tid);
		}
		
		if(observations.keySet().isEmpty())
		{
			clientGUI.getLobbyPanel().getChatPanel().setCanPM(true);
			clientGUI.getBoardPanel().getChatPanel().setCanPM(true);
		}
	}
	public void removeObserver(int tid)
	{
		stopObserving(tid);
	
	}
	public void repaintAllBoards()
	{
		for (Map.Entry<Integer, ObserveFrame> entry : observations.entrySet())
		{
		   entry.getValue().repaint();
		}
		clientGUI.getBoardPanel().getBoard().repaint();
	}
	// return true - a 
	private boolean jumpedPiece(byte[][] boardState, int pieceType)
	{
		int numberOfPiecesPrev  =  0;
		int numberOfPiecesNow = 0;
		
		// number of pieces prev
		for(int i = 0; i < this.prevState.length; i++)
		{
			for(int j = 0; j < this.prevState[i].length; j++)
			{
				if(pieceType == 1 || pieceType == 3)
				{
					if(this.prevState[i][j] == 2 || prevState[i][j] == 4)
					{
						numberOfPiecesPrev++;
					}
					
				}
				else{
					if(this.prevState[i][j] == 1 || prevState[i][j] == 3)
					{
						numberOfPiecesPrev++;
					}
				}
				
			}
		}
		
		for(int i = 0; i < boardState.length; i++)
		{
			for(int j = 0; j < boardState[i].length; j++)
			{
				if(pieceType == 1 || pieceType == 3)
				{
					if(boardState[i][j] == 2 || boardState[i][j] == 4)
					{
						numberOfPiecesNow++;
					}
					
				}
				else{
					if(boardState[i][j] == 1 || boardState[i][j] == 3)
					{
						numberOfPiecesNow++;
					}
				}
				
			}
		}
		System.out.println("Prev: " + numberOfPiecesPrev);
		System.out.println("Now: " + numberOfPiecesNow);
		return (numberOfPiecesPrev > numberOfPiecesNow);
	}
	
	private boolean isMoreKings(byte[][] boardState , int pieceType)
	{
		int numberOfPiecesPrev  =  0;
		int numberOfPiecesNow = 0;
		
		// number of pieces prev
		for(int i = 0; i < this.prevState.length; i++)
		{
			for(int j = 0; j < this.prevState[i].length; j++)
			{
				if(pieceType == 1 || pieceType == 3)
				{
					if(prevState[i][j] == 3)
					{
						numberOfPiecesPrev++;
					}
					
				}
				else{
					if( prevState[i][j] == 4)
					{
						numberOfPiecesPrev++;
					}
				}
				
			}
		}
		
		for(int i = 0; i < boardState.length; i++)
		{
			for(int j = 0; j < boardState[i].length; j++)
			{
				if(pieceType == 1 || pieceType == 3)
				{
					if(boardState[i][j] == 3)
					{
						numberOfPiecesNow++;
					}
					
				}
				else{
					if( boardState[i][j] == 4)
					{
						numberOfPiecesNow++;
					}
				}
				
			}
		}

		return (numberOfPiecesPrev < numberOfPiecesNow);
	}
	public void addPersonalStatsView()
	{
		if( ClientController.getInstance().getUserName() != null && this.personalStatsModel == null ){
			personalStatsModel = new PersonalStatsModel(ClientController.getInstance().getUserName());
		}
		if(ClientController.getInstance().getUserName() != null)
		{
		 PersonalStats personalStats = new PersonalStats(ClientController.getInstance().getUserName());
		personalStats.setVisible(true);
		personalStats.setSize(200,200);
		this.personalStatsModel.addView(personalStats);
		this.personalStatsModel.updateViews();
		}
		
	}

}
