## Bean们

Spring的核心理念DI和AOP都是依赖与Bean的，而Spring Container本身就靠多种Bean相互协调发挥功能，容器管理着Bean的整个声明周期。

### bean的生命周期
bean在容器中的生命周期其实并不复杂，单纯的猜测就能猜出至少有 创建实例，初始化之前，初始化，初始化之后，销毁等步骤，真实的也是如此，正对着一部分在目前只需要只要即可，需要的时候再反过来去学习会更好一些

### Spring构架
Spring基本理念及设计目的，上一篇已经大概说明了，这里有必要全景的给出其整体构架了，如图：
![Spring构架](https://wx4.sinaimg.cn/mw1024/006mou3Bly1g0rx6kjqmmj310m0u04qp.jpg)


### Spring配置

三种配置形式：
1. XML中显示的配置
2. Java代码进行配置
3. 隐式的bean发现机制和自动装配

1跟2都应该能猜到肯定时某种特定的格式，让我很感兴趣的是第3种自动装配，在开发实践中3应该也是用的最多的，毕竟大家都是懒人

__自动装配__ 分为组建扫描（component scaning）+ 自动装配（Autowiring）2个步骤，也就是当扫描到组件，并自动配置开启后，Spring可以自行创建应用中第上下文创建bean，然后完成各个Bean之间的依赖协调。
还是直接上一个例子，比如我们设计一个CD播放的音响系统：
```java

/* ------ SgtPeppers 实现了 CompactDisc 接口  ---- */

// 通过注解，表示 组件 的身份，并且告知Spring为此类自动Bean
// 可以通过 @Component("beatPopAlbum") 来命名，如果缺省则会自动命名为类名 "sgtPeppers"
@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt Pepper's lonly hearts club band";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}

/* ------ CDPlayerConfig 为配置类，它启动对组件的扫描 --- */

@Configuration

// 此注解默认以包为界限，让Spring启动组件扫描，当然也可以用xml来启动，
// 如果想限定包范围可以 @ComponentScan(basePackages={"package001", "package002", ...})，但是面向字符串儿编程可不是什么好电子
// 更常见的还可以通过类名来设置范围 @ComponentScan(basePackageClasses={CDPlayer.class, DVDPlayer.class ...})
@ComponentScan  
public class CDPlayerConfig {
}

/* ------ 下面通过JUnit对其进行测试，看是否Spring为我们创建了 CompactDisc Bean --- */

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class) // <-- 为了测试而创建的 应用上下文
@ContextConfiguration(classes = CDPlayerConfig.class) // config指定
public class CDPlayerTest {

    @Autowired // <--- 指定 CompacteDisc bean自动注入到此处，当然也可以用 @Inject 这个属于JAVA规范中注释，虽然他们有些细微的差别
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull() {

        // 断言
        assertNotNull(cd);
        System.out.println(">>> haha , got a cd Bean here!");
    }

}
```
经过测试，通过断言并输出`>>> haha , got a cd Bean here!`，所以明显通过注解，Spring为我们自动创建并装配了 `CompactDisc Bean`. 写到这里也就理解了为什么自动装配会应用的更广泛，因为简直是太简单了😄。上述只是最简单的验证了 Bean 被创建了而已，下面说一个更一般化的例子能处理 Bean 依赖协调的例子：


