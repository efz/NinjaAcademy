package com.efzgames.ninjaacademy;

import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {
	public static final int playerLives = 5;
	public static final int pointsPerTarget = 1;
	public static final int pointsPerGoldTarget = 10;
	public static final int pointsPerBamboo = 3;
	public static final int penaltyPerBambooDrop = 5;
	public static final int penaltyPerExpodeDynamite = 20;

	public static List<GamePhase> phases = new ArrayList<GamePhase>();

	static {
		GamePhase phase;

		// Phase 1
		phase = new GamePhase();
		phase.duration = 45;
		phase.goldTargetProbablity = 0.1;
		phase.targetAppearanceIntervals = new float[3];
		phase.targetAppearanceIntervals[0] = 5;
		phase.targetAppearanceIntervals[1] = 5;
		phase.targetAppearanceIntervals[2] = 5;
		phase.targetAppearanceProbabilities = new double[3];
		phase.targetAppearanceProbabilities[0] = 0.75;
		phase.targetAppearanceProbabilities[1] = 0.75;
		phase.targetAppearanceProbabilities[2] = 0.75;
		phase.bambooAppearanceInterval = 2;
		phase.bambooAppearanceProbablity = 0.5;
		phase.dynamiteAppearanceInterval = 10;
		phase.dynamiteAppearanceProbablity = 0.25;
		phase.dynamiteAmountProbabilities = new double[1];
		phase.dynamiteAmountProbabilities[0] = 1;
		phases.add(phase);

		// Phase 2
		phase = new GamePhase();
		phase.duration = 75;
		phase.goldTargetProbablity = 0.1;
		phase.targetAppearanceIntervals = new float[3];
		phase.targetAppearanceIntervals[0] = 2;
		phase.targetAppearanceIntervals[1] = 3;
		phase.targetAppearanceIntervals[2] = 4;
		phase.targetAppearanceProbabilities = new double[3];
		phase.targetAppearanceProbabilities[0] = 0.35;
		phase.targetAppearanceProbabilities[1] = 0.65;
		phase.targetAppearanceProbabilities[2] = 0.35;
		phase.bambooAppearanceInterval = 1;
		phase.bambooAppearanceProbablity = 0.65;
		phase.dynamiteAppearanceInterval = 5;
		phase.dynamiteAppearanceProbablity = 0.5;
		phase.dynamiteAmountProbabilities = new double[3];
		phase.dynamiteAmountProbabilities[0] = 0.2;
		phase.dynamiteAmountProbabilities[1] = 0.45;
		phase.dynamiteAmountProbabilities[2] = 0.35;
		phases.add(phase);

		// Phase 3
		phase = new GamePhase();
		phase.duration = -1;
		phase.goldTargetProbablity = 0.1;
		phase.targetAppearanceIntervals = new float[3];
		phase.targetAppearanceIntervals[0] = 2;
		phase.targetAppearanceIntervals[1] = 3;
		phase.targetAppearanceIntervals[2] = 4;
		phase.targetAppearanceProbabilities = new double[3];
		phase.targetAppearanceProbabilities[0] = 0.5;
		phase.targetAppearanceProbabilities[1] = 0.5;
		phase.targetAppearanceProbabilities[2] = 0.5;
		phase.bambooAppearanceInterval = 1;
		phase.bambooAppearanceProbablity = 0.75;
		phase.dynamiteAppearanceInterval = 5;
		phase.dynamiteAppearanceProbablity = 0.5;
		phase.dynamiteAmountProbabilities = new double[3];
		phase.dynamiteAmountProbabilities[0] = 0.25;
		phase.dynamiteAmountProbabilities[1] = 0.5;
		phase.dynamiteAmountProbabilities[2] = 0.25;
		phases.add(phase);

	}
}
