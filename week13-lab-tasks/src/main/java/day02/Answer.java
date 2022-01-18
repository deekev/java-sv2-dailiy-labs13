package day02;

public enum Answer {

    RIGHT_ANSWER(1), PASS(0), WRONG_ANSWER(-2);

    private int points;

    Answer(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}