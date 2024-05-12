import java.util.Arrays;

public class Main {

    // Функция для проверки, можно ли раскрасить страну цветом color
    public static boolean isSafe(int[][] graph, int[] color, int v, int c) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && c == color[i]) {
                return false;
            }
        }
        return true;
    }

    // Рекурсивная функция для раскраски стран
    public static boolean graphColoringUtil(int[][] graph, int m, int[] color, int v) {
        // Если все страны раскрашены, возвращаем true
        if (v == graph.length) {
            return true;
        }

        // Пытаемся раскрасить страну v цветом от 1 до m
        for (int c = 1; c <= m; c++) {
            if (isSafe(graph, color, v, c)) {
                color[v] = c;

                // Рекурсивно раскрашиваем следующую страну
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true;
                }

                // Если раскраска следующей страны не удалась, возвращаем цвет
                color[v] = 0;
            }
        }

        // Если не удалось раскрасить страну, возвращаем false
        return false;
    }

    // Функция для раскраски всей карты
    public static void graphColoring(int[][] graph, int m) {
        int[] color = new int[graph.length]; // Инициализируем массив цветов

        // Пытаемся раскрасить карту
        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Раскраска невозможна");
        } else {
            // Выводим результат раскраски
            int maxColor = Arrays.stream(color).max().getAsInt();
            System.out.println("Минимальное количество цветов: " + maxColor);
            for (int i = 0; i < color.length; i++) {
                System.out.println("Страна " + (i + 1) + " имеет цвет " + color[i]);
            }
        }
    }

    public static void main(String[] args) {
        // Матрица смежности стран
        int[][] graph = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 1, 0},
            {1, 1, 1, 0, 1},
            {0, 1, 0, 1, 0}
        };

        int m = 3; // Максимальное количество цветов

        graphColoring(graph, m);
    }
}