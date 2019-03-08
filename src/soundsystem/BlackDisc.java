package soundsystem;

public class BlackDisc implements CompactDisc {

    private String title;
    private String artist;

    public BlackDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void play() {

        System.out.println("Playing " + title + " by " + artist );
    }
}
