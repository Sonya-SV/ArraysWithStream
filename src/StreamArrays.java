import java.security.cert.CollectionCertStoreParameters;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamArrays {
    public static void main(String[] args) {
        int number = 3;
        int shift = 4;
        final int[] arraySecond = {3, 0, 5, -2};
        final int[] arrayMain = {1, 0, -2, 3, 3, 7, 8, -1, 1, -3, 5, 7};

        System.out.println("Найти сумму элементов массива");
        System.out.println(IntStream.of(arrayMain).sum());

        System.out.println("Найти максимальный элемент, значение и индекс");
        IntStream.range(0, arrayMain.length)
                .reduce((a, b) -> arrayMain[a] < arrayMain[b] ? b : a)
                .ifPresent(x -> System.out.println(arrayMain[x] + "[" + x + "]"));

        System.out.println("Найти минимальный элемент, значение и индекс");
        IntStream.range(0, arrayMain.length)
                .reduce((a, b) -> arrayMain[a] > arrayMain[b] ? b : a)
                .ifPresent(x -> System.out.println(arrayMain[x] + "[" + x + "]"));

        System.out.println("Найти среднее значение элементов массива");
        System.out.println(IntStream.of(arrayMain).average().getAsDouble());

        System.out.println("Посчитать количество элементов равных заданному");
        System.out.println(IntStream.of(arrayMain).filter(x -> x == number).count());

        System.out.println("Посчитать количество элементов равных нулю");
        System.out.println(IntStream.of(arrayMain).filter(x -> x == 0).count());

        System.out.println("Посчитать количество элементов больше нуля");
        System.out.println(IntStream.of(arrayMain).filter(x -> x > 0).count());

        System.out.println("Помножить элементы массива на число " + number);
        IntStream.of(arrayMain).map(x -> x * number).forEach(x -> System.out.print(x + " "));

        System.out.println("\nПрибавить к элементам массива их индекс");
        IntStream.range(0, arrayMain.length)
                .map(a -> a + arrayMain[a])
                .forEach(i -> System.out.print((i + " ")));

        System.out.println("\nОбнулить четные по значению элементы массива");
        IntStream.of(arrayMain)
                .map(i -> (i % 2 == 0) ? 0 : i)
                .forEach(i -> System.out.print((i + " ")));

        System.out.println("\nОбнулить элементы  с нечетным индексом");
        IntStream.range(0, arrayMain.length)
                .map(i -> i % 2 == 0 ? 0 : i)
                .forEach(i -> System.out.print((i + " ")));

        System.out.println("\nНайти первый положительный элемент");
        IntStream.of(arrayMain)
                .filter(i -> i > 0)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("Найти последний отрицательный элемент");
        IntStream.of(arrayMain)
                .filter(i -> i < 0)
                .reduce((i, j) -> j)
                .ifPresent(System.out::println);

        System.out.println("Найти индексы вхождения элемента в массив");
        IntStream.range(0, arrayMain.length)
                .filter(i -> arrayMain[i] == number)
                .forEach(x -> System.out.print(x + " "));

        System.out.println("\nПроверить массив на упорядоченность элементов по возрастанию");
        System.out.println(IntStream.range(0, arrayMain.length - 1).allMatch(i -> arrayMain[i] <= arrayMain[i + 1]));

        System.out.println("Проверить массив на упорядоченность элементов по спаданию");
        System.out.println(IntStream.range(0, arrayMain.length - 1).allMatch(i -> arrayMain[i] >= arrayMain[i + 1]));

        System.out.println("Циклический сдвиг элементов массива на k- позиций вправо");
        IntStream.concat(
                IntStream.range(arrayMain.length - shift, arrayMain.length),
                IntStream.range(0, arrayMain.length - shift)
        )
                .map(e -> arrayMain[e])
                .forEach(x -> System.out.print(x + " "));

        System.out.println("\nВывести элементы, значения которых равны значениям других элементов");
        IntStream.of(arrayMain)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(x -> System.out.print(x.getKey() + " "));

        System.out.println("\nНайти количество элементов больших среднего значения");
        System.out.println(IntStream.of(arrayMain)
                .filter(i -> i > IntStream.of(arrayMain).average().orElse(0))
                .count());

        System.out.println("Вывести элементы, значения которых не равны значениям других элементов");
        IntStream.of(arrayMain)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .forEach(x -> System.out.print(x.getKey() + " "));

        System.out.println("\nВывести элементы одного массива, которые не равны элементам второго массива");
        IntStream.of(arrayMain)
                .filter(i -> IntStream.iterate(0, j -> j + 1)
                        .limit(arraySecond.length)
                        .allMatch(j -> i != arraySecond[j])
                )
                .forEach(x -> System.out.print(x + " "));


        System.out.println("\nПосчитать сколько элементов в массиве с таким-же значением, как и первый");
        System.out.println(IntStream.of(arrayMain)
                .filter(i -> i == IntStream.of(arrayMain).findFirst().getAsInt())
                .count());

        System.out.println("Из двух отсортированных массивов сделать третий отсортированный, не сортируя его");
        System.out.println("\nПоменять первый положительный элемент с последним положительным, второй с предпоследним и т.д.");

    }
}
