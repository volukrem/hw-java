package org.example.carshop.in;

import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;
import org.example.carshop.model.enums.UserRole;
import org.example.carshop.model.Request;
import org.example.carshop.model.Car;
import org.example.carshop.model.User;
import org.example.carshop.out.ConsoleUI;
import org.example.carshop.service.RequestService;
import org.example.carshop.service.CarShopService;
import org.example.carshop.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.time.LocalDateTime.*;

/**
 * UserConsole processes user input and manages the logic for a car store management system.
 * This class handles user registration, authorization, and navigation through various commands for users and administrators.
 */
public class UserConsole {
    private UserService userService;
    private CarShopService carShopService;
    private RequestService requestService;
    private Scanner in = new Scanner(System.in);
    private User currentUser;
    private boolean isAuthorized = false;

    /**
     * Constructs a UserConsole object with the necessary services.
     *
     * @param userService    User service instance for managing user-related operations.
     * @param carShopService Car service instance for managing car-related operations.
     * @param requestService Request service instance for managing request-related operations.
     */
    public UserConsole(UserService userService, CarShopService carShopService, RequestService requestService) {
        this.userService = userService;
        this.carShopService = carShopService;
        this.requestService = requestService;
    }

    /**
     * Runs the initial commands loop of the console application.
     * Handles user registration, authorization, and main menu navigation based on user roles.
     */
    public void runStartCommands() {
        while (true) {
            try {

                if (isAuthorized) {
                    switch (currentUser.getRole()) {
                        case ADMIN -> runCarShopAdminCommands();
                        case CLIENT -> runCarShopClientCommands();
                        case MANAGER -> runCarShopManagerCommands();
                    }
                }

                ConsoleUI.printStartCommands();

                int command = in.nextInt();
                in.nextLine();

                switch (command) {
                    case 1 -> registration();
                    case 2 -> authorization();
                    case 3 -> {
                        in.close();
                        return;
                    }
                    default -> System.out.println("Неверная команда");
                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Неверный формат ввода команды. Введите корректные данные");
                in.next();
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println("Некорректный формат ввода данных бронирования. Введите корректные данные");
            }
        }
    }

    /**
     * Handles the user registration process.
     * Prompts the user for username, password, and role selection to create a new user account.
     */
    private void registration() {
        System.out.print("Введите имя пользователя: ");
        String username = in.nextLine();
        User user = userService.findUserByName(username);

        if (user != null) {
            System.out.println("Пользователь с таким username уже существует!");
            return;
        }

        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        System.out.print("Введите ФИО: ");
        String fullName = in.nextLine();

        ConsoleUI.printUserRoles();
        int userRole = in.nextInt();
        in.nextLine();

        switch (userRole) {
            case 1 -> userService.register(new User(username, password, fullName, UserRole.CLIENT));
            case 2 -> userService.register(new User(username, password, fullName, UserRole.ADMIN));
            case 3 -> userService.register(new User(username, password, fullName, UserRole.MANAGER));
            default -> {
                System.out.println("Введена неверная комманда!");
                return;
            }
        }
        System.out.println("Регистрация прошла успешна!");
    }

    /**
     * Handles the user authorization process.
     * Prompts the user for username and password to authenticate and log in to the system.
     */
    private void authorization() {
        System.out.print("Введите имя пользователя: ");
        String username = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        User user = new User(username, password);
        if (userService.findUserByName(username) == null) {
            System.out.println("Пользователя с таким именем не существует!");
            return;
        }

        boolean success = userService.login(user);
        if (success) {
            System.out.println("Авторизация прошла успешно.");
            currentUser = userService.findUserByName(username);
            isAuthorized = true;
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    /**
     * Runs the commands loop for administrative operations in the Car Shop.
     */
    public void runCarShopAdminCommands() {
        while (isAuthorized) {

            ConsoleUI.printCarShopAdminCommands();

            int command = in.nextInt();
            in.nextLine();
            switch (command) {
                case 1 -> runCarCommands();
                case 2 -> runRequestCommands();
                case 3 -> runUsersCommands();
                case 4 -> {
                    isAuthorized = false;
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Неверная команда");
            }
        }
    }

    /**
     * Runs the commands loop for customer operations.
     * Provides options for viewing cars available for purchase, customer purchase requests,
     * and options for creating new purchase requests.
     */
    public void runCarShopClientCommands() {
        while (isAuthorized) {

            ConsoleUI.printCarShopClientCommands();

            int command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 -> printCollection(carShopService.searchCars(null, null, null, true));
                case 2 -> printCollection(requestService.filterRequestsByUserAndType(currentUser, null));
                case 3 -> {
                    System.out.print("Введите ID автомобиля: ");
                    int carId = in.nextInt();
                    in.nextLine();
                    Car car = carShopService.findCarById(carId);
                    if (car != null && !car.isAvailable()) {
                        System.out.println("Автомобиль не доступен к заказу");
                        continue;
                    } else if (car == null) {
                        System.out.println("Автомобиль с таким ID не существует");
                        continue;
                    }
                    requestService.addRequest(new Request(currentUser, carId, now(), null, RequestType.ORDER, RequestStatus.NEW));
                    car.setAvailable(false);
                    System.out.print("Заказ успешно добавлен!");
                }
                case 4 -> {
                    isAuthorized = false;
                    currentUser = null;
                }
                default -> System.out.println("Неверная команда");
            }

        }
    }

    /**
     * Runs the commands loop for Manager operations.
     * Provides options for searching cars, requests management, viewing users info.
     */
    public void runCarShopManagerCommands() {
        while (isAuthorized) {

            ConsoleUI.printCarShopManagerCommands();

            int command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 -> {
                    System.out.print("Введите название модели: ");
                    String modelName = in.nextLine();

                    System.out.print("Введите название производителя: ");
                    String brandName = in.nextLine();

                    System.out.print("Введите год производства: ");
                    String prodYear = in.nextLine();

                    printCollection(carShopService.searchCars(brandName, modelName, prodYear, null));

                }
                case 2 -> runRequestCommands();
                case 3 -> runUsersCommands();
                case 4 -> {
                    isAuthorized = false;
                    currentUser = null;
                }
                default -> System.out.println("Неверная команда");
            }

        }
    }

    /**
     * Runs the commands loop for managing Car operations.
     * Includes options for adding, updating, deleting cars.
     */
    public void runCarCommands() {
        while (true) {
            ConsoleUI.printCarCommands();

            int command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 -> {
                    System.out.println("Добавление автомобиля");
                    System.out.print("Введите идентификатор:");
                    int id = in.nextInt();
                    in.nextLine();
                    Car car = carShopService.findCarById(id);

                    if (car != null) {
                        System.out.println("Автомобиль с таким ID уже существует!");
                        continue;
                    }

                    System.out.print("Введите название модели: ");
                    String modelName = in.nextLine();

                    System.out.print("Введите название производителя: ");
                    String brandName = in.nextLine();

                    System.out.print("Введите год производства: ");
                    String prodYear = in.nextLine();

                    System.out.print("Введите описание текущего состояния: ");
                    String stateDesc = in.nextLine();

                    carShopService.addCar(new Car(id, modelName, brandName, prodYear, stateDesc, true));
                }
                case 2 -> {
                    System.out.println("Изменение данных об автомобиле");
                    System.out.print("Введите идентификатор: ");
                    int id = in.nextInt();
                    in.nextLine();
                    Car car = carShopService.findCarById(id);

                    if (car == null) {
                        System.out.println("Автомобиль с таким ID не существует!");
                        continue;
                    }

                    System.out.print("Введите название модели: ");
                    String modelName = in.nextLine();

                    System.out.print("Введите название производителя: ");
                    String brandName = in.nextLine();

                    System.out.print("Введите год производства: ");
                    String prodYear = in.nextLine();

                    System.out.print("Введите описание текущего состояния: ");
                    String stateDesc = in.nextLine();

                    ConsoleUI.printAvailabilityCommands();
                    int availableChoice = in.nextInt();
                    in.nextLine();

                    boolean isAvailable;
                    switch (availableChoice) {
                        case 1 -> isAvailable = false;
                        case 2 -> isAvailable = true;
                        default -> {
                            System.out.println("Произошла ошибка");
                            continue;
                        }
                    }

                    Car updatedCar = new Car(id, modelName, brandName, prodYear, stateDesc, isAvailable);
                    carShopService.updateCar(updatedCar);
                }
                case 3 -> {
                    System.out.println("Удаление автомобиля");
                    System.out.print("Введите идентификатор: ");
                    int id = in.nextInt();
                    in.nextLine();

                    Car car = carShopService.findCarById(id);

                    if (car != null) {
                        System.out.println("Автомобиль с таким ID не существует!");
                        continue;
                    }

                    carShopService.deleteCar(id);
                }
                case 4 -> printCollection(carShopService.getAllCars());
                case 5 -> {
                    return;
                }

                default -> System.out.println("Неверная команда");
            }
        }
    }

    /**
     * Runs the commands loop for managing Car Shop employees and clients.
     * Includes options for adding, updating, retrieving users.
     */
    public void runUsersCommands() {
        while (true) {
            ConsoleUI.printUsersCommands();

            int command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 -> printCollection(userService.searchUsers(null, List.of(UserRole.CLIENT)));
                case 2 -> printCollection(userService.searchUsers(null, Arrays.asList(UserRole.MANAGER, UserRole.ADMIN)));
                case 3 -> {
                    System.out.print("Введите имя пользователя: ");
                    String userName = in.nextLine();

                    ConsoleUI.printUserRoles();
                    UserRole userRole = UserRole.CLIENT;
                    int requestTypeIn = in.nextInt();
                    in.nextLine();
                    switch (requestTypeIn) {
                        case 1 -> {
                        }
                        case 2 -> userRole = UserRole.ADMIN;
                        case 3 -> userRole = UserRole.MANAGER;
                        default -> System.out.println("Введена несуществующая команда!");
                    }
                    printCollection(userService.searchUsers(userName,List.of(userRole)));
                }
                case 4 -> registration();
                case 5 ->{
                    System.out.print("Введите username пользователя: ");
                    String username = in.nextLine();
                    User user = userService.findUserByName(username);
                    if (user == null) {
                        System.out.println("Пользователя с таким именем не существует!");
                        continue;
                    }

                    System.out.print("Введите новое ФИО пользователя: ");
                    String fullName = in.nextLine();

                    System.out.print("Введите новый пароль пользователя: ");
                    String password = in.nextLine();

                    ConsoleUI.printUserRoles();
                    UserRole userRole = UserRole.CLIENT;
                    int requestTypeIn = in.nextInt();
                    in.nextLine();
                    switch (requestTypeIn) {
                        case 1 -> {}
                        case 2 -> userRole = UserRole.ADMIN;
                        case 3 -> userRole = UserRole.MANAGER;
                        default -> System.out.println("Введена несуществующая команда!");
                    }

                    User updatedUser = new User(username, password, fullName, userRole);
                    userService.updateUser(updatedUser);
                }
                case 6 -> {return;}
                default -> System.out.println("Неверная команда");
            }
        }
    }

    /**
     * Prompts the user to input request date and time.
     *
     * @return An array of LocalDateTime objects representing creation and completion date/time of request.
     */
    public LocalDateTime[] RequestDateTimeInput() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("Введите значение даты создания заказа (в формате YYYY-MM-DD):");
        String startDate = in.nextLine();
        LocalDate startLocalDate = LocalDate.parse(startDate, dateFormatter);

        System.out.println("Введите значение времени создания заказа (в формате HH:MM):");
        String startTime = in.nextLine();
        LocalTime startLocalTime = LocalTime.parse(startTime, timeFormatter);

        LocalDateTime startDateTime = of(startLocalDate, startLocalTime);

        System.out.println("Введите значение даты выполнения заказа (в формате YYYY-MM-DD):");
        String endDate = in.nextLine();
        LocalDate endLocalDate = LocalDate.parse(endDate, dateFormatter);

        System.out.println("Введите значение времени выполнения заказа (в формате HH:MM):");
        String endTime = in.nextLine();
        LocalTime endLocalTime = LocalTime.parse(endTime, timeFormatter);

        LocalDateTime endDateTime = of(endLocalDate, endLocalTime);

        if (startDateTime.isAfter(endDateTime)) {
            System.out.println("Дата конца бронирования не может идти раньше начала!");
            return null;
        }
        return new LocalDateTime[]{startDateTime, endDateTime};
    }

    /**
     * Runs the commands loop for managing requests.
     * Provides options for viewing, filtering, creating, and canceling request for car order and maintenance.
     */
    public void runRequestCommands() {
        while (true) {
            ConsoleUI.printRequestCommands();

            int command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 -> printCollection(requestService.getAllRequests());
                case 2 -> {
                    ConsoleUI.printRequestTypes();
                    RequestType requestType = RequestType.ORDER;
                    int requestTypeIn = in.nextInt();
                    in.nextLine();

                    switch (requestTypeIn) {
                        case 1 -> {
                        }
                        case 2 -> requestType = RequestType.MAINTENANCE;
                        default -> System.out.println("Введена несуществующая команда!");
                    }

                    ConsoleUI.printRequestFilterCommands();
                    int filterKindIn = in.nextInt();
                    in.nextLine();

                    switch (filterKindIn) {
                        case 1 -> {
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            System.out.println("Введите дату для фильтрации заказов (в формате YYYY-MM-DD):");
                            String startDate = in.nextLine();
                            LocalDate startLocalDate = LocalDate.parse(startDate, dateFormatter);
                            printCollection(requestService.filterRequestsByDateAndType(startLocalDate, requestType));
                        }
                        case 2 -> {
                            System.out.print("Введите имя пользователя для фильтрации заказов: ");
                            String username = in.nextLine();
                            User filterUser = userService.findUserByName(username);
                            if (filterUser != null) {
                                printCollection(requestService.filterRequestsByUserAndType(filterUser, requestType));
                            } else {
                                System.out.println("Пользователь с таким username не найден!");
                            }
                        }
                        case 3 -> {
                            System.out.print("Выберите статус для фильтрации заказов: ");
                            ConsoleUI.printRequestStatuses();
                            RequestStatus requestStatus = RequestStatus.NEW;
                            int requestStatusIn = in.nextInt();
                            in.nextLine();
                            switch (requestStatusIn) {
                                case 1 -> {}
                                case 2 -> requestStatus = RequestStatus.PENDING;
                                case 3 -> requestStatus = RequestStatus.IN_PROGRESS;
                                case 4 -> requestStatus = RequestStatus.COMPLETED;
                                default -> System.out.println("Введена несуществующая команда!");
                            }
                            printCollection(requestService.filterRequestsByRequestStatusAndType(requestStatus, requestType));
                        }
                        case 4 -> {
                            System.out.print("Введите идентификатор автомобиля для фильтрации заказов: ");
                            int carId = in.nextInt();
                            in.nextLine();
                            Car car = carShopService.findCarById(carId);
                            if (car != null) {
                                printCollection(requestService.filterRequestsByCarAndType(carId, requestType));
                            } else {
                                System.out.println("Автомобиль с таким ID не найден!");
                            }
                        }
                        case 5 -> {
                            return;
                        }
                        default -> System.out.println("Введена несуществующая команда!");
                    }
                }
                case 3 -> {
                    System.out.print("Введите ID автомобиля: ");
                    int carId = in.nextInt();
                    in.nextLine();
                    Car car = carShopService.findCarById(carId);
                    if (car != null && !car.isAvailable()) {
                        System.out.println("Автомобиль не доступен к заказу");
                        continue;
                    } else if (car == null) {
                        System.out.println("Автомобиль с таким ID не существует");
                        continue;
                    }
                    requestService.addRequest(new Request(currentUser, carId, now(), null, RequestType.ORDER, RequestStatus.NEW));
                    car.setAvailable(false);
                    System.out.print("Заказ успешно добавлен!");
                }
                case 4 -> {
                    System.out.print("Введите идентификатор: ");
                    int id = in.nextInt();
                    in.nextLine();
                    Request foundRequest = requestService.findRequestById(id);

                    if (foundRequest != null) {
                        requestService.deleteRequest(id);
                    } else {
                        System.out.println("Заказ с таким ID не существует!");
                    }
                }
                case 5 -> {
                    System.out.print("Введите идентификатор: ");
                    int id = in.nextInt();
                    in.nextLine();
                    Request foundRequest = requestService.findRequestById(id);

                    if (foundRequest != null) {
                        System.out.print("Выберите статус: ");
                        ConsoleUI.printRequestStatuses();
                        RequestStatus requestStatus = RequestStatus.NEW;
                        int requestStatusIn = in.nextInt();
                        in.nextLine();
                        switch (requestStatusIn) {
                            case 1 -> {}
                            case 2 -> requestStatus = RequestStatus.PENDING;
                            case 3 -> requestStatus = RequestStatus.IN_PROGRESS;
                            case 4 -> requestStatus = RequestStatus.COMPLETED;
                            default -> System.out.println("Введена несуществующая команда!");
                        }
                        foundRequest.changeStatus(requestStatus);
                    } else {
                        System.out.println("Заказ с таким ID не существует!");
                    }
                }
                case 6 -> {
                    System.out.print("Введите идентификатор: ");
                    int id = in.nextInt();
                    in.nextLine();
                    Request foundRequest = requestService.findRequestById(id);
                    if (foundRequest == null) {
                        System.out.println("Заказа с таким ID не существует!");
                        continue;
                    }

                    System.out.print("Введите username пользователя: ");
                    String username = in.nextLine();
                    User user = userService.findUserByName(username);
                    if (user == null) {
                        System.out.println("Пользователя с таким именем не существует!");
                        continue;
                    }

                    System.out.print("Введите ID автомобиля: ");
                    int carId = in.nextInt();
                    in.nextLine();
                    Car car = carShopService.findCarById(carId);
                    if (car == null) {
                        System.out.println("Автомобиль с таким ID не существует");
                        continue;
                    }

                    LocalDateTime[] requestDateTimes = RequestDateTimeInput();

                    ConsoleUI.printRequestTypes();
                    RequestType requestType = RequestType.ORDER;
                    int requestTypeIn = in.nextInt();
                    in.nextLine();
                    switch (requestTypeIn) {
                        case 1 -> {}
                        case 2 -> requestType = RequestType.MAINTENANCE;
                        default -> System.out.println("Введена несуществующая команда!");
                    }

                    System.out.print("Выберите статус: ");
                    ConsoleUI.printRequestStatuses();
                    RequestStatus requestStatus = RequestStatus.NEW;
                    int requestStatusIn = in.nextInt();
                    in.nextLine();

                    switch (requestStatusIn) {
                        case 1 -> {}
                        case 2 -> requestStatus = RequestStatus.PENDING;
                        case 3 -> requestStatus = RequestStatus.IN_PROGRESS;
                        case 4 -> requestStatus = RequestStatus.COMPLETED;
                        default -> System.out.println("Введена несуществующая команда!");
                    }
                    requestService.addRequest(new Request(user, carId, requestDateTimes[0], requestDateTimes[1], requestType, requestStatus));
                }
                case 7 -> {
                    isAuthorized = false;
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Неверная команда");
            }
        }
    }

    /**
     * Prints the elements of an ArrayList to the console.
     *
     * @param collection The Collection containing elements to print.
     */
    public void printCollection(Collection<?> collection) {
        for (Object object : collection) {
            System.out.println(object);
        }
    }
}
