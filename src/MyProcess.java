import java.util.Comparator;

public class MyProcess implements Cloneable {

    int deadline;
    int arrivalTime;
    int sector;

    public MyProcess(int arrivalTime, int sector, int deadline) {
        this.deadline = deadline;
        this.arrivalTime = arrivalTime;
        this.sector = sector;
    }

    @Override
    protected MyProcess clone() throws CloneNotSupportedException {
        try {
            return (MyProcess)super.clone();
        } catch(Exception e){
            return null;
        }
    }

    public static Comparator<MyProcess> DeadlineComp = new Comparator<MyProcess>(){
        public int compare(MyProcess a1, MyProcess a2){
            return a1.getDeadline() < a2.getDeadline() ? -1 : a1.getDeadline() > a2.getDeadline() ? 1 : 0;
        }
    };

    public static Comparator<MyProcess> ArrivalTimeComp = new Comparator<MyProcess>(){
        public int compare(MyProcess p1, MyProcess p2){
            return p1.arrivalTime < p2.arrivalTime ? -1 : p1.arrivalTime > p2.arrivalTime ? 1 : 0;
        }
    };

    public int  getDeadline() { return deadline;}
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    public int  getSector() {
        return sector;
    }
    public void setSector(int sector) {
        this.sector = sector;
    }
    public int  getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
