import java.util.ArrayList;

public class SJF {
    public static ArrayList<Process> processes;
    public static ArrayList<Process> sortedprocess = new ArrayList<>();

    public SJF(ArrayList<Process>processes){
        this.processes = processes;
        System.out.println("processes execution order ");
        calCompletionTime(processes);
        calTurnaroundTime(processes);
        calWaitingTime(processes);
    }

    public static void calCompletionTime(ArrayList<Process> processes )
    {
        int startTime=0;
        for(int j=0;j<processes.size();j++)
        {
            Process minBTprocess = new Process();
            int minBT = Integer.MAX_VALUE;
            for (Process process : processes) {
                if (startTime - process.getArrivalTime() >= process.age && process.getEndTime() == -1 && process.getArrivalTime() <= startTime) {
                    minBT = process.getBurstTime();
                    minBTprocess = process;
                    break;
                }
                if (process.getBurstTime() < minBT && process.getEndTime() == -1 && process.getArrivalTime() <= startTime) {
                    minBT = process.getBurstTime();
                    minBTprocess = process;
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
        for (Process process : processes) {
            int tat = process.getEndTime() - process.getArrivalTime();
            avgTurnaroundTime += tat;
            System.out.println(process.getName() + ": " + tat);
            process.setturnAroundTime(tat);
        }
        System.out.println("Average turnaround time is " + (avgTurnaroundTime/processes.size()));
    }
    public static void calWaitingTime(ArrayList<Process>processes){
        double avgWaitingTime = 0.0;
        System.out.println();
        System.out.println("waiting time for each process is ");
        for (Process process : processes) {
            int wt = process.getTurnAroundTime() - process.getBurstTime();
            System.out.println(process.getName() + " : " + wt);
            avgWaitingTime += wt;
        }
        System.out.println("Average waiting time is " +(avgWaitingTime/processes.size()));

    }




}

