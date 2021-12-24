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

            System.out.println(p.name);
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
        for (Process process : processes) {

            maxBurstTime = Math.max(maxBurstTime, process.updatedBurstTime);

        }
        //System.out.println("maxBurst: "+maxBurstTime);
        if(maxBurstTime>10)
            v2=maxBurstTime/10;
        else v2=1;
    }

    public void calculateFactor()
    {
        calculateV2();
        //System.out.println(v2);
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
        int minFactor=Integer.MAX_VALUE;
        for (Process process : readyQueue) {

            minFactor = Math.min(minFactor, process.factor);
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
            //System.out.println(currProcess.arrivalTime);
            q=(Math.round((float)(40*currProcess.quantumTime)/100));
            //System.out.println(q);
           System.out.println(currProcess.name+" "+currProcess.quantumTime);
            int remQuantum= currProcess.quantumTime; //3
            calculateFactor();
            for (int i = 0; i < currProcess.quantumTime; i++) {
                time++;
                remQuantum--;
                checkTime();
                //System.out.println(currProcess.name+" Remainder: "+remQuantum);
               if(currProcess.updatedBurstTime!=0) {
                   //time++;
                   if (i == q) {
                       //System.out.println(calcMinFactor().arrivalTime);
                       if(calcMinFactor()!=null) {
                          // System.out.println( calcMinFactor().name+" factor: "+calcMinFactor().factor);
                           //System.out.println("break");
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
                   break;
               }

            }
            if(currProcess.updatedBurstTime>0 && remQuantum==0)
            {
                readyQueue.remove(currProcess);
                readyQueue.add(currProcess);
                currProcess.quantumTime=currProcess.quantumTime + 2;
                System.out.println("mmmmmmmmmmmm");
            }

        }


    }
}
