package soundsystem;

import org.springframework.stereotype.Component;

// 通过注解，表示组件的身份，并且告知Spring为此类自动Bean
@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt Pepper's lonely hearts club band";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
