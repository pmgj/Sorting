package model;

public record Command(int action, int index, int value) {
    
    public static final int READ = 0;
    public static final int WRITE = 1;
    
    @Override
    public String toString() {
        return String.format("(%s, %d, %d)", this.action() == 0 ? "Read" : "Write", this.index(), this.value());
    }
}
