package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.crio.jukebox.exception.JukeboxException;

public class CommandInvoker {
    private final Map<String, ICommand> commands;

    public CommandInvoker() {
        this.commands = new HashMap<>();
    }

    public void addCommand(String commandName, ICommand command) {
        this.commands.putIfAbsent(commandName, command);
    }

    public ICommand get(String commandName) {
        return this.commands.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) {
        ICommand command = this.commands.get(commandName);
        if (Objects.isNull(command)) {
            throw new JukeboxException();
        }
        command.execute(tokens);
    }

}
