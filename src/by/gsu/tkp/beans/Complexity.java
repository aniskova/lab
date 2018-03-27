package by.gsu.tkp.beans;

public enum Complexity {
    HARD,
    MEDIUM,
    EASY;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
