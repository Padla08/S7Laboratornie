#include <iostream>
#include <string>
#include <stack>
using namespace std;

// Функция для вычисления логического выражения
bool evaluateExpression(const string& expression, int& index) {
    char currentChar = expression[index]; // Получаем текущий символ выражения
    bool result = false; // Инициализируем результат вычисления

    // Обработка случаев, когда текущий символ - 't' или 'f'
    if (currentChar == 't') {
        result = true;
        index++; // Перемещаем индекс на следующий символ
    } else if (currentChar == 'f') {
        result = false;
        index++; // Перемещаем индекс на следующий символ
    } 
    // Обработка случая, когда текущий символ - '!'
    else if (currentChar == '!') {
        index++; // Пропускаем '!'
        result = !evaluateExpression(expression, index); // Инвертируем результат вычисления выражения
        index++; // Пропускаем ')'
    } 
    // Обработка случаев, когда текущий символ - '&' или '|'
    else if (currentChar == '&' || currentChar == '|') {
        bool isAnd = (currentChar == '&'); // Проверяем, является ли операция '&'
        index++; // Пропускаем '&' или '|'
        index++; // Пропускаем '('
        result = evaluateExpression(expression, index); // Вычисляем первый операнд
        while (expression[index] != ')') { // Пока не дошли до закрывающей скобки
            index++; // Пропускаем ','
            if (isAnd) {
                result = result && evaluateExpression(expression, index); // Выполняем операцию '&'
            } else {
                result = result || evaluateExpression(expression, index); // Выполняем операцию '|'
            }
        }
        index++; // Пропускаем ')'
    }

    return result; // Возвращаем результат вычисления
}

// Функция для парсинга логического выражения
bool parseBoolExpr(const string& expression) {
    int index = 0; // Инициализируем индекс
    return evaluateExpression(expression, index); // Возвращаем результат вычисления выражения
}

int main() {
    string expression = "!(f)";
    cout << "Expression: " << expression << endl;
    cout << "Result: " << (parseBoolExpr(expression) ? "True" : "False") << endl;

    expression = "|(f,t)";
    cout << "Expression: " << expression << endl;
    cout << "Result: " << (parseBoolExpr(expression) ? "True" : "False") << endl;

    expression = "&(t,f)";
    cout << "Expression: " << expression << endl;
    cout << "Result: " << (parseBoolExpr(expression) ? "True" : "False") << endl;

    return 0;
}