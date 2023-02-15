import java.awt.*;
import java.util.ArrayList;

// The Command interface defines the basic execute() method that all commands will implement.
interface Command {
    void execute();
}

// First Command - rot90
class rot90 implements Command {

    public ColorImage rot90(ColorImage currentImage, ArrayList<String> filter){
        int height = currentImage.getHeight();
        int width = currentImage.getWidth();
        ColorImage rotImage = new ColorImage(height, width);
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = currentImage.getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        filter.add("rot90");
        return rotImage;
    }
    @Override
    public void execute() {
        System.out.println("Rotation is successful");
    }
}

// Second Command - mono
class mono implements Command {

    public ColorImage mono(ColorImage currentImage, ArrayList<String> filter) {
        ColorImage tmpImage = new ColorImage(currentImage);
        //Graphics2D g2 = currentImage.createGraphics();
        int height = tmpImage.getHeight();
        int width = tmpImage.getWidth();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = tmpImage.getPixel(x, y);
                int lum = (int) Math.round(0.299*pix.getRed()
                        + 0.587*pix.getGreen()
                        + 0.114*pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }
        filter.add("mono");
        return tmpImage;
    }
    @Override
    public void execute() {
        System.out.println("Mono successful");
    }
}

// The Invoker class is responsible for executing commands. It contains a reference to the command object and
// calls the execute() method when it needs to perform an action.
class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

// The Client class is responsible for creating commands and configuring the invoker to use them.
class Client {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Command command1 = new rot90();
        Command command2 = new mono();

        invoker.setCommand(command1);
        invoker.executeCommand();

        invoker.setCommand(command2);
        invoker.executeCommand();
    }
}
