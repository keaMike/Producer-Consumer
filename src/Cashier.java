public class Cashier implements Runnable {

    private BurgerSystem bS = BurgerSystem.getBsInstance();
    private final Object lock = BurgerSystem.getObjInstance();

    public void deliverBurger() {
        bS.takeBurger();
        System.out.println("Cashier: I just delivered a burger! Slide contains: " + bS.getBurgersReady() + " burgers");
    }

    @Override
    public void run() {
        while(true) {
            synchronized (lock) {
                if (bS.getBurgersReady() == 0) {
                    System.out.println("Cashier: What is taken so long?");
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                deliverBurger();
                System.out.println("Cashier: I'm taking the customer order...");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
