public class priorityQueue {

    Passenger[] heap;
    int size;
    int key;
    int aisleLen;
    public priorityQueue(Passenger[] p, int n, int k){
        heap = p; //read in data from file
        aisleLen = n;
        key = k; //determines what priority to use
        size = heap.length -1;
        buildHeap();
    }
    public void buildHeap(){ //modify array so it is a heap
        for (int i = size/2; i >=1; i--){
            trickleDown(i);
        }
    }

    //puts the node in the correct spot
    public void trickleDown(int index){
        int smallestNode = index; //assume the original node is the smallest
        int leftChild = 2*index;
        int rightChild = 2*index+1;
        //smallestNode becomes the left child if the left child is smaller
        if (leftChild < size && getPriorityType(heap[leftChild], key) < getPriorityType(heap[smallestNode],key)){
            smallestNode = leftChild;
        }
        //smallestNode becomes the right child if the right child is smaller
        if (rightChild < size && getPriorityType(heap[rightChild], key) < getPriorityType(heap[smallestNode], key)){
            smallestNode = rightChild;
        }
        //if right or left child is smaller than index, swap them and recursive call until index is smaller than the children
        if (smallestNode != index){
            swap(index, smallestNode);
            trickleDown(smallestNode);
        }
    }
    public Passenger extractMin(){
        if (size == 0) {
            return null;
        }
        Passenger extract = heap[1];
        heap[1] = heap[size]; //replace the first element with the last element
        size--;
        trickleDown(1); //put the node in the correct spot to maintain heap structure
        return extract;
    }
    private void swap(int i, int j) { //swap 2 nodes
        Passenger temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void display(){
        for (int i = 1; i <= size; i++){
            System.out.print("|");
            heap[i].display();
            System.out.print("|");
        }
    }
    public boolean isEmpty(){
        return size == 0;
    }

    //returns a priority value based on the key given and the passenger information
    public int getPriorityType(Passenger passenger, int key){
        if (key == 1){ //first come, first served
            return passenger.passengerNumber;
        }
        else if (key == 2){ //slowest passengers first
            return 5- passenger.slowness;
        }
        else if (key == 3){ //fastest passengers first
            return passenger.slowness;
        }
        else if (key == 4){ //load back to front
            return aisleLen+1-passenger.row;
        }
        else if (key == 5){//load aisle seats first
            if (passenger.isWindowSeat()){
                return 2;
            }
            else{
                return 1;
            }
        }
        else return 0;
    }
}
