package org.example.carshop.out;

/**
 * The {@code ConsoleUI} class provides static methods for printing various command menus to the console.
 * These methods are used to display different options to the user in a console-based Car Shop management application.
 */
public class ConsoleUI {

    /**
     * Prints the start commands menu.
     */
    public static void printStartCommands() {
        System.out.print("""
                Выберите команду:
                1 - Регистрация
                2 - Авторизация
                3 - Выход
                """);
    }

    /**
     * Prints the Car Shop Client commands menu.
     */
    public static void printCarShopClientCommands() {
        System.out.print("""
                Выберите команду:
                1 - Показать доступные для заказа автомобили
                2 - Показать заказы пользователя
                3 - Новый заказ
                4 - Выход из пользователя
                """);
    }

    /**
     * Prints the Car Shop Manager commands menu.
     */
    public static void printCarShopManagerCommands() {
        System.out.print("""
                Выберите команду:
                1 - Поиск автомобиля
                2 - Обработка заказов
                3 - Просмотр информации о клиентах и сотрудниках
                4 - Выход из пользователя
                """);
    }

    /**
     * Prints the Car Shop Admin commands menu.
     */
    public static void printCarShopAdminCommands() {
        System.out.print("""
                Выберите команду:
                1 - Управление автомобилями
                2 - Обработка заказов
                3 - Просмотр информации о клиентах и сотрудниках
                4 - Выход из пользователя
                """);
    }

    /**
     * Prints the cars management commands menu.
     */
    public static void printCarCommands() {
        System.out.print("""
                Выберите команду для управления автомобилями:
                1 - Добавить
                2 - Изменить данные
                3 - Удалить
                4 - Показать все
                5 - Вернуться назад
                """);
    }

    /**
     * Prints the users management commands menu.
     */
    public static void printUsersCommands() {
        System.out.print("""
                Выберите команду для управления и просмотра информации о клиентах и сотрудниках:
                1 - Просмотр списка зарегистрированных клиентов
                2 - Просмотр списка зарегистрированных сотрудников
                3 - Поиск по критериям
                4 - Добавить пользователя
                5 - Изменить данные пользователя
                6 - Вернуться назад
                """);
    }

    /**
     * Prints the car availability commands menu.
     */
    public static void printAvailabilityCommands() {
        System.out.print("""
                Произведен ли заказ автомобиля?
                1 - Да
                2 - Нет
                """);
    }

    /**
     * Prints the request commands menu.
     */
    public static void printRequestCommands() {
        System.out.print("""
                Выберите команду для обработки заказов:
                1 - Показать все заказы
                2 - Поиск заказа
                3 - Новый заказ
                4 - Отменить заказ
                5 - Изменить статус заказа
                6 - Изменить данные заказа
                7 - Вернуться назад
                """);
    }

    /**
     * Prints the request filter commands menu.
     */
    public static void printRequestFilterCommands() {
        System.out.print("""
                Выберите фильтр:
                1 - Поиск по дате
                2 - Поиск по клиенту
                3 - Поиск по статусу
                4 - Поиск по автомобилю
                5 - Вернуться назад
                """);
    }

    /**
     * Prints the request types commands menu.
     */
    public static void printRequestTypes() {
        System.out.print("""
                Выберите тип заказа:
                1 - Заказ на покупку автомобиля
                2 - Заявка на обслуживание автомобиля
                """);
    }
    /**
     * Prints the request statuses commands menu.
     */
    public static void printRequestStatuses() {
        System.out.print("""
                Выберите статус:
                1 - Новый
                2 - В ожидании
                3 - В работе
                4 - Завершен
                """);
    }

    /**
     * Prints the user roles commands menu.
     */
    public static void printUserRoles() {
        System.out.print("""
                Выберите роль
                1 - Клиент
                2 - Администратор
                3 - Менеджер
                """);
    }
}
