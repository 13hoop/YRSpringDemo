package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class CDPlayer implements MediaPlayer{

    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc cd) {   // <-- 构造器注入
        this.cd = cd;
    }

    @Autowired
    public void setCd(CompactDisc cd) { // <--- setter注入
        this.cd = cd;
    }

    @Autowired
    public  void openACD(CompactDisc cd) { // <-- 自定义方法注入
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
