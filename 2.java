import java.util.Stack;

public class Main {

    // Функция для вычисления логического выражения
    public static boolean evaluateExpression(String expression, int[] index) {
        char currentChar = expression.charAt(index[0]); // Получаем текущий символ выражения
        boolean result = false; // Инициализируем результат вычисления

        // Обработка случаев, когда текущий символ - 't' или 'f'
        if (currentChar == 't') {
            result = true;
            index[0]++; // Перемещаем индекс на следующий символ
        } else if (currentChar == 'f') {
            result = false;
            index[0]++; // Перемещаем индекс на следующий символ
        } 
        // Обработка случая, когда текущий символ - '!'
        else if (currentChar == '!') {
            index[0]++; // Пропускаем '!'
            result = !evaluateExpression(expression, index); // Инвертируем результат вычисления выражения
            index[0]++; // Пропускаем ')'
        } 
        // Обработка случаев, когда текущий символ - '&' или '|'
        else if (currentChar == '&' || currentChar == '|') {
            boolean isAnd = (currentChar == '&'); // Проверяем, является ли операция '&'
            index[0]++; // Пропускаем '&' или '|'
            index[0]++; // Пропускаем '('
            result = evaluateExpression(expression, index); // Вычисляем первый операнд
            while (expression.charAt(index[0]) != ')') { // Пока не дошли до закрывающей скобки
                index[0]++; // Пропускаем ','
                if (isAnd) {
                    result = result && evaluateExpression(expression, index); // Выполняем операцию '&'
                } else {
                    result = result || evaluateExpression(expression, index); // Выполняем операцию '|'
                }
            }
            index[0]++; // Пропускаем ')'
        }

        return result; // Возвращаем результат вычисления
    }

    // Функция для парсинга логического выражения
    public static boolean parseBoolExpr(String expression) {
        int[] index = new int[1]; // Инициализируем индекс
        return evaluateExpression(expression, index); // Возвращаем результат вычисления выражения
    }

    public static void main(String[] args) {
        String expression = "!(f)";
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + (parseBoolExpr(expression) ? "True" : "False"));

        expression = "|(f,t)";
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + (parseBoolExpr(expression) ? "True" : "False"));

        expression = "&(t,f)";
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + (parseBoolExpr(expression) ? "True" : "False"));
    }
}