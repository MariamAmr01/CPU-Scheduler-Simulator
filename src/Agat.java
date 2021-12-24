import java.util.ArrayList;
import java.lang.Math;
import javafx.util.Pair;

public class Agat {
    private ArrayList<Process> processes = new ArrayList<>();
    private ArrayList<Process> readyQueue= new ArrayList<>();
    private ArrayList<Process> deadList= new ArrayList<>();
    private ArrayList<Pair<Process,Integer>> waiting= new ArrayList<>();
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

        if(maxBurstTime>10)
            v2=maxBurstTime/10;
        else v2=1;
    }

    public void calculateFactor()
    {
        calculateV2();

        for (Process p : processes) {

            p.factor = (10 - p.priorityNumber) + p.ceilV1 + (int) Math.ceil(p.updatedBurstTime / v2);
            p.historyFactor.add(p.factor);
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
    public void printHistoryFactor()
    {
        for (Process process: processes) {
            System.out.println(process.name+": ");
            for (int i = 0; i < process.historyFactor.size(); i++) {
                System.out.print(process.historyFactor.get(i)+" ");
            }
            System.out.print("\n");
        }
    }
    public void printHistoryQt()
    {
        for (Process process: processes) {
            System.out.println(process.name+": ");
            for (int i = 0; i < process.historyQt.size(); i++) {
                System.out.print(process.historyQt.get(i)+" ");
            }
            System.out.print("\n");
        }
    }
    public void printWaiting()
    {
        for (Process process: processes) {
            System.out.println(process.name+" time: "+ process.waitingTime);
        }
    }

    public double getAverageWaiting()
    {
        int sum=0;
        for (Process process: processes) {
            sum+=process.waitingTime;
        }
        double average=0;
        average=(float)sum/processes.size();
        return average;
    }


    public void setTurnAround(int t,Process p)
    {
        p.turnAroundTime=t- p.arrivalTime;
    }

    public void getTurnAround()
    {
        for (Process process: processes) {
            System.out.println(process.name+" time: "+ process.turnAroundTime);
        }
    }

    public double AverageTurnAround()
    {
        int sum=0;
        for (Process process: processes) {
            sum+=process.turnAroundTime;
        }
        double average=0;
        average=(float)sum/processes.size();
        return average;
    }

    public void scheduleAgat()
    {
        checkTime();
        while (deadList.size()!=processes.size()) {
            int q; boolean exists=false;

            Process currProcess=readyQueue.get(0);
            if(!currProcess.historyQt.contains(currProcess.quantumTime))
                currProcess.historyQt.add(currProcess.quantumTime);
            Pair<Process,Integer> p=new Pair(currProcess,time-currProcess.arrivalTime);

            for (Pair<Process, Integer> processIntegerPair : waiting) {

                if (processIntegerPair.getKey().name.equals(p.getKey().name))
                    exists = true;
            }
            if(!exists)
            {
                waiting.add(p);
                p.getKey().waitingTime=p.getValue();
            }


            q=(Math.round((float)(40*currProcess.quantumTime)/100));

            int remQuantum= currProcess.quantumTime;
            calculateFactor();
            for (int i = 1; i <= currProcess.quantumTime; i++) {
                time++;
                checkTime();
                currProcess.updatedBurstTime--;
               if(currProcess.updatedBurstTime!=0) {
                   if (i >= q) {
                       if(calcMinFactor()!=null) {
                           if (currProcess.factor > calcMinFactor().factor) {
                               int indexCurr = readyQueue.indexOf(currProcess);
                               int rep = readyQueue.indexOf(calcMinFactor());
                               readyQueue.set(indexCurr, calcMinFactor());
                               readyQueue.remove(rep);
                               readyQueue.add(currProcess);
                               remQuantum--;
                               currProcess.quantumTime = currProcess.quantumTime + remQuantum;
                               currProcess.historyQt.add(currProcess.quantumTime);
                               break;
                           }
                       }
                   }
                   remQuantum--;
               }
               else {
                   currProcess.quantumTime=0;
                   currProcess.historyQt.add(currProcess.quantumTime);
                   readyQueue.remove(currProcess);
                   deadList.add(currProcess);
                   setTurnAround(time,currProcess);
                   break;
               }

            }
            if(currProcess.updatedBurstTime>0 && remQuantum==0)
            {
                readyQueue.remove(currProcess);
                readyQueue.add(currProcess);
                currProcess.quantumTime=currProcess.quantumTime + 2;
                currProcess.historyQt.add(currProcess.quantumTime);
            }

        }


    }
}
