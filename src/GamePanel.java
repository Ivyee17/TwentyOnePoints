import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

//游戏面板
public class GamePanel extends JPanel implements ActionListener{
    private JLabel[] name;
    private JLabel[] money;
    private JTextField[] stake;
    private JLabel decksNumber;
    private JOptionPane error;
    private Rank rank;
    private IGameRoom gameRoom;
    private int order;
    private boolean[] passed;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private JButton yaoPai;
    private JButton tingPai;
    private JButton[] xiuGai;
    private JButton startButton;
    private ImageIcon icon;
    private Image backPicture;
    private int[][] position;


    public GamePanel(IGameRoom gameRoom) {
        this.setLayout(null);

        rank = new Rank(this);
        rank.setVisible(false);
        this.gameRoom = gameRoom;

        name = new JLabel[7];
        for (int i = 0; i < 7; i++)
            name[i] = new JLabel();

        money = new JLabel[8];
        for (int i = 0; i < 8; i++)
            money[i] = new JLabel();

        stake = new JTextField[7];
        for (int i = 0; i < 7; i++)
            stake[i] = new JTextField();

        order = 0;
        passed = new boolean[]{false, false, false, false, false, false, false};

        icon = new ImageIcon(getClass().getResource("start.jpg"));
        icon.setImage(icon.getImage().getScaledInstance(200, 60, Image.SCALE_DEFAULT));
        startButton = new JButton(icon);
        backPicture = Toolkit.getDefaultToolkit().getImage(getClass().getResource("timg2.jpg"));
        position = new int[8][2];
        position[0][0] = 90;
        position[0][1] = 230;
        position[1][0] = 255;
        position[1][1] = 350;
        position[2][0] = 450;
        position[2][1] = 425;
        position[3][0] = 650;
        position[3][1] = 470;
        position[4][0] = 850;
        position[4][1] = 425;
        position[5][0] = 1035;
        position[5][1] = 350;
        position[6][0] = 1195;
        position[6][1] = 230;
        position[7][0] = 650;
        position[7][1] = 100;

        this.add(startButton);
        startButton.setBounds(1100, 600, 200, 60);
        startButton.addActionListener(this);
        startButton.setVisible(false);


        icon1 = new ImageIcon(getClass().getResource("yaopai.jpg"));
        icon1.setImage(icon1.getImage().getScaledInstance(50, 30, Image.SCALE_DEFAULT));
        icon2 = new ImageIcon(getClass().getResource("tingpai.jpg"));
        icon2.setImage(icon2.getImage().getScaledInstance(50, 30, Image.SCALE_DEFAULT));

        yaoPai = new JButton();
        tingPai = new JButton();
        xiuGai = new JButton[8];
        for (int i = 0; i < 8; i++) {

            xiuGai[i] = new JButton("确认赌注");
            JButton xg = xiuGai[i];
            add(xg);
            xg.setVisible(false);
            xg.addActionListener(this);
            xg.setBounds(position[i][0], position[i][1] + 250, 100, 30);
        }

        yaoPai.setIcon(icon1);
        tingPai.setIcon(icon2);

        add(yaoPai);
        yaoPai.setVisible(false);
        yaoPai.addActionListener(this);

        add(tingPai);
        tingPai.setVisible(false);
        tingPai.addActionListener(this);

        error = new JOptionPane();
    }

    /**
     * Override by adding pictures to the card if necessary.
     * Not all cards need the image resources, so it will save lots of resources by adding pictures only if it's necessary.
     * @param comp the card or other no-override elements.
     * @return the added component, same as before overriding.
     */
    @Override
    public Component add(Component comp) {
        if(comp instanceof Card){
            Card c=(Card) comp;
            ImageIcon cardPicture=new ImageIcon(getClass().getResource( c.getSuit().getName() + String.valueOf(c.getFaceValue()) + ".jpg"));
            cardPicture.setImage(cardPicture.getImage().getScaledInstance(c.getWidth(),c.getHeight(), Image.SCALE_DEFAULT));
            c.setIcon(cardPicture);
        }
        return super.add(comp);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backPicture, 0, 0, 1366, 850, this);
        if(gameRoom.getDealer()!=null) {
            money[7].setText("money: " + String.valueOf(gameRoom.getDealer().getMoney()));
            this.add(money[7]);
            money[7].setBounds(position[7][0], position[7][1] + 150, 100, 20);
            money[7].setForeground(Color.WHITE);
            money[7].setVisible(true);
        }
    }

    /**
     * Add a card.
     * @param x the players' id
     * @param y the order of cards
     */
    public void cardAdded(int x, int y){
        Card card = gameRoom.getPlayers().get(x).getCardList().get(y);
        this.add(card);
        card.setBounds(position[x][0], position[x][1] - y * 30, 70, 120);
        card.setVisible(true);
    }

    /**
     * Get all players.
     * @return players
     */
    public List<Player> getPlayer(){
        return gameRoom.getPlayers();
    }

    /**
     * Restart the game by calling this function.
     */
    public void restart(){
        for(int i = 0; i < gameRoom.getDealer().getCardList().size(); i++)
            gameRoom.getDealer().getCardList().get(i).setVisible(false);
        money[7].setVisible(true);
        for(int i = 0; i < gameRoom.getPlayers().size(); i++){
            for(int j = 0; j < gameRoom.getPlayers().get(i).getCardList().size(); j++){
                gameRoom.getPlayers().get(i).getCardList().get(j).setVisible(false);
            }
        }
        gameRoom.roundFinal();

        order = 0;

        for(int i=0;i<gameRoom.getPlayers().size();i++) {
            xiuGai[i].setVisible(true);
            stake[i].setEnabled(true);
        }

        startButton.setVisible(true);
    }

    /**
     * Add players' info to the board.
     * @param i the players' id.
     */
    public void addPlayerInformation(int i){
        name[i].setText("name: "+ gameRoom.getPlayers().get(i).getName());
        this.add(name[i]);
        name[i].setBounds(position[i][0], position[i][1] + 150, 100, 20);
        name[i].setForeground(Color.WHITE);
        name[i].setVisible(true);

        money[i].setText("money: " + String.valueOf(gameRoom.getPlayers().get(i).getMoney()));
        this.add(money[i]);
        money[i].setBounds(position[i][0], position[i][1] + 180, 100, 20);
        money[i].setForeground(Color.WHITE);
        money[i].setVisible(true);

        xiuGai[i].setVisible(true);

        stake[i].setText(String.valueOf(gameRoom.getPlayers().get(i).getStake()));
        this.add(stake[i]);
        stake[i].setBounds(position[i][0], position[i][1] + 210, 100, 20);
        stake[i].setEnabled(true);
        stake[i].setVisible(true);

        startButton.setVisible(true);
    }

    /**
     * Start the game.
     */
    public void start(){
        if(gameRoom.isDeal()) {
            decksNumber.setVisible(true);
            //xiuGai[7].setVisible(false);
            order = 0;
            yaoPai.setBounds(position[order][0], position[order][1] + 250, 50, 30);
            yaoPai.setVisible(true);

            tingPai.setBounds(position[order][0] + 60, position[order][1] + 250, 50, 30);
            tingPai.setVisible(true);

            for (int i = 0; i < gameRoom.getPlayers().size(); i++) {
                name[i].setText("name: " + gameRoom.getPlayers().get(i).getName());
                this.add(name[i]);
                name[i].setBounds(position[i][0], position[i][1] + 150, 100, 20);
                name[i].setForeground(Color.WHITE);
                name[i].setVisible(true);

                money[i].setText("money: " + String.valueOf(gameRoom.getPlayers().get(i).getMoney()));
                this.add(money[i]);
                money[i].setBounds(position[i][0], position[i][1] + 180, 100, 20);
                money[i].setForeground(Color.WHITE);
                money[i].setVisible(true);
            }

            money[7].setText("money: " + String.valueOf(gameRoom.getDealer().getMoney()));
            this.add(money[7]);
            money[7].setBounds(position[7][0], position[7][1] + 150, 100, 20);
            money[7].setForeground(Color.WHITE);
            money[7].setVisible(true);

            gameRoom.initCard();


            for (int i = 0; i < gameRoom.getPlayers().size(); i++) {
                for (int j = 0; j < gameRoom.getPlayers().get(i).getCardList().size(); j++)
                    cardAdded(i, j);
            }


            List<Card> cardList = gameRoom.getDealer().getCardList();
            for (int i = 0; i < cardList.size(); i++) {
                Card card = cardList.get(i);
                if (i == 1) {
                    ImageIcon cardBgPicture = new ImageIcon(getClass().getResource("backside.jpg"));

                    cardBgPicture.setImage(cardBgPicture.getImage().getScaledInstance(70, 120, Image.SCALE_DEFAULT));
                    card.setIcon(cardBgPicture);
                }
                this.add(card);
                card.setBounds(position[7][0], position[7][1] - i * 30, 70, 120);
                card.setVisible(true);
            }
        }
        else{
            restart();
            error.showMessageDialog(this, "本轮赌约不成立，请依次检查每位玩家的赌金设置是否符合要求。");
        }
    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == startButton) {
            startButton.setVisible(false);
            this.start();
        }
        else if(Arrays.asList(xiuGai).contains(evt.getSource())){
            int clickElement=-1;
            for(int i=0;i<8;i++){
                if(evt.getSource()==xiuGai[i]) clickElement=i;
            }
            if(clickElement==-1) throw new ArrayStoreException("Can not find elements of xiuGai");

            try {

                if (Integer.parseInt(stake[clickElement].getText()) <= 0)
                    error.showMessageDialog(this, "赌注应大于0，请重新输入");
                else {
                    gameRoom.getPlayers().get(clickElement).setStake(Integer.parseInt(stake[clickElement].getText()));
                    stake[clickElement].setEnabled(false);
                    xiuGai[clickElement].setVisible(false);
                    order++;


                }
            }
            catch(Exception e){
                error.showMessageDialog(this, "赌注设置错误，请重新输入");
                return;
            }
        }
        else
        {
            if(evt.getSource() == yaoPai){
                gameRoom.getCard(gameRoom.getPlayers().get(order));
                int x = gameRoom.getPlayers().get(order).getCardList().size();
                cardAdded(order, x - 1);
            }
            else if(evt.getSource() == tingPai){
                passed[order] = true;
                gameRoom.stopGettingCard(gameRoom.getPlayers().get(order));
                order++;
                if(order == gameRoom.getPlayers().size()){
                    gameRoom.calculateResult();
                    gameRoom.changeBalance();
                    for(int p = 0; p < gameRoom.getPlayers().size(); p++){
                        money[p].setText("money: "+String.valueOf(gameRoom.getPlayers().get(p).getMoney()));
                    }

                    money[7].setText("money: "+String.valueOf(gameRoom.getDealer().getMoney()));

                    for(int i = 0; i < gameRoom.getDealer().getCardList().size(); i++)
                    {
                        Card card = gameRoom.getDealer().getCardList().get(i);

                        this.add(card);
                        card.setBounds(position[7][0], position[7][1] - i * 30, 70, 120);
                        if(i >= 1)
                        {
                            ImageIcon cardPicture = new ImageIcon(getClass().getResource(card.getSuit().getName() + String.valueOf(card.getFaceValue()) + ".jpg"));
                            cardPicture.setImage(cardPicture.getImage().getScaledInstance(70, 120, Image.SCALE_DEFAULT));
                            card.setIcon(cardPicture);
                        }
                        card.setVisible(true);
                    }

                    yaoPai.setVisible(false);
                    tingPai.setVisible(false);


                    rank.setVisible(true);
                    rank.setRank();
                    if(!gameRoom.canDeal()){
                        error.showMessageDialog(this,"游戏结束，庄家或任一玩家已输光，请退出游戏。");
                    }
                }
                else
                {
                    yaoPai.setBounds(position[order][0], position[order][1] + 250, 50, 30);
                    yaoPai.setVisible(true);

                    tingPai.setBounds(position[order][0] + 60, position[order][1] + 250, 50, 30);
                    tingPai.setVisible(true);
                }
            }

        }
    }

    /**
     * Set dealer infomation, which should be called after {@link DealerSetting} is invisible.
     * After done, main board will show the number of decks and the money of the dealer.
     * @param deckNumber the number of decks
     * @param dealerMoney the money of the dealer
     */
    public void setDealerInfo(int deckNumber, int dealerMoney ) {
        gameRoom.initDealer(deckNumber, dealerMoney);

        decksNumber = new JLabel();
        decksNumber.setText("牌数：" + String.valueOf(gameRoom.getDecks().size()));
        this.add(decksNumber);
        decksNumber.setBounds(position[7][0], position[7][1] + 100, 100, 20);
        decksNumber.setForeground(Color.WHITE);
    }
}
