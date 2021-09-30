package katas.two;

public class Game {
    private int rolls[] = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }

        }
        return score;
    }

    //is 10 (max for frame)
    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    //idx + 1 == 10 (max for two frames in order)
    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] +
                rolls[frameIndex + 1] == 10;
    }

    //idx + 1
    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }

    //increment by score of idx+2
    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    //increment by score of idx+1 & idx+2
    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }
}
