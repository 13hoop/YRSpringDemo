### 依赖注入
说白了，就是通过多态特性，完成对某种实现这一特定接口不同对象的实例

便于测试吗？show some code：
```java

```

##### 装配Wiring
先用xml装配，以后其他形式的装配暂时按下
```java
public class BraveKnight implements Knight {

    private Quest quest;

    // 构造器注入 DI: 是要解决问题，但问题来自第三方而不是自己给自己创造困难
    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.getName();
        quest.embark();
    }
}

// SlayDragonQuest
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

// ------ main ------
    public static void main(String [] args) {
        String xmlPath = "beans.xml";
        ApplicationContext cxt = new ClassPathXmlApplicationContext(xmlPath);
        Knight kk = cxt.getBean(Knight.class); // 获取 bean 实例
        kk.embarkOnQuest();
    }

```
这样Knighy不用操心是那种Quest，全部都交给了xml去装配，不管是 屠龙还是就美女，也不管是那种骑士，如此就实现了代码的松耦合
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 初始化 及 注入quest -->
    <bean id="knight" class="BraveKnight">
        <constructor-arg ref="quest"></constructor-arg>
    </bean>
    <!-- 实例化 SlayDragonQuest -->
    <bean id="quest" class="SlayDragonQuest">
        <constructor-arg value="#{T(System).out}"></constructor-arg>
    </bean>
</beans>
```

### 应用切面，面向切面编程（aspect-oritented program, AOP）
其实这并不是一个新概念，而是非常合理而自然的，试想任何一个项目总有很多诸如 日记采集，事务管理，安全权限分配，甚至网络数据交换等等这样的，需要融入到核心业务逻辑中的代码和功能，这些系统性的服务我们给他起个名字，横切关注点，也就是说它们总会跨越多种组建。这带来了2个问题：重复代码 跟 逻辑混淆。
![](https://wx3.sinaimg.cn/mw1024/006mou3Bly1g0qsg82dkcj319i0u0h50.jpg)

AOP就是试图更好的解决这个问题而衍生出来的，究竟是如何处理的呢？还是回到上边的例子，骑士的事迹需要游吟诗人（Minstrel）的记录跟传播的，换言之在我们骑士的故事中，Mintrel就是横断关注点。
```java
// 游吟诗人
public class Minstrel {

    private PrintStream stream;
    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la , the Knight is so brave ...");
    }
    public void singAfterQuest() {
        stream.println("Tree hee hee, the barve Knighi "  + " did embark on a quest");
    }
}

// 让游吟诗人发挥作用
public class BraveKnight implements Knight {

    private Quest quest;
    private Minstrel minstrel;

    // 构造器注入 DI: 是要解决问题，但问题来自第三方而不是自己给自己创造困难
    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        minstrel.singBeforeQuest();
        quest.getName();
        quest.embark();
        minstrel.singAfterQuest();
    }
}
```
但是骑士需要自己去管理游吟诗人，这事儿一点儿也不合理，特别是自己要去调Minstrel的方法，而AOP下是如何处理的呢？还是通过xml配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 初始化 及 注入quest -->
    <bean id="knight" class="BraveKnight">
        <constructor-arg ref="quest"></constructor-arg>
    </bean>


    <!-- 实例化 SlayDragonQuest -->
    <bean id="quest" class="SlayDragonQuest">
        <constructor-arg value="#{T(System).out}"></constructor-arg>
    </bean>

    <bean id="minstrel" class="Minstrel">
        <constructor-arg value="#{T(System).out}"></constructor-arg>
    </bean>

    <aop:config>
        <!--将 minstrel 声明为一个 切面-->
        <aop:aspect ref="minstrel">
            <!-- 配置在哪里，哪个时机进行切入 -->
            <aop:pointcut id="embarkPC" expression="execution(* *.embarkOnQuest(..))"/>
            <aop:before pointcut-ref="embarkPC" method="singBeforeQuest"></aop:before>
            <aop:after pointcut-ref="embarkPC" method="singAfterQuest"></aop:after>
        </aop:aspect>
    </aop:config>
</beans>
```
这样以来就可以完全删除`BraveKnight`中所有`Minstrel`的内容了，这简直是一件可以称得上是神奇的事儿，完全没有污染代码的可能了。而`Minstrel`也保证了自己是个简单的POJO。

### 使用模版消除样板式代码（deal with Poilerplate Code）
这方面最卓有成效的成果就是 `JDBCTemplate`了，通过它简化了JDBC从建立链接，检查库是否存在，数据是否存在，异常捕获，关闭库断开连接等等一系列流程化的代码，如：
```java
    public Knight getKnightById(long id) {
        return jdbcTemplate.queryForObject(
                "selected id, name, age, salary" +
                        "from knight where id=?",
                new RowMapper<Knight>() {
                    public Knight mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Knight knight = new Knight();
                        knight.setName(rs.getString("name"));
                        ...
                        return knight;
                    }
                },
                id);
    }
```

以上就是4种策略全景时的展现，我想Spring带来的简化和解藕应该能感受到一些了吧，再回去看看开头的那四种策略应该会有一个初步的认同了，可看到上述过程大量使用的xml装配bean，那就近有何玄机呢，这一点就在下篇继续吧。




