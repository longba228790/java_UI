package com.example.predictor;

import java.util.ArrayList;
import java.util.List;

public class ReversePredictor {
    /**
     * Find experimental parameter combinations whose predicted conductivity
     * matches the target within the specified tolerance.
     *
     * @param targetY   Target conductivity (μS/cm)
     * @param tolerance Allowed absolute error tolerance
     * @return List of formatted strings describing matching parameter sets
     */
    public static List<String> findMatchingConditions(double targetY, double tolerance) {
        List<String> matches = new ArrayList<>();

        for (double x1 = 1; x1 <= 10; x1 += 1) {
            for (double x2 = 12; x2 <= 36; x2 += 1) {
                for (double x3 = 12; x3 <= 20; x3 += 1) {
                    for (double x4 = 0.5; x4 <= 3.0; x4 += 0.5) {
                        for (double x5 = 1.0; x5 <= 10.0; x5 += 1.0) {
                            double predictedY = Calculator.predictConductivity(x1, x2, x3, x4, x5);
                            if (Math.abs(predictedY - targetY) <= tolerance) {
                                String result = String.format(
                                        "Reaction Time: %.1f h | Air Flow: %.1f L/h | Current: %.1f mA\n" +
                                                "Feed Ratio: %.1f | NaCl Conc.: %.1f%% | Conductivity: %.3f μS/cm",
                                        x1, x2, x3, x4, x5, predictedY);
                                matches.add(result);
                            }
                        }
                    }
                }
            }
        }

        return matches;
    }
}
