import java.util.List;

public class GameRoom implements IGameRoom {
    private Finance finance;
    private Game game;

    public List<Deck> getDecks() {
        return game.getDecks();
    }


    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public Dealer getDealer() {
        return game.getDealer();
    }

    public GameRoom() {
        this.game=new Game();
    }

    public Player initPlayer(String name, int money) {
        Player player=new Player(game.getPlayers().size(),name,money);
        game.getPlayers().add(player);
        return player;
    }

    public Dealer initDealer(int deckNumber,int dealerMoney) {
        Dealer dealer=new Dealer("Dealer",dealerMoney);
        for(int i=0;i<deckNumber;i++){
            Deck deck=new Deck();
            game.getDecks().add(deck);
        }
        game.setDealer(dealer);
        return dealer;
    }

    public boolean isDeal() {
        this.finance = new Finance(game.getDealer(),game.getPlayers());
        if (finance.isDeal()) {
            return true;
        } else {
            this.finance = null;
            return false;
        }
    }
    public boolean canDeal() {
        return finance.canDeal();
    }

    public void initCard() {
        game.initCard();
    }

    public Card getCard(Player player) {
        return game.getCard(player);
    }
    public void stopGettingCard(Player player) {
        game.stopGettingCard(player);
    }

    public void calculateResult() {
        game.calculateResult();
    }

    public void changeBalance() {
        finance.changeBalance();
    }

    public void roundFinal(){
        game.getDealer().roundFinal();
        for(Player player:game.getPlayers()){
            player.roundFinal();
        }
    }

    public void terminate(){
        game.setDealer(null);
        game.setDecks(null);
        game.setPlayers(null);
        game=null;
        finance=null;
    }
}
