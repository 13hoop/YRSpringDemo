public class DamselRecuingKnight implements Knight {

    private DamselQuest quest;

    // 高耦合，繁测试
    public DamselRecuingKnight() {
        this.quest = new DamselQuest();
    }

    public void embarkOnQuest() {
        quest.embark();
        quest.getName();
    }
}

