import java.util.Comparator;
import java.util.PriorityQueue;

public class SSTF_EDF {

    static int head;
    int movement;
    int deadProcesses;
    int diskCapacity;

    public SSTF_EDF(MyProcess[] array, int diskCapacity) {
        this.diskCapacity = diskCapacity;
        head = 0;
        movement = 0;
        deadProcesses = 0;
        int time = 0;
        int i = 0;

        PriorityQueue<MyProcess> queue = new PriorityQueue<>(1, SeekTimeComp);
        PriorityQueue<MyProcess> deadlineQueue = new PriorityQueue<>(1, MyProcess.DeadlineComp); //samo sortuje

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

                    if (process.getSector() == head) {
                        deadlineQueue.remove();
                    }
                }
                //#OBSLUGA QUEUE
            } else if (!queue.isEmpty()){
                MyProcess process = queue.peek();
                if (process.getSector() > head) {
                    head++;
                    movement++;
                } else if (process.getSector() < head){
                    head--;
                    movement++;
                }
                if (process.getSector() == head){
                    queue.remove();
                }
            }
            time++;
        }
    }

    public static Comparator<MyProcess> SeekTimeComp = new Comparator<MyProcess>(){
        public int compare(MyProcess p1, MyProcess p2){
            int s1 = Math.abs(p1.getSector() - head);
            int s2 = Math.abs(p2.getSector() - head);
            return s1 < s2 ? -1 : s1 > s2 ? 1 : 0;
        }
    };
}
