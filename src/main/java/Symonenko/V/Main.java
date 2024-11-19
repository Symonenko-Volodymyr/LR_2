package Symonenko.V;

import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final int MAX_SIZE = 20;
    private static final int MIN_RANDOM = -50;
    private static final int MAX_RANDOM = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть кількість рядків матриці (до 20): ");
        int rows = getValidInput(scanner, 1, MAX_SIZE);

        System.out.println("Введіть кількість стовпців матриці (до 20): ");
        int cols = getValidInput(scanner, 1, MAX_SIZE);

        System.out.println("1 - вручну, 2 - рандомно");
        int choice = getValidInput(scanner, 1, 2);

        int[][] matrix = new int[rows][cols];
        if (choice == 1) {
            fillMatrixManually(matrix, scanner);
        } else {
            fillMatrixRandomly(matrix);
        }

        displayMatrix(matrix);

        System.out.println("MIN елемент: " + findMin(matrix));
        System.out.println("MAX елемент: " + findMax(matrix));
        System.out.println("Середнє арифметичне: " + calculateArithmeticMean(matrix));
        System.out.println("Середнє геометричне: " + calculateGeometricMean(matrix));
    }

    // Отримати коректне значення з клавіатури в межах [min, max]
    public static int getValidInput(Scanner scanner, int min, int max) {
        int value;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Будь ласка, введіть ціле число!");
                scanner.next();
            }
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println("Введіть число в межах від " + min + " до " + max + "!");
            }
        } while (value < min || value > max);
        return value;
    }

    // Заповнення матриці вручну
    public static void fillMatrixManually(int[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println("Введіть елемент [" + i + "][" + j + "]:");
                matrix[i][j] = getValidInput(scanner, MIN_RANDOM, MAX_RANDOM);
            }
        }
    }

    // Заповнення матриці випадковими числами
    public static void fillMatrixRandomly(int[][] matrix) {
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rand.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }
    }

    // Print
    public static void displayMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    // Min елементу матриці
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    // Max елементу матриці
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    // Обчислення середнього арифметичного
    public static double calculateArithmeticMean(int[][] matrix) {
        int sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
                count++;
            }
        }
        return (double) sum / count;
    }

    // Обчислення середнього геометричного
    public static double calculateGeometricMean(int[][] matrix) {
        double product = 1.0;
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value == 0) {
                    return 0;
                }
                product *= Math.abs(value);
                count++;
            }
        }
        return Math.pow(product, 1.0 / count);
    }
}