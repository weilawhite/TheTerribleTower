import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameClient extends JFrame {
    static int SWidth = 400;
    static int SHeight = 600;
    int mazeWidth=7;
    int mazeHeight=7;
    JLabel status, levelMap, mainMessage;
    String messageTemp="<html><body> ";
    PlayerState playerState = PlayerState.MOVE;
    int[][] map;

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, SWidth, SHeight);
        super.paint(g);
    }


    @Override
    public void paintComponents(Graphics g) {

        super.paintComponents(g);
    }

    public GameClient() {
        JFrame frame = new JFrame();
        JPanel main = new JPanel();
        JPanel second = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SWidth, SHeight);

        main.setBackground(Color.gray);
        //main.setBounds(0, 0, 600, 600);
        second.setBackground(Color.orange);
        //second.setBounds(0, 0, 600, 200);

        Player player = new Player();
        second.setVisible(true);

        status = new JLabel();
        levelMap = new JLabel();
        mainMessage = new JLabel();

        statusTextRefresh(player);
        status.setFont(new Font(null, Font.BOLD, 20));
        levelMap.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        mainMessage.setFont(new Font(null, Font.BOLD, 15));

        main.add(mainMessage);
        second.add(status);
        second.add(levelMap);

        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jSplitPane.setLeftComponent(main);
        jSplitPane.setRightComponent(second);

        jSplitPane.setDividerLocation(400); //分割線的位置  也就是初始位置
        jSplitPane.setOneTouchExpandable(false); //是否可展開或收起，在這裡沒用
        jSplitPane.setDividerSize(2);//設定分割線的寬度 畫素為單位
        jSplitPane.setEnabled(false); //設定分割線不可拖動！！
        frame.add(jSplitPane);  //加入到面板中就好了
        frame.setResizable(false);

        frame.setVisible(true);
        map = Stage1.getMaze().clone();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                frame.repaint();
                if (playerState == PlayerState.MOVE) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP: //case 0
                            System.out.println("UP");
                            if (checkThisMove(-1, 0, player.getPosX(), player.getPosY()))
                                player.move(0);
                            break;
                        case KeyEvent.VK_DOWN: //case 1
                            System.out.println("DOWN");
                            if (checkThisMove(1, 0, player.getPosX(), player.getPosY()))
                                player.move(1);
                            break;
                        case KeyEvent.VK_LEFT: //case 2
                            System.out.println("LEFT");
                            if (checkThisMove(0, -1, player.getPosX(), player.getPosY()))
                                player.move(2);
                            break;
                        case KeyEvent.VK_RIGHT: //case 3
                            System.out.println("RIGHT");
                            if (checkThisMove(0, 1, player.getPosX(), player.getPosY()))
                                player.move(3);
                            break;/*
                        case KeyEvent.VK_ENTER:
                            System.out.println("ENTER");
                            System.out.println(player.getStatue());
                            break;*/
                        default:
                            return;
                    }
                    System.out.println("X:" + player.getPosX() + ",Y:" + player.getPosY());
                    playerState = PlayerState.EVENT;
                    drawMap(player.getPosX(), player.getPosY());
                }
                else if (playerState == PlayerState.EVENT) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:
                            //System.out.println("ROLL");
                            int roll = Tool.dice();
                            System.out.println("ROLL:" + roll);
                            String message = MapEvent.NE(roll, player);

                            messageTemp=messageTemp+"<br>"+message;
                            mainMessage.setText(messageTemp);

                            System.out.println(message);
                            playerState = PlayerState.MOVE;

                    }
                }
                statusTextRefresh(player);
                repaint();
            }
        });

    }

    private Boolean checkThisMove(int xShift, int yShift, int xPos, int yPos) {

        if (xPos + xShift < 0 || xPos + xShift > mazeWidth-1) return false;
        if (yPos + yShift < 0 || yPos + yShift > mazeHeight-1) return false;
        if (map[xPos + xShift][yPos + yShift] == 0) return false;
        return true;
    }

    private void statusTextRefresh(Player player) {
        status.setText(player.getStatue());
        String hp, atk, def;
        hp = String.valueOf(player.getHp());
        atk = String.valueOf(player.getAtk());
        def = String.valueOf(player.getDef());
        //status.setText("<html><body>HP:"+hp+"<br>ATK:"+atk+"<br>DEF:"+def+"<body></html>");
    }

    void drawMap(int posX, int posY) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        for (int i = 0; i < mazeWidth; i++) {
            for (int j = 0; j < mazeHeight; j++) {
                if (i == posX && j == posY) {
                    System.out.print("＊");
                    stringBuilder.append("＊");
                    continue;
                }
                if (i % 2 == 1 && j % 2 == 1) {
                    System.out.print("　");
                    stringBuilder.append("　");
                    /*
                    System.out.print("■");
                    stringBuilder.append("■");*/
                } else if (i % 2 == 1 || j % 2 == 1) {
                    if (map[i][j] == 0) {
                        System.out.print("　");
                        stringBuilder.append("　");/*
                        System.out.print("■");
                        stringBuilder.append("■");*/
                    } else if (i % 2 == 1) {
                        System.out.print("｜");
                        stringBuilder.append("｜");
                    } else {
                        System.out.print("－");
                        stringBuilder.append("－");
                    }
                } else {
                    System.out.print("□");
                    stringBuilder.append("□");
                }

            }
            System.out.println();
            stringBuilder.append("<br>");
        }
        stringBuilder.append("<body></html>");
        levelMap.setText(stringBuilder.toString());

    }
}
