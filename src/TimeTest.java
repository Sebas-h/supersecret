import java.io.*;

/**
 * Created by Jan on 11/7/2015.
 */
public class TimeTest {
    private long startTime;

    private long endTime;

    private long time;

    private int initialAttacksSize;

    private FileOutputStream file;

    public TimeTest(){
        File file = new File("testResults.csv");
        try{
            this.file = new FileOutputStream(file, true);
        }
        catch (FileNotFoundException e){
            System.err.println("File not found");
        }

    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void setInitialAttacksSize(int size){
        initialAttacksSize = size;
    }

    public void stop(){
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }

    public void writeResults(){
        String data;
        data = String.valueOf(initialAttacksSize) + "," + time + "\n";
        try{
            file.write(data.getBytes());
            file.close();
        }
        catch (IOException e){

        }
    }

}
