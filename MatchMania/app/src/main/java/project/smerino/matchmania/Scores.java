package project.smerino.matchmania;

public class Scores {
    private final String desc;

    public Scores(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString(){return this.getDesc(); }
}