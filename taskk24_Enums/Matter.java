package taskk24_Enums;

public class Matter {
    private String name;
    private double density;
    private StateOfMatter state;

    public Matter(String name, double density, StateOfMatter state) {
        this.name = name;
        this.density = density;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }

    public StateOfMatter getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f, %s)", name, density, state.type());
    }
}
