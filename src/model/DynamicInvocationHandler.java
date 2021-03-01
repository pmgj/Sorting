package model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DynamicInvocationHandler implements InvocationHandler {

    private final List<Integer> original;
    private final List<Command> commands = new ArrayList();

    public DynamicInvocationHandler(List<Integer> original) {
        this.original = original;
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("get")) {
            int index = (int) args[0];
            commands.add(new Command(Command.READ, index, original.get(index)));
        } else if (method.getName().equals("set")) {
            int index = (int) args[0];
            int value = (int) args[1];
            commands.add(new Command(Command.WRITE, index, value));
        }
        return method.invoke(original, args);
    }
}
