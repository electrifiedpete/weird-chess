package com.a7m5.chess.chesspieces;

import com.a7m5.chess.Vector2;


public class Queen extends ChessPiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7808494174957885644L;

	public Queen(ChessOwner owner) {
		super(owner);
	}

	public static final Vector2[] specialMovementVectors = {};

	public static final Vector2[] movementDirectionVectors = {
		new Vector2(0, 1),
		new Vector2(0, -1),
		new Vector2(1, 0),
		new Vector2(-1, 0),

		// Diagonals

		new Vector2(1, 1),
		new Vector2(-1, -1),
		new Vector2(-1, 1),
		new Vector2(1, -1)
	};

	public static final Vector2[] attackDirectionVectors = movementDirectionVectors;

	public boolean tryMove(Vector2 newPosition) {
		for(Vector2 movementVector : movementDirectionVectors) {
			if(owner == ChessOwner.TOP) {
				movementVector = movementVector.multiplyY(-1);
			}
			for(int i = 1; i < board.getBoardWidth() - 1; i++) {
				Vector2 testVector = getPosition().add(movementVector.multiply(i));
				try {
					if(board.getChessPieceByVector(testVector) == null) {
						if(testVector.equals(newPosition)) {
							return true;
						}
					} else {
						break;
					}
				} catch(Exception e) {
					break;
				}
			}
			
		}
		return false;
	}
	
	public boolean tryAttack(ChessPiece targetChessPiece) {
		if(owner != targetChessPiece.owner) {
			for(Vector2 attackVector : attackDirectionVectors) {
				if(owner == ChessOwner.TOP) {
					attackVector = attackVector.multiplyY(-1);
				}
				for(int i = 1; i < board.getBoardWidth() - 1; i++) {
					Vector2 testVector = getPosition().add(attackVector.multiply(i));
					try {
						if(board.getChessPieceByVector(testVector) != null) {
							if(testVector.equals(targetChessPiece.getPosition())) {
								return true;
							} else {
								break;
							}
						}
					} catch(Exception e) {
						break;
					}
				}
				
			}
		}
		return false;
	}

}
