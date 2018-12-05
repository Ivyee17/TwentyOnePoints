import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Deck> decks;
    private List<Player> players;
    private Dealer dealer;

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }



    public Game() {
        this.decks = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void initCard() {
        for(int i=0;i<players.size();i++){
            Player player=players.get(i);
            player.setStatus(Status.INPROGRESS);
            getCard(player);
            getCard(player);
        }
        dealer.getCard(decks);
        dealer.getCard(decks);
    }

    public Card getCard(Player player) {
        if(player.getStatus()!=Status.INPROGRESS) return null;
        return player.getCard(decks);
    }

    public void stopGettingCard(Player player) {
        player.stopGettingCard();
    }

    public void calculateResult() {
        for(Player player:players){
            assert(player.getStatus()==Status.FINISH);
        }
        dealer.play(decks);
        for(Player player:players){
            if(player.calPoints()>21){
                player.setStatus(Status.LOST);
            }
            else if(player.calPoints()<=21&&dealer.calPoints()>21){
                if(player.isBlackJack()){
                    player.setStatus(Status.WIN15);
                }
                else{
                    player.setStatus(Status.WIN2);
                }
            }
            // if here, all <21.
            else if(player.calPoints()<dealer.calPoints()){
                player.setStatus(Status.LOST);
            }
            else if(player.calPoints()==dealer.calPoints()){
                player.setStatus(Status.WIN1);// remember dealer must >17
            }
            //  if here ,all<21 and player>dealer
            else{
                if(player.isBlackJack()){
                    player.setStatus(Status.WIN15);
                }
                else{
                    player.setStatus(Status.WIN2);
                }
            }
        }
    }


}