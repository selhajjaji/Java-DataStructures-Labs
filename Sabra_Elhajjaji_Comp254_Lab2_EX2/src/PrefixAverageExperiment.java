import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PrefixAverageExperiment {



    public static void main(String[] args) {
        int[] sizes = {40, 60, 640, 2560, 10240, 12800, 25600, 51200, 102400};
        Random random= new Random();
        try (FileWriter writer = new FileWriter("D:/Centennial College-Winter2025/data structures/Lab2/Lesson4Examplesprefix_average_results.cvs")){

            writer.write("n,prefixAverage1,prefixAverage2");

            for (int n: sizes){
                double[] x = new double[n];
                for (int i = 0; i < n; i++) {
                    x[i]= random.nextDouble();
                }

                long start1= System.nanoTime();
                PrefixAverage.prefixAverage1(x);
                long end1=System.nanoTime();
                long time1= end1-start1;

                long start2= System.nanoTime();
                PrefixAverage.prefixAverage2(x);
                long end2=System.nanoTime();
                long time2= end2-start2;

                writer.write("\n"+n+","+time1+","+time2+"\n");

                System.out.println("n = " + n + " | Time1: " + time1 + " ns | Time2: " + time2 + " ns");
            }

            System.out.println("Results saved to prefix_average_results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
