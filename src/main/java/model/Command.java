package model;

public class Command {
    
    public static final int READ = 0;
    public static final int WRITE = 1;
    
    private final int action;
    private final int index;
    private final int value;

    public Command(int action, int index, int value) {
        this.action = action;
        this.index = index;
        this.value = value;
    }

    public int getAction() {
        return action;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d, %d)", this.getAction() == 0 ? "Read" : "Write", this.getIndex(), this.getValue());
    }
}
