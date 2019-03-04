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