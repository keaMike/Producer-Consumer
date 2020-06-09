public class BurgerSystem {

    private static BurgerSystem bS;
    private static Object lock;

    private final int maxBurgers = 8;
    private int burgersReady;

    public void start() {
        Thread cook = new Thread(new Cook());
        Thread cashier = new Thread(new Cashier());

        try {
            cook.start();
            cashier.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBurgersReady() {
        return burgersReady;
    }

    public int getMaxBurgers() {
        return maxBurgers;
    }

    public void addBurger() {
        burgersReady++;
    }

    public void takeBurger() {
        burgersReady--;
    }

    public static BurgerSystem getBsInstance() {
        if(bS == null) bS = new BurgerSystem();
        return bS;
    }

    public static Object getObjInstance() {
        if(lock == null) lock = new Object();
        return lock;
    }
}
