import javax.swing.*;
import java.awt.*;

public class Rank extends JFrame {
    private ImageIcon backPicture;
    private JLabel back;
    private GamePanel gamePanel;
    private JLabel nameLabel;
    private JLabel moneyLabel;
    private JLabel resultLabel;
    private JLabel[] name;
    private JLabel[] money;
    private JLabel[] result;

    /**
     * Initial the rank board.
     * @param gamePanel the main panel of the game.
     */
    public Rank(GamePanel gamePanel){
        setSize(1366, 730);
        setTitle("Rank");
        setVisible(true);

        backPicture = new ImageIcon(getClass().getResource("xuanze.jpg"));
        backPicture.setImage(backPicture.getImage().getScaledInstance(1366, 730, Image.SCALE_DEFAULT));
        back = new JLabel();
        back.setIcon(backPicture);
        this.add(back);
        back.setBounds(0, 0, 1366, 730);
        back.setVisible(true);

        this.gamePanel = gamePanel;
        name = new JLabel[7];
        for(int i = 0; i < 7; i++)
            name[i] = new JLabel();

        money = new JLabel[7];
        for(int i = 0; i < 7; i++)
            money[i] = new JLabel();

        result = new JLabel[7];
        for(int i = 0; i < 7; i++)
            result[i] = new JLabel();

        nameLabel = new JLabel("name");
        moneyLabel = new JLabel("money");
        resultLabel = new JLabel("result");
    }

    /**
     * Set the information of each player on the board.
     */
    public void setRank(){
        nameLabel.setBounds(450, 250, 100, 40);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 20));
        back.add(nameLabel);

        moneyLabel.setBounds(550, 250, 100, 40);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("宋体", Font.BOLD, 20));
        back.add(moneyLabel);

        resultLabel.setBounds(650, 250, 500, 40);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("宋体", Font.BOLD, 20));
        back.add(resultLabel);

        for(int i = 0; i < gamePanel.getPlayer().size(); i++){
            name[i].setText(gamePanel.getPlayer().get(i).getName());
            back.add(name[i]);
            name[i].setBounds(450, 300 + i * 50,100, 40);
            name[i].setFont(new Font("宋体", Font.BOLD, 20));
            name[i].setForeground(Color.WHITE);

            money[i].setText(String.valueOf(gamePanel.getPlayer().get(i).getMoney()));
            back.add(money[i]);
            money[i].setBounds(550, 300 + i * 50, 100, 40);
            money[i].setFont(new Font("宋体", Font.BOLD, 20));
            money[i].setForeground(Color.WHITE);

            result[i].setText(gamePanel.getPlayer().get(i).getStatus().toString());
            back.add(result[i]);
            result[i].setBounds(650,300 + i * 50, 500, 40);
            result[i].setFont(new Font("宋体", Font.BOLD, 20));
            result[i].setForeground(Color.WHITE);
        }
    }
}
