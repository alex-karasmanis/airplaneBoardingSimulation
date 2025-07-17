public class Main {
    public static void main(String[] args) {
        /**
         * key will select what priority to board the passengers. key 1 is first come
         * key 1 is first-come first served
         * key 2 is slowest passengers first
         * key 3 is fastest passengers first
         * key 4 is load back to front
         * key 5 is load out most section first (B and C)
         *
         * Number will determine how many rows are in the airplane
         */
        int averagedTime = 0; //Used to Calculate Average. Do not change.
        int key = 1;
        int number = 10;
        for (int i = 0; i < 10; i++){ //loop 10 time for 10 simulations
            AirplaneBoarding simulation = new AirplaneBoarding(number, key);
            averagedTime += simulation.getTotalTime();
        }
        averagedTime = averagedTime / 10;
        System.out.println("Average time over "+ number + " simulations is " + averagedTime + "seconds");
    }
}