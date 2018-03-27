public class Music {
    private String name;
    private int amount;
    private boolean isLoad;

    public Music(String name, int amount, boolean isLoad) {
        this.name = name;
        this.amount = amount;
        this.isLoad = isLoad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
