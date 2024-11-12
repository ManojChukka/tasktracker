package ch.manoj.tasktracker.service;

import ch.manoj.tasktracker.dto.UsersDto;
import org.springframework.stereotype.Service;

public interface UserService {

    public UsersDto createUser(UsersDto usersDto);
}
