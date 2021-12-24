import java.util.ArrayList;
import java.lang.Math;
public class Agat {
    private ArrayList<Process> processes = new ArrayList<>();
    private ArrayList<Process> readyQueue= new ArrayList<>();
    private ArrayList<Process> deadList= new ArrayList<>();
    private float v2;
    private int time;


    public Agat(ArrayList<Process> processes)
    {
        this.processes=processes;
        time=0;
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

    public void getDeadList() {
        for (Process p : deadList) {

            System.out.println(p.arrivalTime);
        }
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
            processes.get(i).ceilV1= (int) Math.ceil(processes.get(i).arrivalTime/v1);
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

            p.factor = (10 - p.priorityNumber) + p.ceilV1 + (int) Math.ceil(p.updatedBurstTime / v2);
        }
    }
    public void checkTime()
    {
        for (Process p : processes) {

            if(p.arrivalTime==time)
            {
                readyQueue.add(p);
            }
        }
    }

    public Process calcMinFactor()
    {
        Process p=null;
        int minFactor=0;
        for (int i = 0; i < readyQueue.size()-1; i++) {

            minFactor=Math.min(readyQueue.get(i).factor,readyQueue.get(i+1).factor);
        }

        for (Process p2 : readyQueue) {

            if(p2.factor==minFactor)
                p= p2;
        }
        return p;
    }

    public void scheduleAgat()
    {
        checkTime();
        while (deadList.size()!=processes.size()) {
            int q;
            Process currProcess=readyQueue.get(0);
            System.out.println(currProcess.arrivalTime);
            q=(Math.round((float)(40*currProcess.quantumTime)/100));
            int remQuantum= currProcess.quantumTime;
            calculateFactor();
            for (int i = 0; i < currProcess.quantumTime; i++) {

               if(currProcess.updatedBurstTime!=0) {
                   time++;
                   remQuantum--;
                   checkTime();
                   if (i == q) {
                       //System.out.println(calcMinFactor().arrivalTime);
                       if(calcMinFactor()!=null) {
                           if (currProcess.factor > calcMinFactor().factor) {
                               int indexCurr = readyQueue.indexOf(currProcess);
                               int rep = readyQueue.indexOf(calcMinFactor());
                               readyQueue.set(indexCurr, calcMinFactor());
                               readyQueue.remove(rep);
                               readyQueue.add(currProcess);
                               currProcess.quantumTime = currProcess.quantumTime + remQuantum;
                               break;
                           }
                       }
                   }
                   currProcess.updatedBurstTime--;
               }
               else {
                   currProcess.quantumTime=0;
                   readyQueue.remove(currProcess);
                   deadList.add(currProcess);
               }

            }
            if(currProcess.updatedBurstTime>0)
            {
                readyQueue.remove(currProcess);
                readyQueue.add(currProcess);
                currProcess.quantumTime=currProcess.quantumTime + 2;
            }

        }


    }
}
