package packageName.BinTree;

public class SomeDataHolder implements Comparable<SomeDataHolder>{
    private int num;

    SomeDataHolder(int n){
        this.num = n;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(SomeDataHolder o) {
        return Integer.compare(num, o.num);
    }
}
