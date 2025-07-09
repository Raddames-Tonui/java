package org.example;

/**
 * Enum representing the state of matter.
 * Each constant overrides the `type()` method.
 */
public enum StateOfMatter {
    SOLID {
        @Override
        public String type() {
            return "Solid";
        }
    },
    LIQUID {
        @Override
        public String type() {
            return "Liquid";  // Fixed typo: previously returned "SOLID"
        }
    },
    GAS {
        @Override
        public String type() {
            return "Gas";
        }
    };

    // Abstract method to be implemented by each constant
    public abstract String type();
}
