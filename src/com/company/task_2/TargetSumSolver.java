package com.company.task_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;

public class TargetSumSolver {
    private static List<String> solutions = new ArrayList<>();
    private static final double EPSILON = 0.001;

    public static void main(String[] args) {
        double[] numbers = {215.41, 151.44, 147.22, 937.13, 239.91, 491.45, 521.17, 768.99, 613.84};
        double target = 143.5;

        System.out.println("Berilgan sonlar: " + Arrays.toString(numbers));
        System.out.println("Maqsadli natija: " + target);
        System.out.println("\nIxtiyoriy yechimlar topilmoqda...");

        findTargetSum(numbers, target, 1, 1.0, "");

        if (solutions.isEmpty()) {
            System.out.println("\nUshbu kombinatsiyalar bilan natija topilmadi.");
        } else {
            System.out.println("\nTopilgan yechimlar:");
            for (String solution : solutions) {
                System.out.println(solution);
            }
        }
    }

    private static void findTargetSum(double[] numbers, double target, int index, double currentSum, String currentExpression) {
        if (Math.abs(currentSum - target) < EPSILON) {
            DecimalFormat df = new DecimalFormat("0.##");
            solutions.add(currentExpression + " = " + df.format(currentSum));
            return;
        }
        if (index == numbers.length) {
            return;
        }

        findTargetSum(numbers, target, index + 1, currentSum + numbers[index],
                currentExpression + (currentExpression.isEmpty() ? "" : " + ") + numbers[index]);

        findTargetSum(numbers, target, index + 1, currentSum - numbers[index],
                currentExpression + (currentExpression.isEmpty() ? "" : " - ") + numbers[index]);

        findTargetSum(numbers, target, index + 1, currentSum, currentExpression);
    }
}