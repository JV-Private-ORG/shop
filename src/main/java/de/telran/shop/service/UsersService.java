package de.telran.shop.service;

import de.telran.shop.config.MapperUtil;
import de.telran.shop.dto.UsersDto;
import de.telran.shop.entity.Users;
import de.telran.shop.exceptions.DataNotFoundInDataBaseException;
import de.telran.shop.exceptions.InvalidValueExeption;
import de.telran.shop.exceptions.WrongIdException;
import de.telran.shop.mapper.Mappers;
import de.telran.shop.repository.CartRepository;
import de.telran.shop.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final Mappers mappers;

    public List<UsersDto> getUsers() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDto> usersDtoList = MapperUtil.convertList(usersList, mappers::convertToUsersDto);

        return usersDtoList;
    }

    public UsersDto getUsersById(Long id) {
        Users users = usersRepository.findById(id).orElse(null);
        UsersDto usersDto;
        if (users != null) {
            usersDto = mappers.convertToUsersDto(users);
        } else {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }
        return usersDto;
    }


    public void deleteUsersById(Long id) {
        Users users = usersRepository.findById(id).orElse(null);
        if (users != null) {
            usersRepository.delete(users);
        } else {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }

    }


    public UsersDto insertUsers(UsersDto usersDto) {
        Users users = mappers.convertToUsers(usersDto);

        users.setUserId(0);
        Users savedUsers = usersRepository.save(users);

        return mappers.convertToUsersDto(savedUsers);
    }


    public UsersDto updateUsers(UsersDto usersDto) {
        if (usersDto.getUserId() <= 0) {
            throw new InvalidValueExeption("The value you entered is not valid.");
        }

        Users users = usersRepository.findById(usersDto.getUserId()).orElse(null);
        if (users == null) {
            throw new DataNotFoundInDataBaseException("Data not found in database.");
        }

//        if (usersDto.getUserId() != users.getUserId()) {//номер users, введенный пользователем не совпадает с тем, который прописан в базе данных
//            throw new WrongIdException("Id you entered not found in database.");
//        }

        users = mappers.convertToUsers(usersDto);
        Users updatedUsers = usersRepository.save(users);

        return mappers.convertToUsersDto(updatedUsers);
    }
}
