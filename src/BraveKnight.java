public class BraveKnight implements Knight {

    private Quest quest;

//    // 但是骑士需要自己去管理游吟诗人，这事儿一点儿也不合理
//    private Minstrel minstrel;

    // 构造器注入 DI: 是要解决问题，但问题来自第三方而不是自己给自己创造困难
    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
//        minstrel.singBeforeQuest();
        quest.getName();
        quest.embark();
//        minstrel.singAfterQuest();
    }
}