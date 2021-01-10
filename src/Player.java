public class Player {
    int hp = 100;
    int atk = 20;
    int def = 20;
    int posX = 0, posY = 0;

    String getStatue() {
        String returnString = ("<html><body>HP:" + hp + "<br>ATK:" + atk + "<br>DEF:" + def + "<body></html>");

        return returnString;
    }

    public void move(int dir) {
        switch (dir) {
            case 0:
                posX = posX - 2;
                break;
            case 1:
                posX = posX + 2;
                break;
            case 2:
                posY=posY-2;
                break;
            case 3:
                posY=posY+2;
                break;
        }


    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }
}
