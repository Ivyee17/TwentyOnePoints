import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Background extends JFrame implements ActionListener {
    private GamePanel gamePanel;
    private IGameRoom gameRoom;
    private JMenuBar menuBar;
    private JMenuItem addPlayer;
    private JMenuItem restart;
    private PlayerSetting playerSetting;
    private JOptionPane error;

    public Background(){
        gameRoom = new GameRoom();
        setSize(1366, 850);
        setTitle("BlackJack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        addPlayer = new JMenuItem("增加玩家");
        addPlayer.addActionListener(this);
        menuBar.add(addPlayer);
        restart = new JMenuItem("重新开始");
        restart.addActionListener(this);
        menuBar.add(restart);

        Container contentPane = getContentPane();
        gamePanel = new GamePanel(gameRoom);

        DealerSetting dealerSetting=new DealerSetting(gamePanel);
        gamePanel.add(dealerSetting);
        dealerSetting.setBounds(383, 215, 600, 300);
        dealerSetting.setVisible(true);



        contentPane.add(gamePanel);

        playerSetting = new PlayerSetting(gamePanel, gameRoom);
        gamePanel.add(playerSetting);
        playerSetting.setBounds(383, 215, 600, 300);
        playerSetting.setVisible(false);
        error=new JOptionPane();
    }

    public void actionPerformed(ActionEvent evt){
        boolean isFinish=true;
        for(Player player:gameRoom.getPlayers()){
            if(player.getStatus()==Status.INPROGRESS) isFinish=false;
        }
        if(!isFinish){
            error.showMessageDialog(this, "游戏尚未结束");
            return;
        }
        else{
            if(evt.getSource() == addPlayer){
                if(gameRoom.getPlayers().size()==7){
                    error.showMessageDialog(this, "最多七人，不可添加");
                }
                else {
                    playerSetting.setVisible(true);
                }
            }
            else if(evt.getSource() == restart) {
                if (gameRoom.getPlayers().size() == 0) {
                    error.showMessageDialog(this, "你还没有添加任何玩家");
                }
                else {gamePanel.restart();}
            }
        }


    }

    public static void main(String[] args){
        Background back = new Background();
    }
}
