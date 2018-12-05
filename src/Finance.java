import java.util.ArrayList;
import java.util.List;

/**
 * All actions interacting with money (or stake) will be dealt in this class.
 */
public class Finance {

    private List<Player> players;
    private Dealer dealer;


    public Finance(Dealer dealer, List<Player> players) {
        this.players=players;
        this.dealer=dealer;
    }

    public boolean isDeal() {
        int totalDealer=dealer.getMoney();
        int totalPlayer=0;
        for(int i=0;i<players.size();i++){
            if(players.get(i).getStake()<=0) return false;
            totalPlayer+=players.get(i).getStake();
        }

        return (totalDealer>=totalPlayer*2);
    }
    public boolean canDeal(){
        if(dealer.getMoney()<=0) return false;
        for(Player player:players){
            if(player.getMoney()<=0) return false;
        }
        return true;
    }

    public void changeBalance() {
        for (Player player : players) {
            if (player.getStatus() == Status.LOST) {
                player.setMoney(player.getMoney() - player.getStake());
                dealer.setMoney(dealer.getMoney() + player.getStake());
            } else if (player.getStatus() == Status.WIN15) {
                player.setMoney(player.getMoney() + player.getStake() * 3 / 2);
                dealer.setMoney(dealer.getMoney() - player.getStake() * 3 / 2);
            } else if (player.getStatus() == Status.WIN2) {
                player.setMoney(player.getMoney() + player.getStake() * 2);
                dealer.setMoney(dealer.getMoney() - player.getStake() * 2);
            }
            // if WIN1, do nothing.
        }
    }
}
