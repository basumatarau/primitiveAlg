package packageName;

public class HanoiTowers {
    static int nDiscs = 3;

    static void doTowers(int topDiscNum, char from, char inter, char to){
        if(topDiscNum==1){
            System.out.println("Disc 1 from " + from + " to " + to);
        }else{
            doTowers(topDiscNum - 1, from, to, inter);
            System.out.println("Disc " + topDiscNum + " from " + from + " to " + to);
            doTowers(topDiscNum - 1, inter, from, to);
        }
    }

    public static void main(String[] args) {
        doTowers(nDiscs, 'A', 'B', 'C');
    }
}
