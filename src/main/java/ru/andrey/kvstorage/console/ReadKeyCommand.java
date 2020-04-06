package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {

    private final String databaseName;
    private final String tableName;
    private final String key;
    private final ExecutionEnvironment environment;

    public ReadKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String key) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isPresent()) {
            return DatabaseCommandResult.success(database.get().read(tableName, key));
        } else {
            return DatabaseCommandResult.error("There are no such a database: " + databaseName);
        }
    }
}
