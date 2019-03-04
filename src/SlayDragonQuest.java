import java.io.PrintStream;

public class SlayDragonQuest implements Quest {

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public String getName() {

        return "SlayDragonQuest";
    }

    public void embark() {
        stream.println(" 正在屠龙中.... ");
    }
}
