import java.util.ArrayList;
import java.lang.Math;
public class Agat {
    private ArrayList<Process> processes = new ArrayList<>();
    private ArrayList<Process> readyQueue= new ArrayList<>();
    private ArrayList<Process> deadList= new ArrayList<>();
    private float v2;


    public Agat(ArrayList<Process> processes)
    {
        this.processes=processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public void setDeadList(ArrayList<Process> deadList) {
        this.deadList = deadList;
    }

    public void setReadyQueue(ArrayList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public ArrayList<Process> getReadyQueue() {
        return readyQueue;
    }

    public ArrayList<Process> getDeadList() {
        return deadList;
    }

    public void calculateV1()
    {
        for (int i = 0; i < processes.size(); i++) {
            //last arrival time / 10
            double v1;
            if(processes.get(processes.size() - 1).arrivalTime < 10)
            {
                v1=1;
            }
            else {

                 v1= ((float)processes.get(processes.size() - 1).arrivalTime)/10;

            }
            processes.get(i).v1= (int) Math.ceil(processes.get(i).arrivalTime/v1);
        }

    }
    public void calculateV2()
    {
        float maxBurstTime=0;
        for (int i = 0; i < processes.size()-1; i++) {

            maxBurstTime=Math.max(processes.get(i).updatedBurstTime,processes.get(i+1).updatedBurstTime);

        }

        if(maxBurstTime>10)
            v2=maxBurstTime/10;
        else v2=1;
    }

    public void calculateFactor()
    {
        calculateV2();
        for (Process p : processes) {

            p.factor = (10 - p.priorityNumber) + p.v1 + (int) Math.ceil(p.updatedBurstTime / v2);
        }
    }
}
