package ch.manoj.tasktracker.service;

import ch.manoj.tasktracker.dto.UsersDto;
import ch.manoj.tasktracker.entity.Users;
import ch.manoj.tasktracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        Users savedUsers = userRepository.save(mapper.map(usersDto, Users.class));
        return mapper.map(savedUsers, UsersDto.class);
    }

}
