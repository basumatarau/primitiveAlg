package simpleAlgorithms.binTreeLeafs;

public class SomeDataHolder implements Comparable<SomeDataHolder>{
    private int num;

    public SomeDataHolder(int n){
        this.num = n;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(SomeDataHolder o) {
        return Integer.compare(num, o.num);
    }

    @Override
    public String toString() {
        return String.format("%-2d", num);
    }
}
