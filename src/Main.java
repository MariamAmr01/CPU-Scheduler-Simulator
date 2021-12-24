import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        ArrayList<Process> processes = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of processes");
        int n = input.nextInt();
        System.out.println("Enter context switching");
        int contextSwitching = input.nextInt();
        for(int i = 0; i<n; i++)
        {
            Scanner input2= new Scanner(System.in);
            Process p= new Process();
            System.out.println("Enter process "+(i+1)+" name:");
            p.name=input2.nextLine();
            System.out.println("Enter process "+(i+1)+" color:");
            p.color=input2.nextLine();
            System.out.println("Enter process "+(i+1)+" arrival time:");
            p.arrivalTime=input.nextInt();
            System.out.println("Enter process "+(i+1)+" burst time:");
            p.burstTime=input2.nextInt();
            p.updatedBurstTime= p.burstTime;
            System.out.println("Enter process "+(i+1)+" priority number:");
            p.priorityNumber=input2.nextInt();
            System.out.println("Enter process "+(i+1)+" time quantum: ");
            p.quantumTime= input.nextInt();
            processes.add(p);
        }
    }
}
