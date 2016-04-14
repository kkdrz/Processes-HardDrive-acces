import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Test {

    static int seriesNumber = 100;
    static int processesNumber = 40;
    static int processesDeadlineNumber = 20;
    static int maxArrivalTime = 600;
    static int minArrivalTime = 1;
    static int maxDeadline = 1500;
    static int minDeadline = 1000;
    static int diskCapacity = 1500;


    public static void main(String[] args) throws CloneNotSupportedException {

        PrintWriter cout = new PrintWriter(System.out, true);
        double FCFS_EDF_TotalMovement = 0;
        double FCFS_FD_TotalMovement = 0;
        double SSTF_EDF_TotalMovement = 0;
        double SSTF_FD_TotalMovement = 0;
        double SCAN_EDF_TotalMovement = 0;
        double SCAN_FD_TotalMovement = 0;
        double C_SCAN_EDF_TotalMovement = 0;
        double C_SCAN_FD_TotalMovement = 0;
        int FCFS_EDF_TotalDeadProcesses = 0;
        int FCFS_FD_TotalDeadProcesses = 0;
        int SSTF_EDF_TotalDeadProcesses = 0;
        int SSTF_FD_TotalDeadProcesses = 0;
        int SCAN_EDF_TotalDeadProcesses = 0;
        int SCAN_FD_TotalDeadProcesses = 0;
        int C_SCAN_EDF_TotalDeadProcesses = 0;
        int C_SCAN_FD_TotalDeadProcesses = 0;

        for (int i = 0; i < seriesNumber; i++) {
            MyProcess[] array = makeArray();

            FCFS_EDF fcfs_edf = new FCFS_EDF(copy(array), diskCapacity);
            FCFS_EDF_TotalMovement += fcfs_edf.movement;
            FCFS_EDF_TotalDeadProcesses += fcfs_edf.deadProcesses;

            FCFS_FD fcfs_fd = new FCFS_FD(copy(array), diskCapacity);
            FCFS_FD_TotalMovement += fcfs_fd.movement;
            FCFS_FD_TotalDeadProcesses += fcfs_fd.deadProcesses;

            SSTF_EDF sstf_edf = new SSTF_EDF(copy(array), diskCapacity);
            SSTF_EDF_TotalMovement += sstf_edf.movement;
            SSTF_EDF_TotalDeadProcesses += sstf_edf.deadProcesses;

            SSTF_FD sstf_fd = new SSTF_FD(copy(array), diskCapacity);
            SSTF_FD_TotalMovement += sstf_fd.movement;
            SSTF_FD_TotalDeadProcesses += sstf_fd.deadProcesses;

            SCAN_EDF scan_edf = new SCAN_EDF(copy(array), diskCapacity);
            SCAN_EDF_TotalMovement += scan_edf.movement;
            SCAN_EDF_TotalDeadProcesses += scan_edf.deadProcesses;

            SCAN_FD scan_fd = new SCAN_FD(copy(array), diskCapacity);
            SCAN_FD_TotalMovement += scan_fd.movement;
            SCAN_FD_TotalDeadProcesses += scan_fd.deadProcesses;

            C_SCAN_EDF c_scan_edf = new C_SCAN_EDF(copy(array), diskCapacity);
            C_SCAN_EDF_TotalMovement += c_scan_edf.movement;
            C_SCAN_EDF_TotalDeadProcesses += c_scan_edf.deadProcesses;

            C_SCAN_FD c_scan_fd = new C_SCAN_FD(copy(array), diskCapacity);
            C_SCAN_FD_TotalMovement += c_scan_fd.movement;
            C_SCAN_FD_TotalDeadProcesses += c_scan_fd.deadProcesses;
        }

        cout.println("Liczba serii: " + seriesNumber + "\nLiczba procesów: " + (processesNumber+processesDeadlineNumber) + " (" + processesDeadlineNumber + " z deadlinem)" + "\n");
        cout.println("Srednia liczba przemieszczen: ");
        cout.printf("%-12s %-12s %-12s\n", "Algorytm", "EDF", "FD SCAN");
        cout.printf("%-12s %-12.2f %-12.2f\n", "FCFS:", FCFS_EDF_TotalMovement/seriesNumber, FCFS_FD_TotalMovement/seriesNumber);
        cout.printf("%-12s %-12.2f %-12.2f\n", "SSTF:", SSTF_EDF_TotalMovement/seriesNumber, SSTF_FD_TotalMovement/seriesNumber);
        cout.printf("%-12s %-12.2f %-12.2f\n", "SCAN:", SCAN_EDF_TotalMovement/seriesNumber, SCAN_FD_TotalMovement/seriesNumber);
        cout.printf("%-12s %-12.2f %-12.2f\n", "C_SCAN:", C_SCAN_EDF_TotalMovement/seriesNumber, C_SCAN_FD_TotalMovement/seriesNumber);


        cout.println("Liczba martwych procesow: ");
        cout.printf("%-12s %-3s  %-4s %-6s %-4s\n", "Algorytm", "EDF", "%", "FD SCAN", "%");
        cout.printf("%-12s %-4d %-3.2f  %-6d %-3.2f \n", "FCFS:", FCFS_EDF_TotalDeadProcesses, (((double)FCFS_EDF_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100),  FCFS_FD_TotalDeadProcesses, (((double)FCFS_FD_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100));
        cout.printf("%-12s %-4d %-3.2f  %-6d %-3.2f \n", "SSTF:", SSTF_EDF_TotalDeadProcesses, (((double)SSTF_EDF_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100),  SSTF_FD_TotalDeadProcesses, (((double)SSTF_FD_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100));
        cout.printf("%-12s %-4d %-3.2f  %-6d %-3.2f \n", "SCAN:", SCAN_EDF_TotalDeadProcesses, (((double)SCAN_EDF_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100),  SCAN_FD_TotalDeadProcesses, (((double)SCAN_FD_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100));
        cout.printf("%-12s %-4d %-3.2f  %-6d %-3.2f \n", "C_SCAN:", C_SCAN_EDF_TotalDeadProcesses, (((double)C_SCAN_EDF_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100),  C_SCAN_FD_TotalDeadProcesses, (((double)C_SCAN_FD_TotalDeadProcesses/((double)(processesNumber+processesDeadlineNumber)*(double)seriesNumber))*100));
    }

    static MyProcess[] makeArray() {
        MyProcess[] array = new MyProcess[processesNumber+processesDeadlineNumber];
        Random rand = new Random();
        for(int i = 0; i < processesNumber; i++) {
            array[i] = new MyProcess(rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime, rand.nextInt(diskCapacity), rand.nextInt(maxDeadline-minDeadline)+minDeadline);
        }
        for(int i = processesNumber; i < processesDeadlineNumber+processesNumber; i++) {
            array[i] = new MyProcess(rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime, rand.nextInt(diskCapacity), 0);
        }
        Arrays.sort(array, MyProcess.ArrivalTimeComp);
        return array;
    }

    static MyProcess[] copy(MyProcess[] array) throws CloneNotSupportedException {
        MyProcess[] result = new MyProcess[array.length];
        for (int i = 0; i < result.length; i++){
            result[i] = array[i].clone();
        }
        return result;
    }
}
