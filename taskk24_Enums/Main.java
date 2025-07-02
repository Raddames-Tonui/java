package taskk24_Enums;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample array of Matter objects
        Matter[] matter = {
                new Matter("Water", 1.3, StateOfMatter.LIQUID),
                new Matter("Milk", 2.3, StateOfMatter.LIQUID),
                new Matter("Chair", 34.2, StateOfMatter.SOLID),
                new Matter("Ice", 0.9, StateOfMatter.SOLID),
                new Matter("Argon", 0.2, StateOfMatter.GAS)
        };

        // Separate lists for each state
        List<Matter> solids = new ArrayList<>();
        List<Matter> liquids = new ArrayList<>();
        List<Matter> gases = new ArrayList<>();

        // Grouping based on state of matter
        for (Matter m : matter) {
            switch (m.getState()) {
                case SOLID -> solids.add(m);
                case LIQUID -> liquids.add(m);
                case GAS -> gases.add(m);
            }
        }

        // Sorting by density (ascending)
        Comparator<Matter> densityComparator = Comparator.comparingDouble(Matter::getDensity);
        solids.sort(densityComparator);
        liquids.sort(densityComparator);
        gases.sort(densityComparator);

        // Display results
        System.out.println("Solids: " + solids);
        System.out.println("Liquids: " + liquids);
        System.out.println("Gases: " + gases);
    }
}
