@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sqrSum = 0.0
    if (v.isEmpty()) return 0.0
    for (i in v.indices) {
        sqrSum += v[i] * v[i]
    }
    return sqrSum.pow(0.5)

}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0
    else list.sum() / list.size

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val average = list.sum() / list.size
    for (i in 0 until list.size) {
        val element = list[i]
        list[i] = element - average
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var sumAB = 0
    if (a.isEmpty() || b.isEmpty())
        return 0
    for (i in a.indices) {
        sumAB += a[i] * b[i]
    }
    return sumAB
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var pX = 0.0
    val xCopy = x.toDouble()
    if (p.isEmpty()) return 0
    pX += p[0]
    for (i in 1 until p.size) {
        pX += p[i] * xCopy.pow(i)
    }
    return pX.toInt()
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sumList = 0
    for (i in 1 until list.size) {
        sumList = list[i] + list[i - 1]
        list[i] = sumList
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var nCopy = n
    var i = 1
    while (nCopy > 1) {
        i++
        while (nCopy % i == 0) {
            list.add(i)
            nCopy /= i
        }
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var nCopy = n
    if (n == 0) {
        list.add(0)
        return list
    }
    while (nCopy > 0) {
        list.add(nCopy % base)
        nCopy /= base
    }
    return list.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    if (n == 0)
        return "0"
    val listBase = mutableListOf<String>()
    for (i in 'a'..'z') {
        listBase.add(i.toString())
    }
    val list = convert(n, base).toMutableList()
    val list1 = mutableListOf<String>()
    for (i in list.indices) {
        if (list[i] > 9)
            list1.add(listBase[list[i] - 10])
        else list1.add(list[i].toString())
    }
    return list1.joinToString(separator = "")
}


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var j = 1
    var result = 0
    val base1 = base.toDouble()
    for (i in digits.indices) {
        result += digits[i] * base1.pow(digits.size - j).toInt()
        j++
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0.0
    var a = 0.0
    for (i in str.length - 1 downTo 0) {
        result += str[i].digitToInt(base) * base.toDouble().pow(a)
        a += 1
    }
    return result.toInt()
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val listDigits = listOf("0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val listDozens = listOf("0", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val listHundreds = listOf("0", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val listThousands = listOf("0", "M", "MM", "MMM")
    return if (digitNumber(n) == 1)
        listDigits[n]
    else if (digitNumber(n) == 2)
        (listDozens[n / 10] + listDigits[n % 10]).replace("0", "")
    else if (digitNumber(n) == 3)
        (listHundreds[n / 100] + listDozens[(n / 10) % 10] + listDigits[n % 10]).replace("0", "")
    else
        (listThousands[n / 1000] + listHundreds[(n / 100) % 10] + listDozens[(n / 10) % 10] +
                listDigits[n % 10]).replace("0", "")
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val hundreds = listOf(
        "",
        "сто ",
        "двести ",
        "триста ",
        "четыреста ",
        "пятьсот ",
        "шестьсот ",
        "семьсот ",
        "восемьсот ",
        "девятьсот "
    )
    val ones = listOf(
        "",
        "один ",
        "два ",
        "три ",
        "четыре ",
        "пять ",
        "шесть ",
        "семь ",
        "восемь ",
        "девять "
    )
    val dozens = listOf(
        "",
        "десять ",
        "двадцать ",
        "тридцать ",
        "сорок ",
        "пятьдесят ",
        "шестьдесят ",
        "семьдесят ",
        "восемьдесят ",
        "девяносто "
    )
    val dozens2 = listOf(
        "десять ",
        "одиннадцать ",
        "двенадцать ",
        "тринадцать ",
        "четырнадцать ",
        "пятнадцать ",
        "шестнадцать ",
        "семнадцать ",
        "восемнадцать ",
        "девятнадцать "
    )
    val onesForThousands = listOf(
        "тысяч ",
        "одна тысяча ",
        "две тысячи ",
        "три тысячи ",
        "четыре тысячи ",
        "пять тысяч ",
        "шесть тысяч ",
        "семь тысяч ",
        "восемь тысяч ",
        "девять тысяч "
    )
    var n1 = n
    var k = 0
    while (n1 > 0) {
        k += 1
        n1 /= 10
    }
    when {
        k == 1 -> return ones[n].trim()
        k == 2 && n / 10 == 1 -> return (dozens2[n % 10]).trim()
        k == 2 -> return (dozens[n / 10] + " " + ones[n % 10]).trim()
        k == 3 && (n % 100) / 10 == 1 -> return (hundreds[n / 100] + dozens2[n % 10]).trim()
        k == 3 -> return (hundreds[n / 100] + dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 4 && n / 1000 == 1 && (n % 100) / 10 == 1 -> return ("тысяча " + hundreds[(n % 1000) / 100] +
                dozens2[n % 10]).trim()
        k == 4 && n / 1000 == 1 && (n % 100) / 10 == 1 -> return ("тысяча" +
                hundreds[(n % 1000) / 100] + dozens2[n % 10]).trim()
        k == 4 && n / 1000 == 1 -> return ("тысяча " + hundreds[(n % 1000) / 100] +
                dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 4 && (n % 100) / 10 == 1 -> return (onesForThousands[n / 1000] + hundreds[(n % 1000) / 100] +
                dozens2[n % 10]).trim()
        k == 4 -> return (onesForThousands[n / 1000] + hundreds[(n % 1000) / 100] +
                dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 5 && n / 10000 == 1 && (n % 100) / 10 == 1 -> return (dozens2[(n % 10000) / 1000] + "тысяч " +
                hundreds[(n % 1000) / 100] + dozens2[n % 10]).trim()
        k == 5 && n / 10000 == 1 -> return (dozens2[(n % 10000) / 1000] + "тысяч " +
                hundreds[(n % 1000) / 100] + dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 5 && (n % 100) / 10 == 1 -> return (dozens[n / 10000] + onesForThousands[(n % 10000) / 1000] +
                hundreds[(n % 1000) / 100] + dozens2[n % 10]).trim()
        k == 5 -> return (dozens[n / 10000] + onesForThousands[(n % 10000) / 1000] +
                hundreds[(n % 1000) / 100] + dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 6 && (n % 100000) / 10000 == 1 && (n % 100) / 10 == 1 -> return (hundreds[n / 100000] +
                dozens2[(n % 10000) / 1000] + "тысяч " + hundreds[(n % 1000) / 100] + dozens2[n % 10]).trim()
        k == 6 && (n % 100000) / 10000 == 1 -> return (hundreds[n / 100000] + dozens2[(n % 10000) / 1000] +
                "тысяч " + hundreds[(n % 1000) / 100] + dozens[(n % 100) / 10] + ones[n % 10]).trim()
        k == 6 && (n % 100) / 10 == 1 -> return (hundreds[n / 100000] + dozens[(n % 100000) / 10000] +
                onesForThousands[(n % 10000) / 1000] + hundreds[(n % 1000) / 100] +
                dozens2[n % 10]).trim()
        k == 6 -> return (hundreds[n / 100000] + dozens[(n % 100000) / 10000] +
                onesForThousands[(n % 10000) / 1000] + hundreds[(n % 1000) / 100] +
                dozens[(n % 100) / 10] + ones[n % 10]).trim()
    }
    return "0"
}
