package org.games.battleShip;

import java.util.*;

// Player Class
class Player {
	Map<String, Ship> ships;  // Map of ships in the player's fleet
	String name;  // Player name
	BattleField battleField;  // BattleField object
	Set<Coordinates> firedCoordinates;  // Set of coordinates fired upon
	public Player(String name, BattleField battleField) {
		this.ships = new HashMap<>();
		this.name = name;
		this.battleField = battleField;
		this.firedCoordinates = new HashSet<>();
	}

	// Add ship to the player's fleet
	void addShip(String id, int x, int y, int size) {
		Ship ship = new Ship(id);
		for(int i = x; i > x - size; i--) {
			for(int j = y; j < y + size; j++) {
				ship.addCoords(i, j);  // Add coordinates to the ship
				battleField.battleField[i][j] = "S";  // Mark the ship on the field
			}
		}
		ships.put(id, ship);  // Add ship to the player's fleet
	}

	public boolean hasShips() {
		return !ships.isEmpty();  // Returns true if player has ships left
	}

	public void removeShip(String id) {
		ships.remove(id);  // Remove ship from the player's fleet
	}

	public boolean fire(int xB, int yB) {
		// check if the coordinates are valid
		if (xB < 0 || xB >= battleField.battleField.length || yB < 0 || yB >= battleField.battleField[0].length) {
			System.out.println("Invalid coordinates");
			return false;
		}

		// check if the coordinates have already been fired upon
		if (firedCoordinates.contains(new Coordinates(xB, yB))) {
			System.out.println("Coordinates already fired upon");
			return false;
		}

		firedCoordinates.add(new Coordinates(xB, yB));  // Add coordinates to the fired set

		// check if the coordinates hit a ship
		for (Ship ship : ships.values()) {
			if (ship.coordinates.contains(new Coordinates(xB, yB))) {
				battleField.battleField[xB][yB] = "X";  // Mark the hit on the field
				return true;
			}
		}

		battleField.battleField[xB][yB] = "O";  // Mark the miss on the field
		return false;
	}
}

// Ship Class
class Ship {
	String id;  // Ship identifier
	Set<Coordinates> coordinates;  // Coordinates occupied by the ship

	public Ship(String id) {
		this.id = id;
		this.coordinates = new HashSet<>();
	}

	// Add coordinates to the ship's position
	public void addCoords(int x, int y) {
		Coordinates coord = new Coordinates(x, y);
		coordinates.add(coord);
	}
}

// Coordinates Class to represent positions on the field
class Coordinates {
	int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Coordinates that)) return false;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

// Field Class
class BattleField {
	String[][] battleField;  // NxN field grid
	Map<String, Ship> ships = new HashMap<>();  // Map of ships on the field
	BattleField(int n) {
		battleField = new String[n + 1][n + 1];
		initializeBattleField(n + 1);
	}

	// Initialize the field with empty cells
	void initializeBattleField(int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				battleField[i][j] = "-";
			}
		}
	}

	// add the ship
	void addShip(String id, Ship ship) {
		ships.put(id, ship);
	}
}

// Strategy Interface for firing strategies
interface Strategy {
	int getCoordinate(int fieldSize);
}

// RandomFireStrategy Class to randomly choose firing coordinates
class RandomFireStrategy implements Strategy {
	Random random = new Random();

	@Override
	public int getCoordinate(int fieldSize) {
		return random.nextInt(fieldSize);  // Return random coordinate within field
	}
}

// BattleShipGame Class
class BattleShipGame {
	BattleField battleField;
	Player playerA;
	Player playerB;
	int n;  // Field size

	// Initialize the game with field size N
	public void initGame(int n) {
		this.n = n;
		battleField = new BattleField(n);
		playerA = new Player("A", battleField);
		playerB = new Player("B", battleField);
	}

	// Add ship to both PlayerA and PlayerB
	public void addShip(String id, int size, int xA, int yA, int xB, int yB) {
		if (size <= 0) {
			System.out.println("Size should be greater than 0");
			return;
		}
		// check if valid size
		if (xA < 0 || xA >= n || yA < 0 || yA >= n || xB < 0 || xB >= n || yB < 0 || yB >= n) {
			System.out.println("Invalid coordinates");
			return;
		}

		int xA1 = xA + size - 1;
		int yA1 = yA + size - 1;
		int xB1 = xB + size - 1;
		int yB1 = yB + size - 1;

		if (xA1 >= n + 1 || yA1 >= n + 1 || xB1 >= n + 1 || yB1 >= n + 1) {
			System.out.println("Ship out of bounds");
			return;
		}

		// Add ship to both players
		playerA.addShip(id, xA, yA, size);
		playerB.addShip(id, xB, yB, size);
		battleField.addShip(id, playerA.ships.get(id));
		battleField.addShip(id, playerB.ships.get(id));
	}

	public void startGame() {
		Strategy strategy = new RandomFireStrategy();
		boolean isPlayerATurn = true;
		while (playerA.hasShips() && playerB.hasShips()) {
			if(isPlayerATurn) {
				int xB = n/2 + strategy.getCoordinate(n/2);
				int yB = strategy.getCoordinate(n);
				if(playerA.fire(xB, yB)) {
					Ship firedShip = playerA.ships.values().stream().filter(ship -> ship.coordinates.contains(new Coordinates(xB, yB))).findFirst().get();
					System.out.println("Player B fires at " + xB + ", " + yB + " and hits Player A's ship" + " " + firedShip.id);
				} else {
					System.out.println("Player B fires at " + xB + ", " + yB + " and misses");
				}
				isPlayerATurn = false;
			} else {
				int xA = strategy.getCoordinate(n / 2);
				int yA = strategy.getCoordinate(n);
				if(playerB.fire(xA, yA)) {
					Ship firedShip = playerB.ships.values().stream().filter(ship -> ship.coordinates.contains(new Coordinates(xA, yA))).findFirst().get();
					System.out.println("Player A fires at " + xA + ", " + yA + " and hits Player B's ship" + " " + firedShip.id);
				} else {
					System.out.println("Player A fires at " + xA + ", " + yA + " and misses");
				}
				isPlayerATurn = true;
			}
		}

		if (!playerA.hasShips()) {
			System.out.println("Player B wins");
		} else {
			System.out.println("Player A wins");
		}


	}

	public void viewBattleField() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// print in the %5d format to make the grid look better
				System.out.printf("%5s", battleField.battleField[i][j]);
			}
			System.out.println();
		}
	}
}

// Main Class to run the game
class Main {
	public static void main(String[] args) {
		BattleShipGame game = new BattleShipGame();
		int n = 6;  // Field size
		game.initGame(n);
		game.addShip("SH1", 2, 1, 5, 4, 4);  // Adding a ship
		game.viewBattleField();
		game.startGame();
	}
}