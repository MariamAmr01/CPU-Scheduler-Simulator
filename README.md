# CPU Scheduler Simulator
Simulating CPU schedule algorithms (With solving starvation) <br />
* Non-Preemptive Priority Scheduling with context switching.
* Non-Preemptive Shortest- Job First (SJF).
* Shortest-Remaining Time First (SRTF).
* AGAT Scheduling.
  each process has its own quantum (different from the others)  <br />
  
## AGAT
A new factor is suggested to attach with each submitted process in AGAT scheduling algorithm. <br />
AGAT factor based on (priority, arrival time and remaining service time).  <br /><br />

* Set v1 as (if last-arrival-time > 10 then (last-arrival-time(all processes) /10) else 1)
* Set V2 as (if max-remaining burst time > 10 then (max-remaining burst time(all processes) /10) else 1)
* AGAT-Factor = (10-Priority) + ceiling(Arrival Time/v1) + ceiling(Remaining Burst Time/v2) <br /><br />

* Once a process is executed for given time period, it’s called Non-preemptive AGAT till the finishing of (round(40% of quantum time))
* After that it’s converted to Pre-emptive AGAT after which it can be replaced with the process with the best (least) AGAT factor if any.
  
  
### Scenarios of AGAT
1- The running process used all its quantum time and it still has job to do (add this process to the end of the queue, then increases its quantum time by 2).
 Next process is picked from queue.
2- The running process didn’t use all its quantum time because it was removed in favor of a process with better AGAT factor (add this process to the end of the queue,
then increases its quantum time by the remaining quantum time for it).
3- The running process finished its job (set its quantum time to zero and remove it from ready queue and add it to the dead list).
