# WEB приложение для извлечения ссылок с web-страниц. (Использовал фрэймворк PrimeFaces)

## Автор

**Kirill Kupriyanov**

## Задание

* требуется создать WEB приложение по подсчету количества ссылок на WEB-странице.

## Компиляция

* использовать уже скомпилированный архив ./jsf-primefaces-example/libs/jsf-primefaces-example.war перейдя к пункту Запуск

* либо:

* установить [Gradle](https://gradle.org/install/)

* в системной переменной path должен быть прописан путь к файлу ./bin/gradle.bat

* в папке проекта (jsf-primefaces-example) выполнить команду:
```
 gradle build
```

## Запуск

* должен быть установлен и запущен [Tomcat server](https://tomcat.apache.org/)

* скопировать (вариант без компиляции) ./jsf-primefaces-example/libs/jsf-primefaces-example.war (вариант с компиляцией) ./jsf-primefaces-example/build/libs/jsf-primefaces-example.war в папку /webapps Tomcat сервера

* ввести в адресной строке браузера http://127.0.0.1:8080/jsf-primefaces-example

