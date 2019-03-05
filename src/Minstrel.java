import java.io.PrintStream;

public class Minstrel {

    private PrintStream stream;
    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la , the Knight is so brave ...");
    }
    public void singAfterQuest() {
        stream.println("Tree hee hee, the barve Knight "  + " did embark on a quest");
    }
}
