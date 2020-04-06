package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final String name;
    private final ExecutionEnvironment environment;

    public CreateDatabaseCommand(ExecutionEnvironment environment, String name) {
        this.environment = environment;
        this.name = name;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        // Это на случай, если все же нужно реализовывать интерфейс Database.
        // Правда тогда скорее всего придется изменить синтаксис этой команды,
        // потому что в теории реализаций Database может быть несколько, и
        // нужно понять, какую именно БД создавать

        //TODO:: update NULL with concrete instance, improve success message
        environment.addDatabase(null); // это заглушка
        return DatabaseCommandResult.success("Database was successfully created");
    }
}
