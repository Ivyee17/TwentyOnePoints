/**
 * Mark players' status, which should be set by {@link Player}.
 * Seven status are given. {@code READY} means the game hasn't start.
 * {@code INPROGRESS} means the game is playing, which should not add players in this status.
 * {@code FINISH}, an instantaneous status, means the game is finished, but has not judged by {@link Game#calculateResult()}.
 * {@code LOST}, {@code WIN1}, {@code WIN15} and {@code WIN2} is the result of this game.
 */
public enum Status {
    READY("游戏未开始"),INPROGRESS("正在进行游戏"),FINISH("已结束，正在评分"),
    LOST("本轮游戏已输"),WIN2("赢得两倍赌金"),WIN15("赢得1.5倍赌金"),WIN1("赢得一倍赌金");

    private String name;
    Status(String name) {
        this.name=name;
    }

    /**
     * Return the meaning of the status.
     * @return the meaning of the status.
     */
    @Override
    public String toString() {
        return name;
    }
}