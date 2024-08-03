# Car Shop Management Application

## Описание
Это приложение предназначено для управления коворкинг-пространством

## Запуск
Необходимо запустить класс `Main.Java`

## Тестирование

## Взаимодействие с программой
Интерфейс построен на вводе пользователем команд, предлагаемых для ввода программой в текущий момент времени
1. Происходит регистрация/авторизация
2. Отображение начального набора команд, в зависимости от роли пользователя
3. Если роль администратор - CRUD автомобилей/заявок, создание/обновление данных о пользователях
4. Если роль клиент - показ доступных для заказа автомобилей, показ заказов пользователя, формирование новых заказов

# Краткое описание классов
- Request - заказы на покупку, заявки на обслуживание
- User - пользователи (Клиенты, Менеджеры, Администраторы)
- Car - автомобили салона

+ CarShopService - управление коллекцией автомобилей 
+ RequestService - управление коллекцией заказов
+ UserService - управление коллекцией пользователей

* ConsoleUI - текстовое представление наборов команд
* UserConsole - управление вводом пользователя