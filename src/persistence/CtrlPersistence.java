package persistence;

import java.io.*;
import java.util.HashMap;

import domain.CtrlDomain;
import exceptions.*;

public class CtrlPersistence {
	private HashMap<String,Integer> IDs;
	private String savingDirectory; //everything is saved  in the same place
	private File IDsFile;
	private CtrlDomain ctrlDomain;
	
	public CtrlPersistence(CtrlDomain ctrlDomain) {
		this.ctrlDomain = ctrlDomain;
		this.savingDirectory = "src/persistence/DATA/";
		IDsFile = new File(this.savingDirectory + "IDs.list");
		if (!IDsFile.exists()) {
			IDs = new HashMap<String,Integer>();
			IDs.put("FiveGuess", 0);
			IDs.put("Genetic", 1);
			saveObject(IDsFile, IDs);
			Object p1 = this.ctrlDomain.getInstanceOfPlayer("FiveGuess");
			Object p2 = this.ctrlDomain.getInstanceOfPlayer("Genetic");
			saveAi("FiveGuess", p1);
			saveAi("Genetic", p2);
		}else {
			IDs = (HashMap<String,Integer>) loadObject(IDsFile);
		}
	}
	
	private void saveObject(File dir, Object obj) {
		try {
			FileOutputStream fos = new FileOutputStream(dir, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("this shouldn't happen: FileNotFoundException when saving object "+obj+ " to "+dir);
		} catch (IOException e) {
			System.out.println("this shouldn't happen: IOException when saving object "+obj+ " to "+dir);
		}
	}
	
	private void saveObject(String filename, Object obj) {
		File dir = new File(savingDirectory + filename);
		saveObject(dir,obj);
	}
	
	private Object loadObject(File dir) {
		try {
			FileInputStream fis = new FileInputStream(dir);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object tmp = ois.readObject();
			ois.close();
			return tmp;
		} catch (FileNotFoundException e) {
			System.out.println("this shouldn't happen: FileNotFoundException when loading file "+dir);
		} catch (IOException e) {
			System.out.println("this shouldn't happen: IOException when loading file "+dir);
		} catch (ClassNotFoundException e) {
			System.out.println("this shouldn't happen: ClassNotFoundException when loading file "+dir);
		}
		return null; //we should never get here
	}
	
	private Object loadObject(String filename) {
		File dir = new File(savingDirectory + filename);
		return loadObject(dir);
	}
	
	
	//ctrlPersistence.saveGame(activeUser.getPlayerName(), gameId, activeGame);
	//ctrlPersistence.saveUser(activeUser.getPlayerName(), activeUser);
	
	public void saveGame(String username, String gamename, Object game) throws UnexistingUser {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player already has a ID
			playerID = IDs.get(username);
		}else {
			throw new UnexistingUser();
		}
		
		//Loading the HashMap for the gameIDs of the player
		File gameIDsFile = new File(this.savingDirectory + playerID + ".gameIDs.list");
		HashMap<String,Integer> gameIDs;
		if (!gameIDsFile.exists()) {
			gameIDs = new HashMap<String,Integer>();
		}else {
			gameIDs = (HashMap<String,Integer>) loadObject(gameIDsFile);
		}
		
		//Searching for the game for the player
		Integer gameID = -1;
		if (gameIDs.containsKey(gamename)) {
			//Game already has a ID
			gameID = gameIDs.get(gamename);
		}else {
			//Game didn't have a ID
			gameID = gameIDs.size();
			gameIDs.put(gamename, gameID);
			//Saving gameIDs file with the new gameID
			saveObject(gameIDsFile, gameIDs);
		}
		
		// write game to file
		saveObject(playerID+ "." + gameID +".game", game);
		
	}
	
	public Object loadGame(String username, String gamename) throws UnexistingUser, GameUnexistentForUser {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player already has a ID
			playerID = IDs.get(username);
		}else {
			throw new UnexistingUser();
		}
		
		//Loading the HashMap for the gameIDs of the player
		File gameIDsFile = new File(this.savingDirectory + playerID + ".gameIDs.list");
		HashMap<String,Integer> gameIDs;
		if (!gameIDsFile.exists()) {
			throw new GameUnexistentForUser();
		}else {
			gameIDs = (HashMap<String,Integer>) loadObject(gameIDsFile);
		}
		
		//Searching for the game for the player
		Integer gameID = -1;
		if (gameIDs.containsKey(gamename)) {
			//Game already has a ID
			gameID = gameIDs.get(gamename);
		}else {
			//Game didn't have a ID
			throw new GameUnexistentForUser();
		}
		
		// loading and returning game
		return loadObject(playerID+ "." + gameID +".game");
		
	}
	
	public void deleteGame(String username, String gamename) throws UnexistingUser, GameUnexistentForUser {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player already has a ID
			playerID = IDs.get(username);
		}else {
			throw new UnexistingUser();
		}
		
		//Loading the HashMap for the gameIDs of the player
		File gameIDsFile = new File(this.savingDirectory + playerID + ".gameIDs.list");
		HashMap<String,Integer> gameIDs;
		if (!gameIDsFile.exists()) {
			throw new GameUnexistentForUser();
		}else {
			gameIDs = (HashMap<String,Integer>) loadObject(gameIDsFile);
		}
		
		//Searching for the game for the player
		Integer gameID = -1;
		if (gameIDs.containsKey(gamename)) {
			//Game already has a ID
			gameID = gameIDs.get(gamename);
		}else {
			//Game didn't have a ID
			throw new GameUnexistentForUser();
		}
		
		gameIDs.remove(gamename);
		File dir = new File(savingDirectory + playerID+ "." + gameID +".game");
		dir.delete();
		
	}
	
	public void saveAi(String playername, Object p) {
		Integer playerID= -1;
		//get or add ID of player	
		//Searching for the player
		if (IDs.containsKey(playername)) {
			//Player already has a ID
			playerID = IDs.get(playername);
		}else {
			//Player didn't have a ID
			playerID = IDs.size();
			IDs.put(playername, playerID);
			//Saving IDs file with the new ID
			saveObject(IDsFile, IDs);
		}
		
		// write player to file
		if (playerID !=-1) {
			saveObject(playerID+".player", p);
		}else {
			System.out.println("Error playerID was -1");
		}
		
	}
	
	public Object loadAi(String playername) {
		Object result= null;
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(playername)) {
			//Player has an ID
			playerID = IDs.get(playername);
		}else {
			System.out.println("Player was never given a ID and saved");
		}

		
		if (playerID != -1) {
			result = loadObject(playerID+".player");
		}else {
			System.out.println("Error playerID was -1");
		}
		return result;
	}
	
	public void saveNewUser(String username, Object p) throws ExistingUser {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player already has a ID
			throw new ExistingUser();
		}else {
			//Player didn't have a ID
			playerID = IDs.size();
			IDs.put(username, playerID);
			//Saving IDs file with the new ID
			saveObject(IDsFile, IDs);
		}
		
		// write player to file
		if (playerID !=-1) {
			saveObject(playerID+".user", p);
		}else {
			//this should never execute
			throw new ExistingUser();
		}
		
	}
	
	public void saveUser(String username, Object p) {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player already has a ID
			playerID = IDs.get(username);
		}else {
			//Player didn't have a ID
			playerID = IDs.size();
			IDs.put(username, playerID);
			//Saving IDs file with the new ID
			saveObject(IDsFile, IDs);
		}
		
		// write player to file
		if (playerID !=-1) {
			saveObject(playerID+".user", p);
		}else {
			System.out.println("Error playerID was -1");
		}
		
	}
	
	public Object loadUser(String username) throws UnexistingUser {
		Object result= null;
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player has an ID
			playerID = IDs.get(username);
		}else {
			throw new UnexistingUser();
		}
		
		if (playerID != -1) {
			result = loadObject(playerID+".user");
		}else {
			//this should never execute
			throw new UnexistingUser();
		}
		return result;
	}
}
