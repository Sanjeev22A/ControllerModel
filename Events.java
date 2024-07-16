public abstract class Events{
    protected long delayTime;
    private long startTime;
    Events(long delayTime){
        this.delayTime=delayTime;
        start();
    }
    public void start(){
        startTime=System.currentTimeMillis()+delayTime;
    }
    boolean ready(){
        return System.currentTimeMillis()>=startTime;
    }
    abstract void action();
}