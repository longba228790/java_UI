package com.example.predictor;

public class Calculator {
    public static double predictConductivity(double x1, double x2, double x3, double x4, double x5) {
        return -269.8045
                + 1.3576 * x1 - 6.3796 * x2 + 59.6472 * x3 + 19.9396 * x4 - 11.6398 * x5
                - 0.1261 * Math.pow(x1, 2) + 0.3166 * Math.pow(x2, 2) - 3.7315 * Math.pow(x3, 2)
                - 7.9371 * Math.pow(x4, 2) + 4.0888 * Math.pow(x5, 2)
                - 0.0117 * Math.pow(x1, 3) - 0.0046 * Math.pow(x2, 3)
                + 0.0758 * Math.pow(x3, 3) + 0.9933 * Math.pow(x4, 3) - 0.2696 * Math.pow(x5, 3);
    }
}
