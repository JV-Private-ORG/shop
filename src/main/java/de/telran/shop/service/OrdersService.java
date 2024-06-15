package de.telran.shop.service;

import de.telran.shop.config.MapperUtil;
import de.telran.shop.dto.OrdersDto;
import de.telran.shop.entity.Orders;
import de.telran.shop.entity.Users;
import de.telran.shop.exceptions.DataNotFoundInDataBaseException;
import de.telran.shop.exceptions.InsufficientDataException;
import de.telran.shop.exceptions.InvalidValueExeption;
import de.telran.shop.exceptions.WrongIdException;
import de.telran.shop.mapper.Mappers;
import de.telran.shop.repository.OrdersRepository;
import de.telran.shop.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final Mappers mappers;

    public List<OrdersDto> getOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDto> ordersDtoList = MapperUtil.convertList(ordersList, mappers::convertToOrdersDto);

        return ordersDtoList;
    }


    public OrdersDto getOrdersById(Long id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        OrdersDto ordersDto;
        if (orders != null) {
            ordersDto = mappers.convertToOrdersDto(orders);
        } else {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }
        return ordersDto;
    }


    public void deleteOrdersById(Long id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders != null) {
            ordersRepository.delete(orders);
        } else {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }
    }

    public OrdersDto insertOrders(OrdersDto ordersDto) {
        if (ordersDto.getUsers() == null) {
            throw new InsufficientDataException("Data you entered is insufficient.");
        }
        if (ordersDto.getUsers().getUserId() == null){
            throw new InsufficientDataException("Data you entered is insufficient.");
        }
        if (usersRepository.findById(ordersDto.getUsers().getUserId()).orElse(null) == null) {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }
        Orders orders = mappers.convertToOrders(ordersDto);

        orders.setOrderId(0);
        orders.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Users users = usersRepository.findById(ordersDto.getUsers().getUserId()).orElse(null);
        orders.setUsers(users);

        Orders savedOrders = ordersRepository.save(orders);

        return mappers.convertToOrdersDto(savedOrders);
    }


    public OrdersDto updateOrders(OrdersDto ordersDto) {
        if (ordersDto.getOrderId() <= 0 || ordersDto.getUsers().getUserId() <= 0) { // При редактировании такого быть не должно, нужно вывести пользователю ошибку
            throw new InvalidValueExeption("The value you entered is not valid.");
        }

        Orders orders = ordersRepository.findById(ordersDto.getOrderId()).orElse(null);
               if (orders == null) {// Объект в БД не найден с таким orderId, нужно вывести пользователю ошибку
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }

//        if (ordersDto.getOrderId() != orders.getOrderId()) {//номер orders, введенный пользователем не совпадает с тем, который прописан в базе данных
//            throw new WrongIdException("Id you entered not found in database.");
//        }
        orders = mappers.convertToOrders(ordersDto);
        orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        Orders updatedOrders = ordersRepository.save(orders);

        return mappers.convertToOrdersDto(updatedOrders);
    }
}
