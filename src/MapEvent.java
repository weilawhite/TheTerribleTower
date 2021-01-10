public class MapEvent {
    static String NE(int roll, Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(exploreWords()+"<br>");
        int plus;
        if (roll < 30) {
            plus = 1 + roll / 10;
            stringBuilder.append("找到了一把武器，你的攻擊力提升了" + plus + "點");
            player.atk = player.atk + plus;
        } else if (roll < 60) {
            plus = 1 + roll / 30;
            stringBuilder.append("找到了一件護甲，你的防禦力提升了" + plus + "點");
            player.def = player.def + plus;
        } else if (roll < 80) {
            plus = roll - 60;
            stringBuilder.append("找到了一個恢復藥水，你的生命力提升了" + plus + "點");
            player.hp = player.hp + plus;
        } else if(roll<90){
            plus = roll / 2;
            stringBuilder.append("你被敵人偷襲了，受到了" + plus + "點的傷害");
            player.hp = player.hp - plus;
        }else {
            stringBuilder.append("你沒有找到任何東西");
        }
        stringBuilder.append("<br>");
        return stringBuilder.toString();
    }

    static String exploreWords() {
        StringBuilder stringBuilder = new StringBuilder();
        String temp = null;
        switch (Tool.dice() % 5) {
            case 0:
                temp = "你發現遠處有輕微的聲響，";
                break;
            case 1:
                temp = "這個房間內似乎某處藏著一些東西，";
                break;
            case 2:
                temp = "你的經驗告訴你，這個房間不太單純，";
                break;
            case 3:
                temp = "這個房間亂糟糟的，也許藏著什麼？";
                break;
            default:
                temp = "你在房間內發現了其他生物待過的痕跡，";
                break;
        }
        stringBuilder.append(temp+"<br>");
        switch (Tool.dice() % 5) {
            case 0:
                temp = "你點亮了火把決定一探究竟，";
                break;
            case 1:
                temp = "你決定沿著牆壁的邊緣，尋找未知的事物，";
                break;
            case 2:
                temp = "你開始在幾個老舊的箱子中翻找，";
                break;
            case 3:
                temp = "仔細搜查整個房間應該是不錯的選擇，";
                break;
            default:
                temp = "你憑著直覺在房間裡面摸索，";
                break;
        }
        stringBuilder.append(temp);
        return stringBuilder.toString();

    }


}
