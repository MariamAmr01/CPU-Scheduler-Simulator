
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;


public class SJF {
    public static ArrayList<Process> processes;
    public static ArrayList<Process> sortedprocess = new ArrayList<>();
    public static ArrayList<Integer> turnArroundTime = new ArrayList<Integer>();
    public static ArrayList<Integer> burstTime = new ArrayList<Integer>();
    public SJF(ArrayList<Process>processes){
        this.processes = processes;
        gettingBrustTime(processes);
        System.out.println("processes execution order ");
        calCompletionTime(processes);
        calTurnaroundTime(processes);
        calWaitingTime(processes,turnArroundTime);
    }

    public static void calCompletionTime(ArrayList<Process> processes )
    {
        int startTime=0;
        while (!served(processes))
        {
            Process minBTprocess = new Process();
            int minBT = Integer.MAX_VALUE;
            for(int i = 0; i<processes.size(); i++)
            {
                if(processes.get(i).getBurstTime() < minBT&& !processes.get(i).isEnded()&& processes.get(i).getArrivalTime()<=startTime)
                {
                    minBT = processes.get(i).getBurstTime();
                    minBTprocess = processes.get(i);
                }
            }
            startTime+=minBT;
            System.out.print(minBTprocess.getName()+" : "+(startTime)+"  ");
            if(minBTprocess != null)
            {

                minBTprocess.setBurstTime(minBTprocess.getBurstTime()-minBTprocess.getBurstTime());
                if(minBTprocess.getBurstTime()==0)
                {
                    minBTprocess.setEndTime(startTime);
                    sortedprocess.add(minBTprocess);
                }

            }


        }
    }

    public static void calTurnaroundTime(ArrayList<Process> processes){
        double avgTurnaroundTime = 0.0;
        System.out.println();
        System.out.println("turnaround time for each process is:");
        for(int i =0;i<processes.size();i++){
            int tat = processes.get(i).getEndTime() - processes.get(i).getArrivalTime();
            avgTurnaroundTime+=tat;
            System.out.println(processes.get(i).getName()+": "+ tat);
            turnArroundTime.add(tat);
        }
        System.out.println("Average turnaround time is " + (avgTurnaroundTime/processes.size()));
    }
    public static void calWaitingTime(ArrayList<Process>processes, ArrayList<Integer> turnArroundTime){
        double avgWaitingTime = 0.0;
        System.out.println();
        System.out.println("waiting time for each process is ");
        for(int i =0; i<processes.size();i++)
        {
            int wt = turnArroundTime.get(i)-burstTime.get(i);
            System.out.println(processes.get(i).getName()+" : "+wt);
            avgWaitingTime+=wt;
        }
        System.out.println("Average waiting time is " +(avgWaitingTime/processes.size()));

    }
    public static boolean served(ArrayList<Process> processes){
        for(int i= 0; i<processes.size();i++){
            if(!processes.get(i).isEnded())return false;
        }
        return true;
    }
    public static void gettingBrustTime(ArrayList<Process> processes){
        for (int i = 0; i<processes.size(); i++){
            burstTime.add(processes.get(i).getBurstTime());
        }
    }


}


