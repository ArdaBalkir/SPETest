import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Command<T> {
    public ColorImage execute(String name);
}



class MonoCommand implements Command {
    private ColorImage picture;

    public MonoCommand(ColorImage picture) {
        this.picture = picture;
    }

    public ColorImage execute(String name) {
        //picture
        return null;
    }
}

class RotateCommand implements Command {
    private ColorImage picture;

    public RotateCommand(ColorImage picture) {
        this.picture = picture;
    }

    public ColorImage execute(String name) {
        //picture
        return null;
    }
}

class LoadCommand implements Command {
    private ColorImage picture;
    private String name;

    public LoadCommand() {
        //this.picture = picture;
        //this.name = name;
    }

    public ColorImage execute(String name) {
        try {
            picture = new ColorImage(ImageIO.read(new File(name)));
        } catch (IOException e) {
            System.out.println("Cannot find image file, " + name);
            System.out.println("cwd is " + System.getProperty("user.dir"));
        }
        return picture;
    }
}

class Invoker {
    private Command command;
    private List<Command> executedCommands;

    Invoker() {
        this.executedCommands = new ArrayList<Command>();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public ColorImage runCommand(String name) {
        executedCommands.add(command);
        return command.execute(name);
    }

    public void printExecutedCommands() {
        System.out.println("Executed Commands:");
        for (Command command : executedCommands) {
            System.out.println("- " + command.getClass().getSimpleName());
        }
    }
}
class Editor {
    public static void main(String[] args) {
        ColorImage currImg = null;
        Command mono = new MonoCommand(currImg);
        Command rot90 = new RotateCommand(currImg);
        Command open = new LoadCommand();

        Invoker invoker = new Invoker();

        invoker.setCommand(mono);
        currImg = invoker.runCommand(null); // Mono applied

        invoker.setCommand(rot90);
        currImg = invoker.runCommand(null); // Rot applied

        invoker.printExecutedCommands(); // Script command

        invoker.setCommand(open);
        currImg = invoker.runCommand("FileName"); // Opened the file with the set name
    }
}
