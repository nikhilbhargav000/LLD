package sl.service;

import sl.exception.SLGameWinnerFoundException;
import sl.gameplay.GamePlay;
import sl.gameplay.SLGamePlay;

public class SLGamePlayServiceImpl implements GamePlayService {

	@Override
	public void playGame(GamePlay gamePlay) {
		// Get game play singleton instance
		if (gamePlay == null) {
			gamePlay = SLGamePlay.getInstance();
		}
		
		while (true) {
			try {
				gamePlay.playTurn();
			} catch (SLGameWinnerFoundException e) {
				break;
			}
		}
	}

}
