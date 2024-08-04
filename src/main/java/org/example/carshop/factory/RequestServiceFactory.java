package org.example.carshop.factory;

import lombok.RequiredArgsConstructor;
import org.example.carshop.repository.RequestRepository;
import org.example.carshop.service.RequestService;

/**
 * Фабрика для создания объектов типа RequestService.
 * Реализует интерфейс CarshopFactory для создания экземпляров сервиса заказов.
 */
@RequiredArgsConstructor
public class RequestServiceFactory implements CarshopFactory<RequestService> {
    private final CarshopFactory<RequestRepository> requestRepositoryFactory;

    /**
     * Создает и возвращает новый экземпляр RequestService, используя фабрику requestRepositoryFactory
     * для создания необходимого RequestService.
     *
     * @return новый экземпляр RequestService
     */
    @Override
    public RequestService create() {
        return new RequestService(requestRepositoryFactory.create());
    }
}
