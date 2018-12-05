import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSetting extends JPanel implements ActionListener {
    private int playerNumber;
    private GamePanel gamePanel;
    private IGameRoom gameRoom;
    private String name;
    private int money;
    private Image backPicture;
    private JTextField nameText;
    private JTextField moneyText;
    private JLabel nameLabel;
    private JLabel moneyLabel;
    private JButton button;
    private JOptionPane error;

    public PlayerSetting(GamePanel gamePanel, IGameRoom gameRoom) {
        this.setLayout(null);
        this.gameRoom = gameRoom;
        playerNumber = 0;
        this.gamePanel = gamePanel;
        nameLabel = new JLabel("姓名：");
        moneyLabel = new JLabel("赌金：");
        nameText = new JTextField();
        moneyText = new JTextField();
        button = new JButton("提交");

        backPicture = Toolkit.getDefaultToolkit().getImage(getClass().getResource("xuanze.jpg"));

        this.add(nameLabel);
        nameLabel.setBounds(250, 100, 40, 20);

        this.add(nameText);
        nameText.setBounds(300, 100, 50, 20);

        this.add(moneyLabel);
        moneyLabel.setBounds(250, 150, 40, 20);

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
            name = nameText.getText();

            try {
                money = Integer.parseInt(moneyText.getText());
                if (money <= 0) throw new IllegalArgumentException("para error");
            } catch (Exception e) {
                error.showMessageDialog(this, "输入有误，请重新输入");
                nameText.setText("");
                moneyText.setText("");
                return;
            }
            gameRoom.initPlayer(name, money);
            nameText.setText("");
            moneyText.setText("");
            gamePanel.addPlayerInformation(playerNumber);
            playerNumber++;

            this.setVisible(false);


        }
    }

    public String getName() {
        return this.name;
    }
}
