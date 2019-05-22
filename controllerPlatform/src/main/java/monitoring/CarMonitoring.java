package monitoring;

import java.util.Random;

import simulator.CheckResult;

public class CarMonitoring {
	long allowedParkTime = 10000;
	long penaltyinterval = 15000;
	long towTime = 20000;
	CheckResult checkResult = new CheckResult();
	Random random = new Random();

	public void checkCar(int ImageId, String latitude, String longitude, boolean carExists)
			throws InterruptedException {
		if (true) {
			checkResult.loadJSON(ImageId, latitude, longitude);
			checkResult.carDetected(ImageId);
			Thread.sleep(allowedParkTime);
			if (true) {
				checkResult.warningMessage();
				Thread.sleep(penaltyinterval);
				if (random.nextBoolean()) {
					checkResult.signPenalty(ImageId);
					Thread.sleep(towTime);
					if (random.nextBoolean()) {
						checkResult.chargeTow(ImageId);
						Thread.sleep(8000);
						checkResult.left(ImageId);
						
					} else {
						checkResult.left(ImageId);
					}
				} else {
					checkResult.left(ImageId);
				}

			} else {
				checkResult.left(ImageId);
			}

		} else {
			checkResult.left(ImageId);
		}
	}

}
