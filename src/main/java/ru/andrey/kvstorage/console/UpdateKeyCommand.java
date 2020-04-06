package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {

    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;
    private final ExecutionEnvironment environment;

    public UpdateKeyCommand(ExecutionEnvironment environment,
                            String databaseName, String tableName, String key, String value) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isPresent()) {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success("Key was successfully updated");
        } else {
            return DatabaseCommandResult.error("There are no such a database: " + databaseName);
        }
    }
}
