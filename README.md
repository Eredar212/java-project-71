#### Hexlet tests and linter status:
[![Actions Status](https://github.com/Eredar212/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Eredar212/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/bd397a799fc87da945c5/maintainability)](https://codeclimate.com/github/Eredar212/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/bd397a799fc87da945c5/test_coverage)](https://codeclimate.com/github/Eredar212/java-project-71/test_coverage)

## Вычислитель отличий

Консольная команда позволяющая получить различия между двумя наборами данных формата ключ-данные.


Использование: gendiff [-hV] [-f=format] filepath1 filepath2   
- filepath1 - путь к первому файлу   
- filepath2 - путь ко второму файлу  
- -f, --format=format   Формат вывода [default: stylish]  
- -h, --help            Получения информации об использовании команды.  
- -V, --version         Вывести версию.

### Запуск из проекта
```sh
make --directory app/ gendiff format=<format> f1=<filepath1> f2=<filepath2>
```
`format=<format>` - необязательный параметр

### Демонстрация использования на примерах JSON и YAML форматах данных:
* формат вывода stylish  
    * [Asciinema JSON Simple](https://asciinema.org/a/611463)  
    * [Asciinema YAML Simple](https://asciinema.org/a/612369)
    * пример составных данных  
[Asciinema JSON YML Composite](https://asciinema.org/a/612450)
* форма вывода plain  
[Asciinema "plain" Format Option](https://asciinema.org/a/612459)
* формат вывода json  
[Asciinema "json" Format Option](https://asciinema.org/a/612461)
