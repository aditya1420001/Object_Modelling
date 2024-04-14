package com.crio.codingame.commands;

import java.util.List;
import java.util.Optional;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
      final String userName = Optional.ofNullable(tokens)
      .map(list -> list.get(1))
      .orElseThrow(() -> new RuntimeException());

      User user = userService.create(userName);
      System.out.println(user);
    }
    
}
