package org.example.carshop.factory;

import org.example.carshop.model.Request;
import org.example.carshop.repository.RequestRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для создания объектов типа RequestRepository.
 * Реализует интерфейс CarshopFactory для создания экземпляров репозитория заказов.
 */
public class RequestRepositoryFactory implements CarshopFactory<RequestRepository> {

    /**
     * Создает и возвращает новый экземпляр RequestRepository.
     *
     * @return новый экземпляр RequestRepository
     */
    @Override
    public RequestRepository create() {
        Map<Integer, Request> requestHashMap = new HashMap<>();
        return new RequestRepository(requestHashMap);
    }
}
