public class Player extends Person {
    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        if (stake > this.getMoney()) {
            this.stake = 0;
        } else {
            this.stake = stake;
        }
    }

    private int stake;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public Player(int id, String name, int money) {
        super(name, money);
        this.id = id;
        this.status = Status.READY;
    }

    /**
     * After this was set, this player will never get the card again since its status has been {@link Status#FINISH}.
     */
    public void stopGettingCard() {
        status = Status.FINISH;
    }

    /**
     * For player, besides {@link Person#roundFinal()}, it will also need to empty the status and status.
     */
    @Override
    public void roundFinal() {
        super.roundFinal();
        this.setStake(0);
        this.setStatus(Status.READY);
    }
}