import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * key will select what priority to board the passengers. key 1 is first come
         * key 1 is first-come first served
         * key 2 is slowest passengers first
         * key 3 is fastest passengers first
         * key 4 is load back to front
         * key 5 is load out most section first (B and C)
         */
        int key = 5;
        AirplaneBoarding simulation = new AirplaneBoarding(key);
        
    }
}