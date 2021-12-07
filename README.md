<h1 align="center">Решения домашних заданий к курсу<br>«Введение в программирование»</h1>

> Тесты и модификации к домашним заданиям: [kgeorgiy.info/prog-intro-2021](https://www.kgeorgiy.info/git/geo/prog-intro-2021/) или зеркало [kgeorgiy/prog-intro-2021](https://github.com/kgeorgiy/prog-intro-2021)

### Оглавление:
- [Домашнее задание 13. Обработка ошибок](#домашнее-задание-13-обработка-ошибок)
- [Домашнее задание 12. Разбор выражений](#домашнее-задание-12-разбор-выражений)
- [Домашнее задание 11. Игра m,n,k](#домашнее-задание-11-выражения)
- [Домашнее задание 10. Игра m,n,k](#домашнее-задание-10-игра-mnk)
- [Домашнее задание 9. Markdown to HTML](#домашнее-задание-9-markdown-to-html)
- [Домашнее задание 8. Чемпионат](#домашнее-задание-8-чемпионат)
- [Домашнее задание 7. Разметка](#домашнее-задание-7-разметка)
- [Домашнее задание 6. Статистика слов++](#домашнее-задание-6-статистика-слов)
- [Домашнее задание 5. Свой сканнер](#домашнее-задание-5-свой-сканер)
- [Домашнее задание 4. Подсчет слов](#домашнее-задание-4-статистика-слов)
- [Домашнее задание 3. Реверс](#домашнее-задание-3-реверс)
- [Домашнее задание 2. Сумма чисел](#домашнее-задание-2-сумма-чисел)
- [Домашнее задание 1. Запусти меня!](#домашнее-задание-1-запусти-меня)

----------------------------------------------------------------------------------------------------

## Домашнее задание 13. Обработка ошибок

1. Добавьте в программу вычисляющую выражения обработку ошибок, в том числе:
    - ошибки разбора выражений;
    - ошибки вычисления выражений.
2. Для выражения `1000000*x*x*x*x*x/(x-1)` вывод программы должен иметь следующий вид:

    | **x** | **f**     |
    |-------|-----------|
    | 0     | 0         |
    | 2     | 32000000  |
    | 3     | 121500000 |
    | 4     | 341333333 |
    | 5     | overflow  |
    | 6     | overflow  |
    | 7     | overflow  |
    | 8     | overflow  |
    | 9     | overflow  |
    | 10    | overflow  |

    Результат `division by zero` (`overflow`) означает, что в процессе вычисления произошло деление на ноль (переполнение).
3. При выполнении задания следует обратить внимание на дизайн и обработку исключений.
4. Человеко-читаемые сообщения об ошибках должны выводится на консоль.
5. Программа не должна «вылетать» с исключениями (как стандартными, так и добавленными). 


## Домашнее задание 12. Разбор выражений

1. Доработайте предыдущее домашнее задание, так что бы выражение строилось по записи вида
    ```
    x * (x - 2)*x + 1
    ```
2. В записи выражения могут встречаться:
    - бинарные операции: умножение \*, деление /, сложение \+ и вычитание \-;
    - унарный минус \-;
    - переменные `x`, `y` и `z`;
    - целочисленные константы в десятичной системе счисления, помещающиеся в 32-битный знаковый целочисленный тип;
    - круглые скобки для явного обозначения приоритета операций;
    - произвольное число пробельных символов в любом месте, не влияющем на однозначност понимания формулы (например, между операцией и переменной, но не внутри констант). 
3. Приоритет операций, начиная с наивысшего
    1. унарный минус;
    2. умножение и деление;
    3. сложение и вычитание.
4. Разбор выражений рекомендуется производить [методом рекурсивного спуска](https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D0%B2%D0%BD%D0%BE%D0%B3%D0%BE_%D1%81%D0%BF%D1%83%D1%81%D0%BA%D0%B0).
    - Алгоритм должен работать за линейное время.
    - Лексический анализ (токенизация) не требуется.


## Домашнее задание 11. Выражения

1. Разработайте классы `Const`, `Variable`, `Add`, `Subtract`, `Multiply`, `Divide` для вычисления выражений с одной переменной в типе `int` (интерфейс `Expression`).
2. Классы должны позволять составлять выражения вида

    ```java
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).evaluate(5)
    ```

    При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу `evaluate`. Таким образом, результатом вычисления приведенного примера должно стать число 7.
3. Метод `toString` должен выдавать запись выражения в полноскобочной форме. Например

    ```java
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toString()
    ```

    должен выдавать `((2 * x) - 3)`.
4. *Сложный вариант*. Метод `toMiniString` (интерфейс `ToMiniString`) должен выдавать выражение с минимальным числом скобок. Например

    ```java
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toMiniString()
    ```

    должен выдавать `2 * x - 3`.
5. Реализуйте метод `equals`, проверяющий, что два выражения совпадают. Например,

    ```java
    new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Const(2), new Variable("x")))
    ```

    должно выдавать `true`, а

    ```java
    new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Variable("x"), new Const(2)))
    ```

    должно выдавать `false`.
6. Для тестирования программы должен быть создан класс `Main`, который вычисляет значение выражения `x^2 - 2x + 1`, для `x`, заданного в командной строке.
7. При выполнении задания следует обратить внимание на:
    - Выделение общего интерфейса создаваемых классов.
    - Выделение абстрактного базового класса для бинарных операций. 

## Домашнее задание 10. Игра m,n,k

В этом домашнем задании вы можете пользоваться кодом, написанным на лекции. Он есть на сайте курса и в репозитории [prog-intro-2021-solutions](https://www.kgeorgiy.info/git/geo/prog-intro-2021-solutions).

1. Реализуйте [игру m,n,k](https://en.wikipedia.org/wiki/M,n,k-game) (*k* в ряд на доске *m*×*n*).
2. Добавьте обработку ошибок ввода пользователя. В случае ошибочного хода пользователь должен иметь возможность сделать другой ход.
3. Добавьте обработку ошибок игроков. В случае ошибки игрок автоматически проигрывает.
4. *Простая версия*. Проверку выигрыша можно производить за *O(nmk)*.
5. *Сложная версия*.
    - Проверку выигрыша нужно производить за *O(k)*.
    - Предотвратите жульничество: у игрока не должно быть возможности достать `Board` из `Position`. 
6. *Бонусная версия*. Реализуйте `Winner` — игрок, который выигрывает всегда, когда это возможно (против любого соперника). 


## Домашнее задание 9. Markdown to HTML

1. Разработайте конвертер из [Markdown](https://ru.wikipedia.org/wiki/Markdown)-разметки в [HTML](https://ru.wikipedia.org/wiki/HTML).
2. Конвертер должен поддерживать следующие возможности:
    1. Абзацы текста разделяются пустыми строками.
    2. Элементы строчной разметки: выделение (\* или \_), сильное выделение (\*\* или \_\_), зачеркивание (\-\-), код (\`)
    3. Заголовки (\# \* уровень заголовка) 
3. Конвертер должен называться `md2html.Md2Html` и принимать два аргумента: название входного файла с Markdown-разметкой и название выходного файла c HTML-разметкой. Оба файла должны иметь кодировку UTF-8.
4. При выполнении этого ДЗ можно повторно использовать код ДЗ `markup`.
5. Пример
    - Входной файл

    ```markdown
    # Заголовок первого уровня

    ## Второго

    ### Третьего ## уровня

    #### Четвертого
    # Все еще четвертого

    Этот абзац текста,
    содержит две строки.

        # Может показаться, что это заголовок.
    Но нет, это абзац начинающийся с `#`.

    #И это не заголовок.

    ###### Заголовки могут быть многострочными
    (и с пропуском заголовков предыдущих уровней)

    Мы все любим *выделять* текст _разными_ способами.
    **Сильное выделение**, используется гораздо реже,
    но __почему бы и нет__?
    Немного --зачеркивания-- еще ни кому не вредило.
    Код представляется элементом `code`.

    Обратите внимание, как экранируются специальные
    HTML-символы, такие как `<`, `>` и `&`.

    Знаете ли вы, что в Markdown, одиночные * и _
    не означают выделение?
    Они так же могут быть заэкранированы
    при помощи обратного слэша: \*.



    Лишние пустые строки должны игнорироваться.

    Любите ли вы *вложеные __выделения__* так,
    как __--люблю--__ их я?
    ```


    - Выходной файл

    ```html
    <h1>Заголовок первого уровня</h1>
    <h2>Второго</h2>
    <h3>Третьего ## уровня</h3>
    <h4>Четвертого
    # Все еще четвертого</h4>
    <p>Этот абзац текста,
    содержит две строки.</p>
    <p>    # Может показаться, что это заголовок.
    Но нет, это абзац начинающийся с <code>#</code>.</p>
    <p>#И это не заголовок.</p>
    <h6>Заголовки могут быть многострочными
    (и с пропуском заголовков предыдущих уровней)</h6>
    <p>Мы все любим <em>выделять</em> текст <em>разными</em> способами.
    <strong>Сильное выделение</strong>, используется гораздо реже,
    но <strong>почему бы и нет</strong>?
    Немного <s>зачеркивания</s> еще ни кому не вредило.
    Код представляется элементом <code>code</code>.</p>
    <p>Обратите внимание, как экранируются специальные
    HTML-символы, такие как <code>&lt;</code>, <code>&gt;</code> и <code>&amp;</code>.</p>
    <p>Знаете ли вы, что в Markdown, одиночные * и _
    не означают выделение?
    Они так же могут быть заэкранированы
    при помощи обратного слэша: *.</p>
    <p>Лишние пустые строки должны игнорироваться.</p>
    <p>Любите ли вы <em>вложеные <strong>выделения</strong></em> так,
    как <strong><s>люблю</s></strong> их я?</p>
    ```


    - Реальная разметка  
    <h1>Заголовок первого уровня</h1>
    <h2>Второго</h2>
    <h3>Третьего ## уровня</h3>
    <h4>Четвертого
    # Все еще четвертого</h4>
    <p>Этот абзац текста,
    содержит две строки.</p>
    <p>    # Может показаться, что это заголовок.
    Но нет, это абзац начинающийся с <code>#</code>.</p>
    <p>#И это не заголовок.</p>
    <h6>Заголовки могут быть многострочными
    (и с пропуском заголовков предыдущих уровней)</h6>
    <p>Мы все любим <em>выделять</em> текст <em>разными</em> способами.
    <strong>Сильное выделение</strong>, используется гораздо реже,
    но <strong>почему бы и нет</strong>?
    Немного <s>зачеркивания</s> еще ни кому не вредило.
    Код представляется элементом <code>code</code>.</p>
    <p>Обратите внимание, как экранируются специальные
    HTML-символы, такие как <code>&lt;</code>, <code>&gt;</code> и <code>&amp;</code>.</p>
    <p>Знаете ли вы, что в Markdown, одиночные * и _
    не означают выделение?
    Они так же могут быть заэкранированы
    при помощи обратного слэша: *.</p>
    <p>Лишние пустые строки должны игнорироваться.</p>
    <p>Любите ли вы <em>вложеные <strong>выделения</strong></em> так,
    как <strong><s>люблю</s></strong> их я?</p>


## Домашнее задание 8. Чемпионат

1. Решите как можно больше задач Чемпионата северо-запада России по программированию 2019.
2. Материалы соревнования:
    - [PCMS](https://pcms.itmo.ru/): Java. North-Western Russia Regional Contest - 2019
    - [Условия задач](https://nerc.itmo.ru/archive/2019/northern/nwrrc-2019-statements.pdf)
    - [Разбор задач](https://nerc.itmo.ru/archive/2019/northern/nwrrc-2019-tutorials.pdf)
3. Задачи для решения

    | **Задача**               | **Тема**  | **Сложность** |
    |--------------------------|-----------|---------------|
    | A. Accurate Movement     | Формула   | 5             |
    | B. Bad Treap             | Циклы     | 10            |
    | C. Cross-Stitch          | Графы     | 40            |
    | D. Double Palindrome     | Массивы   | 40            |
    | E. Equidistant           | Деревья   | 30            |
    | H. High Load Database    | Массивы   | 20            |
    | I. Ideal Pyramid         | Циклы     | 15            |
    | J. Just the Last Digit   | Матрицы   | 20            |
    | K. King’s Children       | Массивы   | 40            |
    | M. Managing Difficulties | Коллекции | 10            |

4. Рекомендуемое время выполнения задания: 3 часа


## Домашнее задание 7. Разметка

1. Разработайте набор классов для текстовой разметки.
2. Класс `Paragraph` может содержать произвольное число других элементов разметки и текстовых элементов.
3. Класс `Text` – текстовый элемент.
4. Классы разметки `Emphasis`, `Strong`, `Strikeout` – выделение, сильное выделение и зачеркивание. Элементы разметки могут содержать произвольное число других элементов разметки и текстовых элементов.
5. Все классы должны реализовывать метод <code>toMarkdown([StringBuilder](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html))</code>, которой должен генерировать [Markdown](https://ru.wikipedia.org/wiki/Markdown)-разметку по следующим правилам:
    1. текстовые элементы выводятся как есть;
    2. выделенный текст окружается символами '\*';
    3. сильно выделенный текст окружается символами '\_';
    4. зачеркнутый текст окружается символами '\~'.
6. Следующий код должен успешно компилироваться:

    ```java
    Paragraph paragraph = new Paragraph(List.of(
        new Strong(List.of(
            new Text("1"),
            new Strikeout(List.of(
                new Text("2"),
                new Emphasis(List.of(
                    new Text("3"),
                    new Text("4")
                )),
                new Text("5")
            )),
            new Text("6")
        ))
    ));
    ```

    Вызов `paragraph.toMarkdown(new StringBuilder())` должен заполнять переданный `StringBuilder` следующим содержимым:

    ```
        __1~2*34*5~6__
    ```

7. Разработанные классы должны находиться в пакете `markup`.


## Домашнее задание 6. Статистика слов++

1. Разработайте класс `Wspp`, который будет подсчитывать статистику встречаемости слов во входном файле.
2. Словом называется непрерывная последовательность букв, апострофов и тире (Unicode category Punctuation, Dash). Для подсчета статистики, слова приводятся к нижнему регистру.
3. Выходной файл должен содержать все различные слова, встречающиеся во входном файле, в порядке их появления. Для каждого слова должна быть выведена одна строка, содержащая слово, число его вхождений во входной файл и номера вхождений этого слова среди всех слов во входном файле.
4. Имена входного и выходного файла задаются в качестве аргументов командной строки. Кодировка файлов: UTF-8.
5. Программа должна работать за линейное от размера входного файла время.
6. Для реализации программы используйте Collections Framework.
7. *Сложный вариант*. Реализуйте и примените класс `IntList`, компактно хранящий список целых чисел.
8. Примеры работы программы:

    |                             Входной файл                              |                                                      Выходной файл                                                       |
    |-----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
    | To be, or not to be, that is the question:                            | to 2 1 5<br> be 2 2 6<br>or 1 3<br>not 1 4<br>that 1 7<br>is 1 8<br>the 1 9<br>question 1 10                             |
    | Monday's child is fair of face.<br>Tuesday's child is full of grace.  | monday's 1 1<br>child 2 2 8<br>is 2 3 9<br>fair 1 4<br>of 2 5 11<br>face 1 6<br>tuesday's 1 7<br>full 1 10<br>grace 1 12 |
    | Шалтай-Болтай<br>Сидел на стене.<br>Шалтай-Болтай<br>Свалился во сне. | шалтай-болтай 2 1 5<br>сидел 1 2<br>на 1 3<br>стене 1 4<br>свалился 1 6<br>во 1 7<br>сне 1 8                             |


## Домашнее задание 5. Свой сканер

1. Реализуйте свой аналог класса [Scanner](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Scanner.html) на основе [Reader](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Reader.html).
2. Примените разработанный `Scanner` для решения задания «Реверс».
3. Примените разработанный `Scanner` для решения задания «Статистика слов».
4. Нужно использовать блочное чтение. Код, управляющий чтением должен быть общим.
5. *Сложный вариант*. Код, выделяющий числа и слова должен быть общим.
6. Обратите внимание на:
    - Обработку ошибок.
    - На слова/числа, пересекающие границы блоков, особенно — больше одного раза.


## Домашнее задание 4. Статистика слов

1. Разработайте класс `WordStatInput`, подсчитывающий статистику встречаемости слов во входном файле.
2. Словом называется непрерывная последовательность букв, апострофов (') и дефисов (Unicode category [Punctuation, Dash](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#DASH_PUNCTUATION)). Для подсчета статистики слова приводятся к нижнему регистру.
3. Выходной файл должен содержать все различные слова, встречающиеся во входном файле, в порядке их появления. Для каждого слова должна быть выведена одна строка, содержащая слово и число его вхождений во входном файле.
4. Имена входного и выходного файла задаются в качестве аргументов командной строки. Кодировка файлов: UTF-8.
5. Примеры работы программы:

    |                             Входной файл                              |                                         Выходной файл                                         |
    |-----------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
    | To be, or not to be, that is the question:                            | to 2<br>be 2<br>or 1<br>not 1<br>that 1<br>is 1<br>the 1<br>question 1                        |
    | Monday's child is fair of face.<br>Tuesday's child is full of grace.  | monday's 1<br>child 2<br>is 2<br>fair 1<br>of 2<br>face 1<br>tuesday's 1<br>full 1<br>grace 1 |
    | Шалтай-Болтай<br>Сидел на стене.<br>Шалтай-Болтай<br>Свалился во сне. | шалтай-болтай 2<br>сидел 1<br>на 1<br>стене 1<br>свалился 1<br>во 1<br>сне 1                  |


## Домашнее задание 3. Реверс

1. Разработайте класс `Reverse`, читающий числа из [стандартного ввода](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html#in), и выводящий их на [стандартный вывод](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html#out) в обратном порядке.
2. В каждой строке входа содержится некоторое количество целых чисел (может быть 0). Числа разделены пробелами. Каждое число помещается в тип `int`.
3. Порядок строк в выходе должен быть обратным по сравнению с порядком строк во входе. Порядок чисел в каждой строке также должен быть обратным к порядку чисел во входе.
4. Вход содержит не более 10^6 чисел и строк.
5. Для чтения чисел используйте класс [Scanner](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Scanner.html).
6. Примеры работы программы:

    |                                 Ввод                                 |        Вывод         |
    |----------------------------------------------------------------------|----------------------|
    | 1&nbsp;2<br>3                                                        | 3<br>2&nbsp;1        |
    | 3<br>2&nbsp;1                                                        | 1&nbsp;2<br>3        |
    | 1<br><br>2&nbsp;-3                                                   | -3&nbsp;2<br><br>1   |
    | 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2<br>3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4 | 4&nbsp;3<br>2&nbsp;1 |


## Домашнее задание 2. Сумма чисел

1. Разработайте класс `Sum`, который при запуске из командной строки будет складывать переданные в качестве аргументов целые числа и выводить их сумму на консоль.
2. Примеры запуска программы:

    `java Sum 1 2 3`  
    Результат: 6

    `java Sum 1 2 -3`  
    Результат: 0

    `java Sum "1 2 3"`  
    Результат: 6

    `java Sum "1 2" " 3"`  
    Результат: 6

    `java Sum " "`  
    Результат: 0

3. Аргументы могут содержать:
    - цифры;
    - знаки + и -;
    - произвольные [пробельные символы](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#isWhitespace(char)).
4. При выполнении задания можно считать, что для представления входных данных и промежуточных результатов достаточен тип `int`.
5. Перед выполнением задания ознакомьтесь с документацией к классам [String](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html) и [Integer](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Integer.html).
6. Для отладочного вывода используйте [System.err](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html#err), тогда он будет игнорироваться проверяющей программой.


## Домашнее задание 1. Запусти меня!

1. Установите [JDK 11+](https://adoptopenjdk.net/?variant=openjdk16&jvmVariant=hotspot)
2. Скопируйте один из вариантов `HelloWorld`, рассмотренных на лекции.
3. Откомпилируйте `HelloWorld.java` и получите `HelloWorld.class`.
4. Запустите `HelloWorld` и проверьте его работоспособность.
5. Создайте скрипт, компилирующий и запускающий `HelloWorld` из командной строки.
