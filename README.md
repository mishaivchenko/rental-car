﻿## Описание проекта:

Разработать WEB-приложение, которое поддерживает заданную функциональность.
 
* **Требования к реализации следующие:**
    1.	На основе сущностей предметной области создать классы которые им соответствуют
    2.	Классы и методы должны иметь названия, которые отражают их функциональность, и должны быть грамотно разнесены по пакетам
    3.	Оформление кода должно соответствовать Java Code Convention
    4.	Информацию о предметной области хранить в базе данных (в качестве СУБД рекомендуется использовать Maria DB)
    5.	Для доступа к данным использовать API JDBC с использованием пула соединений (не допускается использование ORM фреймворков)
    6.	Приложение должно поддерживать работу с кириллицей (быть многоязычным), в том числе при хранении информации в базе данных:
		•	должна быть возможность переключения языка интерфейса;
		•	должна быть поддержка ввода, вывода и хранения информации (в базе данных), записанной на разных языках (см. ниже);
		•	в качестве поддерживаемых языков выбрать минимум два: один на основе кириллицы, другой на основе латиницы.
    7.	Архитектура приложения должна соответствовать шаблону MVC (не допускается использование MVC фреймверков)
    8.	При реализации алгоритмов бизнес-логики использовать шаблоны
    9.	Используя сервлеты и JSP, реализовать функциональность, приведенную в постановке задачи
    10.	В качестве контейнера сервлетов использовать Apache Tomcat
    11.	На страницах JSP применять теги из библиотеки JSTL и разработанные собственные теги (минимум один custom tag library тег и минимум один tag file тег)
    12.	При разработке использовать сессии, фильтры, слушатели
    13.	Использовать журналирование событий с использованием библиотеки Apache Log4j или Slf4J
    14.	Самостоятельное расширение постановки задачи по функциональности приветствуется

* **Дополнительно, к требованиям изложенным выше, более чем желательно обеспечить выполнение следующих требований:**
    1.	Реализовать разграничение прав доступа пользователей системы к компонентам приложения
    2.	Реализовать защиту от повторной отправки данных на сервер при обновлении страницы
    3.	Все поля ввода должны быть с валидацией данных

## Прокат автомобилей 
* В системе существует перечень Автомобилей, для которого необходимо реализовать: 
	- выборку по марке; 
    - выборку по классу качества; 
    - сортировку по стоимости проката; 
    - сортировку по названию. 

* Клиент регистрируется в системе, выбирает автомобиль и делает заказ на аренду. Незарегистрированный клиент не может сделать заказ. 
* В данных заказа клиент указывает паспортные данные, опцию 'с водителем'/'без водителя', срок аренды. Система формирует счет, который клиент оплачивает. 
* Менеджер рассматривает заказ и может отклонить его, указав причину. Также менеджер регистрирует возврат автомобиля, в случае повреждения автомобиля выставляет через систему счет за ремонт. 
* **Администратор системы владеет следующими правами:** 
    - добавления, удаление автомобилей, редактирования информации об автомобилях; 
    - блокирования/разблокирования пользователей; 
    - регистрации менеджеров в системе.
    
   to build - mvn clean install; to run migrations db - mvn liquibase:update 