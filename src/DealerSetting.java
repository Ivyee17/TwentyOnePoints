import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealerSetting extends JPanel implements ActionListener {
    private int deckNumber;
    private int money;
    private Image backPicture;
    private JTextField deckNumberText;
    private JTextField moneyText;
    private JLabel deckNumberLabel;
    private JLabel moneyLabel;
    private JButton button;
    private GamePanel gamePanel;
    private JOptionPane error;

    public DealerSetting(GamePanel gamePanel) {
        this.setLayout(null);
        this.gamePanel = gamePanel;
        deckNumberLabel = new JLabel("牌数：");
        moneyLabel = new JLabel("庄家赌金：");
        deckNumberText = new JTextField();
        moneyText = new JTextField();
        button = new JButton("提交");

        backPicture = Toolkit.getDefaultToolkit().getImage(getClass().getResource("xuanze.jpg"));

        this.add(deckNumberLabel);
        deckNumberLabel.setBounds(210, 120, 80, 20);

        this.add(deckNumberText);
        deckNumberText.setBounds(300, 120, 50, 20);

        this.add(moneyLabel);
        moneyLabel.setBounds(210, 150, 80, 20);

        this.add(moneyText);
        moneyText.setBounds(300, 150, 50, 20);

        this.add(button);
        button.setBounds(250, 200, 100, 50);
        button.addActionListener(this);

        error = new JOptionPane();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backPicture, 0, 0, 600, 300, this);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == button) {
            try {
                deckNumber = Integer.parseInt(deckNumberText.getText());
                money = Integer.parseInt(moneyText.getText());
                if (deckNumber <= 0 || money <= 0) throw new IllegalArgumentException("para error");
            } catch (Exception e) {
                error.showMessageDialog(this, "输入有误，请重新输入");
                deckNumberText.setText("");
                moneyText.setText("");
                return;
            }
            gamePanel.setDealerInfo(getDeckNumber(), getMoney());
            this.setVisible(false);

        }
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public int getMoney() {
        return money;
    }
}
