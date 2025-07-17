import java.util.Random;

public class AirplaneBoarding {
    int totalTime;
    int totalSeats;
    int n;
    Passenger[] passengers;
    Aisle[] aisle;
    int key;

    public AirplaneBoarding(int number, int k) {
        n = number;
        key = k;
        readData();
        boardPassengers();
    }

    public void readData() {
        Random rand = new Random();
        int slowness;
        char[] seatLetters = {'A', 'B', 'C', 'D'};
        totalSeats = 2*2*n;
        passengers = new Passenger[totalSeats + 1];
        aisle = new Aisle[n+1];
        for(int i = 1; i < aisle.length; i++){ //create an aisle for the needed spots in the aisle array
            aisle[i] = new Aisle();
        }
        int index = 1;
        for (int row = 1; row <= n; row++){//loop through number of rows
            for (int i = 0; i < 4; i++){ //loop through the 4 seats at each row
                slowness = rand.nextInt(1,5); //random slowness
                Passenger p = new Passenger();
                p.setRow(row);
                p.setPosition(seatLetters[i]);
                p.setSlowness(slowness);
                passengers[index] = p;
                //put passenger p in array after assigning values
                index++;
            }
        }
        shuffle(passengers); //random permutation
    }
    public void shuffle(Passenger[] passengers){
        Random r = new Random();
        for (int i = 1; i < passengers.length-2; i++) {
            //start at index one to keep [0] null
            int j = r.nextInt(i, passengers.length);
            //swap
            Passenger temp = passengers[i];
            passengers[i] = passengers[j];
            passengers[j] = temp;
        }
    }

    public void boardPassengers() {
        totalTime = 0;
        Passenger currentPassenger;
        boolean flag = false;
        priorityQueue queue = new priorityQueue(passengers, n, key);
        //loop until everyone has sat down. Each iteration represents one second
        while (!aisleIsEmpty() || !queue.isEmpty()) {
            //if a spot is available in aisle then take the first person from queue and put it in aisle
            if (!queue.isEmpty() && aisle[1].passenger == null) {
                currentPassenger = queue.extractMin();
                aisle[1].setPassenger(currentPassenger);
                aisle[1].setTime(calcTime(currentPassenger, 1));
            }
            //displayText(totalTime, queue); //Display the isal array
            //every second we loop check each section of the aisle to see if passengers need to sit or move.
            for (int i = 1; i <= n; i++) {
                if (flag){ //important to skip the iteration if we moved the person up
                    flag = false;
                    continue;
                }
                if (isAtRow(i)) {
                    seatPassengers(i);
                    //if i is equal to n, we are at the end of aisle so there is nowhere to move
                } else if (!isAtRow(i) && i != n) {
                   flag = movePassengers(i);
                }
            }
            totalTime++;
        }
        System.out.println("Passengers all seated. Total time = " + totalTime + " seconds");
    }

    public boolean movePassengers(int i) {
        //if its empty ahead and there is a person behind he is free to move up
        if (aisle[i + 1].getPassenger() == null && aisle[i].getPassenger() != null) {
            //if he is out of time, that means he has reached the next aisle so move him up the array and reset time
            if (aisle[i].getTime() <= 1) {
                aisle[i + 1].setPassenger(aisle[i].getPassenger());
                aisle[i].setPassenger(null);
                aisle[i].setTime(0);
                aisle[i + 1].setTime(calcTime(aisle[i + 1].getPassenger(), i + 1));
                return true;
            } else {
                //if he still has time left to move decrease it by one
                aisle[i].setTime(aisle[i].getTime() - 1);
            }
        }
        return false;
    }

    //print passengers row position and slowness as well as time remaining in the aisle
    public void aisleDisplay() {
        for (int i = 1; i <= n; i++) {
            System.out.print("|");
            if (aisle[i].getPassenger() == null) {
                System.out.print(aisle[i].getPassenger());
            } else {
                aisle[i].getPassenger().display();
            }
            System.out.print(": ");
            System.out.print(aisle[i].getTime());
            System.out.print("|");
        }
    }


    public boolean isAtRow(int i) {
        return aisle[i].getPassenger() != null && aisle[i].getPassenger().getRow() == i;
    }

    public void seatPassengers(int i) {
        //if passenger is out of time, that means he sat down so remove him from the aisle
        if (aisle[i].getTime() == 1) {
            aisle[i].setPassenger(null);
            aisle[i].setTime(0);
        } else {
            //if he still has time left to sit then decrease the time by one
            aisle[i].setTime(aisle[i].getTime() - 1);
        }
    }

    //calculate the time needed for the passenger to move up to the next row or sit down
    public int calcTime(Passenger passenger, int i) {
        int time;
        if (isAtRow(i)) {
            //if the passenger is at the correct row, calculate the time needed to sit
            time = passenger.slowness * 20;
            if (passenger.isWindowSeat()) {
                time += 10;
            }
            //if the passenger needs to move up the aisle, calculate the time needed to move
        } else {
            time = passenger.slowness;
        }
        return time;
    }

    //return true if aisle is empty
    public boolean aisleIsEmpty() {
        boolean flag = true;
        for (int i = 1; i < aisle.length; i++) {
            if (aisle[i].passenger != null) {
                flag = false; //if any spot has a passenger it can't be empty
                break;
            }
        }
        return flag;
    }
    //display each second of the simulation and the contents of the arrays
    public void displayText(int totalTime, priorityQueue queue){
        System.out.println("Simulation Second " + totalTime);
        System.out.println("Aisle array:" );
        aisleDisplay();
        System.out.println();
        System.out.println("Queue array:");
        queue.display();
        System.out.println();
        System.out.println("------------");
    }
    public int getTotalTime(){
        return totalTime;
    }
}
