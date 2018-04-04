public class Counter {
    public synchronized void addCounter(long res){
        Runner.list.add(res);
    }
}
