public class Aisle {
    public Passenger passenger; //The passenger in this aisle
    public int t; //The time until the aisle is cleared for this row

    public Aisle(Passenger passenger, int t) {
        this.passenger = passenger;
        this.t = t;
    }

    public Aisle() {
        this.passenger = null;
        this.t = 0;
    }
    public int getTime(){
        return this.t;
    }
    public void setTime(int time){
        this.t = time;
    }
    public void setPassenger(Passenger pass){
        this.passenger = pass;
    }
    public Passenger getPassenger(){
        return passenger;
    }
}
