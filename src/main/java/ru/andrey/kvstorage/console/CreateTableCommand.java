package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {

    private final String databaseName;
    private final String tableName;
    private final ExecutionEnvironment environment;

    public CreateTableCommand(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isPresent()) {
            database.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success("Table was successfully created: " + tableName);
        } else {
            return DatabaseCommandResult.error("There are no such a database: " + databaseName);
        }
    }
}
