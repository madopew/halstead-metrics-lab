# halstead-metrics-lab
Лабораторная работа МСиСвИнфТ по метрике Холстеда, БГУИР 2020, гр. 951007.
Программа написана полностью на Java, метрика поддерживает синтаксис Kotlin.
## Использование
Метрика:
```Java
Metrics m = new Metrics(rawText); //Исходный код программы
```
или
```Java
Metrics m = new Metrics(new Parser(new Lexer(rawText).getTokens()).getArgTokens());
```

Остальные метрики:
```Java
m.getNu1(); //Возвращает словарь операторов
m.getNu2(); //Словарь операндов
m.getN1(); //Кол-во операторов
m.getN2(); //Кол-во операндов
m.getNu(); //Словарь программы
m.getN(); //Длинна программы
m.getV(); //Объем пограммы
```

Лексер (переводит исходный код в таблицу токенов):
```Java
Lexer l = new Lexer(rawText);
l.getTokens();
```

Парсер (переводит таблицу токенов в таблицу аргументов):
```Java
Parser p = new Parser(rawText); //OR
Parser p = new Parser(new Lexer(rawText).getTokens());
p.getArgTokens();
```