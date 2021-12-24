import java.util.ArrayList;

public class Process {
    public String name;
    public String color;
    public int arrivalTime;
    public int burstTime;
    public int updatedBurstTime;
    public int priorityNumber;
    public int quantumTime;

    //n
    public int roundedQuantum;
    public int waitingTime;
    public int turnAroundTime;

    //ceil Arrival time/v1
    public int ceilV1;
    public int factor;

    //n
    public ArrayList<Integer> historyQt;
    public ArrayList<Integer> historyFactor;

    public Process() {

    }

    public Process(String name, String color, int arrivalTime, int burstTime, int priorityNumber, int quantumTime) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.quantumTime = quantumTime;
        updatedBurstTime=burstTime;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setArrivalTime(int arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
//    public void setBurstTime(int burstTime) {
//        this.burstTime = burstTime;
//    }
//
//    public void setQuantumTime(int quantumTime) {
//        this.quantumTime = quantumTime;
//    }
//
//    public void setWaitingTime(int waitingTime) {this.waitingTime = waitingTime;}
//
//    public void setturnAroundTime(int turnAroundTime) {this.turnAroundTime = turnAroundTime;}
//
//    public void setPriorityNumber(int priorityNumber) {
//        this.priorityNumber = priorityNumber;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public int getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public int getBurstTime() {
//        return burstTime;
//    }
//
//    public int getPriorityNumber() {
//        return priorityNumber;
//    }
//
//    public int getQuantumTime() {
//        return quantumTime; }
//
//    public int getTurnAroundTime() { return turnAroundTime; }
//
//    public int getWaitingTime() { return waitingTime; }

}
