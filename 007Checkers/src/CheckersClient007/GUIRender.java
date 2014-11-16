package CheckersClient007;

import java.util.HashMap;

import javax.swing.JOptionPane;

public class GUIRender {

	private ClientController clientController;
	private ClientLobbyGUIFrame clientGUI;
	// actual game played by the user tid
	private int ourTID;
	private HashMap<Integer, ObserveFrame> observations;

	public GUIRender(ClientController controller, ClientLobbyGUIFrame gui) {
		clientController = controller;
		clientGUI = gui;
		observations = new HashMap<>();
	}

	public void joinLobby()
	{
		this.ourTID = -1;
		clientGUI.getLobbyPanel().getChatPanel().setCanGlobalMessage(true);
		clientGUI.getBoardPanel().getChatPanel().setCanGlobalMessage(true);
		clientGUI.switchToTable();
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
		gameBoard.repaint();
	}

	public void clientWon() {
		JOptionPane.showMessageDialog(clientGUI, "You're a winner!",
				"You're a winner!", JOptionPane.PLAIN_MESSAGE);
	}

	public void clientLost() {
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
		// clientGUI.getLobbyPanel().getChatPanel().addPlayer(user);
		// clientGUI.getBoardPanel().getChatPanel().addPlayer(user);

	}

	public void removePlayer(String user) {
		clientGUI.getLobbyPanel().removePlayer(user);
		// clientGUI.getLobbyPanel().getChatPanel().removePlayer(user);
		// clientGUI.getBoardPanel().getChatPanel().removePlayer(user);
	}

	public void gameStart() {
		clientGUI.getBoardPanel().setReadyUp(false);
		clientGUI.getBoardPanel().setStatus("Game Started");
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
		PieceType playerColor;
		if (isBlack) {
			playerColor = PieceType.BLACK;
		} else {
			playerColor = PieceType.RED;
		}
		clientGUI.getBoardPanel().getBoard()
				.setPlayerColor(playerColor.getColor());
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
	

}
