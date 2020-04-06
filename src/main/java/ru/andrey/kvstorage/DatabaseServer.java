package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        /*
         Тут может показаться, что я, бросая исключения в try-catch блоке,
         совершаю стилистическую ошибку, потому что то же самое можно сделать,
         не используя try-catch, а оставить просто два if-a и в каждом
         возвращаться соотвествующий результат, генеруруя error message прямо на месте.
         Но это не так:
            * метод valueOf бросит IllegalArgumentException, если не найдет соответвующей команды
            * метод getCommand бросит IllegalArgumentException, если кол-во переданных
                аргументов неверно
            * метод execute бросит DatabaseException, если исполнение команды произойдет с ошибкой
         Так как я отлавливаю только эти исключения, то мне кажется, что бросание
         IllegalArgumentException внутри try-catch блока
         (учитывая, что эти исключения в любом случае надо отлаливать и обрабатывать),
         делает код только читабельнее и не является ошибкой.
         Тем более, что эти исключения действительно являются IllegalArgumentException, то есть
         это не "хак", чтобы писать "меньше кода", а удачное стечение обстоятельств.
         */
        try {
            if (commandText == null) {
                throw new IllegalArgumentException("No command name");
            }
            String[] args = commandText.split(" ");
            if (args.length <= 1) {
                throw new IllegalArgumentException("Wrong number of arguments");
            }
            return DatabaseCommands.valueOf(args[0]).getCommand(env,
                    Arrays.copyOfRange(args, 1, args.length)).execute();
        } catch (DatabaseException | IllegalArgumentException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
