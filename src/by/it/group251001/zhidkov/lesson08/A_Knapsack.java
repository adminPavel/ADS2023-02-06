package by.it.group251001.zhidkov.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {
    int getMaxWeight(InputStream stream) {
        // Чтение входных данных
        Scanner scanner = new Scanner(stream);
        int w = scanner.nextInt(); // Вместимость рюкзака
        int n = scanner.nextInt(); // Количество вариантов золотых слитков
        int[] gold = new int[n]; // Веса слитков
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        int[][] table = new int[n + 1][w + 1];

        // Инициализация начальных значений таблицы
        for (int i = 0; i <= w; i++)
            table[0][i] = 0;
        for (int i = 0; i <= n; i++)
            table[i][0] = 0;

        // Заполнение таблицы методом динамического программирования
        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= w; s++) {
                if (s >= gold[k - 1])
                    table[k][s] = Math.max(table[k - 1][s], table[k][s - gold[k - 1]] + gold[k - 1]);
                else
                    table[k][s] = table[k - 1][s];
            }
        }

        return table[n][w];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
