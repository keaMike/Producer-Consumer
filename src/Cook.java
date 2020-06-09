public class Cook implements Runnable {

    private BurgerSystem bS = BurgerSystem.getBsInstance();
    private final Object lock = BurgerSystem.getObjInstance();

    public void cookBurger() {
        bS.addBurger();
        System.out.println("Cook: Burger coming up... Slide contains: " + bS.getBurgersReady() + " burgers");
    }

    @Override
    public void run() {
        while(true) {
            synchronized (lock) {
                if (bS.getBurgersReady() == bS.getMaxBurgers()) {
                    System.out.println("Cook: Why are you so slow!");
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                cookBurger();
                System.out.println("Cook: I'm preparing a burger...");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
