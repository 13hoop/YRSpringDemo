package soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class) // <-- 为了测试而创建的 应用上下文
@ContextConfiguration(classes = CDPlayerConfig.class) // config指定
public class CDPlayerTest {

    @Autowired // <--- 指定 CompacteDisc bean自动注入到此处
    private CompactDisc cd;

//    @Rule
//    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc player;

    @Test
    public void cdShouldNotBeNull() {

        // 断言
        assertNotNull(cd);

        System.out.println(">>> haha , got a cd Done here!");
    }


    @Test
    public void play() {
        player.play();
        assertNotNull(player);
//        assertEquals("Playing Sgt Pepper's lonely hearts club band by The Beatles", );
    }

}