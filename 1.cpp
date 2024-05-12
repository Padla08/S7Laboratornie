#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Функция для проверки, можно ли раскрасить страну цветом color
bool isSafe(vector<vector<int>>& graph, vector<int>& color, int v, int c) {
    for (int i = 0; i < graph.size(); i++) {
        if (graph[v][i] && c == color[i]) {
            return false;
        }
    }
    return true;
}

// Рекурсивная функция для раскраски стран
bool graphColoringUtil(vector<vector<int>>& graph, int m, vector<int>& color, int v) {
    // Если все страны раскрашены, возвращаем true
    if (v == graph.size()) {
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
void graphColoring(vector<vector<int>>& graph, int m) {
    vector<int> color(graph.size(), 0); // Инициализируем массив цветов

    // Пытаемся раскрасить карту
    if (!graphColoringUtil(graph, m, color, 0)) {
        cout << "Раскраска невозможна" << endl;
    } else {
        // Выводим результат раскраски
        cout << "Минимальное количество цветов: " << *max_element(color.begin(), color.end()) << endl;
        for (int i = 0; i < color.size(); i++) {
            cout << "Страна " << i + 1 << " имеет цвет " << color[i] << endl;
        }
    }
}

int main() {
    // Матрица смежности стран
    vector<vector<int>> graph = {
        {0, 1, 0, 1, 0},
        {1, 0, 1, 1, 1},
        {0, 1, 0, 1, 0},
        {1, 1, 1, 0, 1},
        {0, 1, 0, 1, 0}
    };

    int m = 3; // Максимальное количество цветов

    graphColoring(graph, m);

    return 0;
}