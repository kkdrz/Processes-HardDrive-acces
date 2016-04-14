import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FCFS_FD {

    int head;
    int movement;
    int deadProcesses;
    int diskCapacity;

    ArrayDeque<MyProcess> queue = new ArrayDeque<>();
    PriorityQueue<MyProcess> deadlineQueue = new PriorityQueue<>(1, MyProcess.DeadlineComp); //samo sortuje

    public FCFS_FD(MyProcess[] array, int diskCapacity) {
        this.diskCapacity = diskCapacity;
        head = 0;
        movement = 0;
        deadProcesses = 0;
        int time = 0;
        int i = 0;

        while (i < array.length || !queue.isEmpty() || !deadlineQueue.isEmpty()) {

            while (i < array.length && time == array[i].getArrivalTime()) {
                if (array[i].getDeadline() != 0) {
                    deadlineQueue.add(array[i]);
                } else {
                    queue.add(array[i]);
                }
                i++;
            }

            //#OBSLUGA DEADLINE
            if(!deadlineQueue.isEmpty()) {
                while (!deadlineQueue.isEmpty()) {
                    if(Math.abs(deadlineQueue.peek().getSector() - head) > deadlineQueue.peek().getDeadline()){
                        deadProcesses++;
                        deadlineQueue.poll();
                        continue;
                    }
                    MyProcess process = deadlineQueue.peek();
                    if (process.getSector() > head) {
                        head++;
                        movement++;
                    } else if (process.getSector() < head) {
                        head--;
                        movement++;
                    }

                    fd_execute();

                }
                //#OBSLUGA QUEUE
            } else if (!queue.isEmpty()){
                MyProcess process = queue.getFirst();
                if (process.getSector() > head) {
                    head++;
                    movement++;
                } else if (process.getSector() < head){
                    head--;
                    movement++;
                }
                if (process.getSector() == head){
                    queue.removeFirst();
                }
            }
            time++;
        }
    }

    private void fd_execute() {
        ArrayList<MyProcess> toRemove = new ArrayList<>();
        for(MyProcess p: queue)
            if (p.getSector() == head) toRemove.add(p);
        queue.removeAll(toRemove);
        toRemove.clear();
        for(MyProcess d: deadlineQueue)
            if (d.getSector() == head) toRemove.add(d);
        deadlineQueue.removeAll(toRemove);
        toRemove.clear();
    }
}
