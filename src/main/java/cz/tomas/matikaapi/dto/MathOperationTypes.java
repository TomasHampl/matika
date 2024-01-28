package cz.tomas.matikaapi.dto;

import lombok.Getter;

@Getter
public enum MathOperationTypes {
    ADDITION("ADDITION"),
    SUBTRACTION("SUBTRACTION"),
    MULTIPLICATION("MULTIPLICATION"),
    DIVISION("DIVISION");

    private final String operation;
    MathOperationTypes(String operation) {
        this.operation = operation;
    }
}
