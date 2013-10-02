import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Разработчик - Геннадий Матющенко
 * Использована IDE :IntelliJ IDEA:.
 */
public class Util {
    public static List<String> numVars = new ArrayList<String>();
    public static List<String> numVarsRepeat = new ArrayList<String>();

    /**
     * Перебирает все возможные комбинации простых
     * чисел внутри заданого числа (комбинаторный перебор числа без повторов)
     * ------------------------------
     * Алгоритм работы
     * ------------------------------
     * 00: Разбиваем входную строку с данными (которые нужно перебрать) на элементы,
     * представляем их в виде массива. Например 1234 - {1, 2, 3, 4}, abcd - {a, b, c, d}
     * 01: Задаем каждому элементу полученного массива идентификаторы
     * 02: От меньшего идентификатора к большему производим перебор элементов с помощью рекурсии
     * (слева на право). Например входной набор элементов abc -> {a, b, c}
     * Меньший идентификатор имеет элемент a, для него рекурсивно подбираем перестановки остальных элементов(b и c)
     * Получаем выражения abc и acb
     * Следующий по возрастанию идентификатор ссылается на элемент {b}, делаем с ним тоже, что и с элементом {a}
     * Получаем выражения bca и bac
     * И.т.д
     * 03: Проверяем, есть ли в полученном выражении все элементы из входного набора {a, b c}, если хоть один элемент
     * отсутствует - пропускаем выражение. Таким образом фильтруем повторы.
     *
     * Например - выражение cab содержит все нужные элементы {a, b c}
     * Выражение aab не содержит элемента {c} и поэтому не пригодно.
     * ------------------------------
     *
     * @param input - char массив с элементами для перебора (например 123 - {1, 2, 3})
     * @param arr - изначально пустой char массив с числом элементов как у input, его будет заполнять вариантами перестановок
     * @param index - int с индексом элемента, который сейчас перебирается. Изначально должен быть равен 0
     */
    public static void numVar(char[] input, char[] arr, int index){
        int inputLength = input.length;
        //Если index больше длины массива - выходим из рекурсии
        if(inputLength == index){
            String out = String.valueOf(arr);
            String inputString = String.valueOf(input);
            int matches = 0;
            //Проверяем, есть ли в сгенерированом выражении все элементы из массива input
            for(int i = 0; i < inputLength; i++){
                String ch = String.valueOf(inputString.charAt(i));
                if(out.contains(ch)) ++matches;
            }
            if(matches == inputLength) numVars.add(out);
            return;
        }
        //Поэлементно перебираем массив input и входим в рекурсию
        for(char c : input){
            arr[index] = c;
            numVar(input, arr, index + 1);
        }
    }

    /**
     * Перебирает все возможные комбинации простых
     * чисел внутри заданого числа (комбинаторный перебор числа с повторами)
     * ------------------------------
     * Алгоритм работы
     * ------------------------------
     * 00: Разбиваем входную строку с данными (которые нужно перебрать) на элементы,
     * представляем их в виде массива. Например 1234 - {1, 2, 3, 4}, abcd - {a, b, c, d}
     * 01: Задаем каждому элементу полученного массива идентификаторы
     * 02: От меньшего идентификатора к большему производим перебор элементов с помощью рекурсии
     * (слева на право). Например входной набор элементов abc -> {a, b, c}
     * Меньший идентификатор имеет элемент a, для него рекурсивно подбираем перестановки остальных элементов(b и c)
     * Получаем выражения abc и acb
     * Следующий по возрастанию идентификатор ссылается на элемент "b", делаем с ним тоже, что и с элементом "a"
     * Получаем выражения bca и bac
     * И.т.д
     * ------------------------------
     *
     * @param input - char массив с элементами для перебора (например 123 - {1, 2, 3})
     * @param arr - изначально пустой char массив с числом элементов как у input, его будет заполнять вариантами перестановок
     * @param index - int с индексом элемента, который сейчас перебирается. Изначально должен быть равен 0
     */
    public static void numVarRepeat(char[] input, char[] arr, int index){
        //Если index больше длины массива - выходим из рекурсии
        if(input.length == index){
            numVarsRepeat.add(String.valueOf(arr));
            return;
        }
        //Поэлементно перебираем массив input и входим в рекурсию
        for(char c : input){
            arr[index] = c;
            numVarRepeat(input, arr, index + 1);
        }
    }

    /**
     * Находит факториал натурального числа
     * Может рассчитать факториал любого натурального числа, расчет упирается в мощности машины
     * @param num - long число, факториал которого нужно вывести
     * @return BigInteger - результат
     */
    public static BigInteger getFactorial(long num) {
        if(num < 1) return BigInteger.ONE;
        BigInteger factorial = BigInteger.valueOf(num);
        for(long i = num - 1; i > 1; --i) factorial = factorial.multiply(BigInteger.valueOf(i));
        return factorial;
    }

    /**
     * Нахождение большего числа из ряда существующих
     * @param list - ArrayList коллекция данных с рядом long чисел
     * @return long - максимальное число
     */
    public static long getMaxNum(ArrayList<Long> list) {
        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    @Deprecated
    public static long legacyGetFactorial(int num) {
        if(num < 1) return 1;
        long factorial = num;
        for(int i = num - 1; i > 1; --i) factorial *= i;
        return factorial;
    }
}
