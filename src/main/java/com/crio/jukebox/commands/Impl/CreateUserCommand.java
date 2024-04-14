package com.crio.jukebox.commands.Impl;

import java.util.List;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand {

    private final IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        User userEntity = userService.loadByName(tokens.get(1));
        System.out.println(userEntity);
    }

}
