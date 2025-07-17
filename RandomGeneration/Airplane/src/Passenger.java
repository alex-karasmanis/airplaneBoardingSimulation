
public class Passenger {
    int row;
    char position;
    int slowness;
   int passengerNumber; //Passenger number will be assigned based on the order they arrive
    public void setRow(int r){
        this.row = r;
    }
    public int getRow(){
        return this.row;
    }
    public void setPosition(char p){
        this.position = p;
    }
    public void setSlowness(int s){
        this.slowness = s;
    }
    public void setNum(int n){
        this.passengerNumber = n;
    }
    public int getSlowness(){
        return this.slowness;
    }
    public char getPosition(){
        return this.position;
    }

    public boolean isWindowSeat(){
        if (position == 'A' || position == 'D'){
            return true;
        }
        else if (position == 'B' ||position == 'C'){
            return false;
        }
        return false;
    }
    public void display(){
        System.out.print(row + ", " + position + ", " + slowness);
    }
    public void displayNum(){
        System.out.print(passengerNumber + ", ");
    }

}
