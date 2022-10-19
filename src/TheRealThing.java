import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class TheRealThing extends Thread {
    private static float result = 0;
    private String filename;
    private int start;
    private int end;
    private static float[] floatArray;


    /**
     * Creates a new TheRealThing thread which operates
     * on the indexes start to end.
     */
    public TheRealThing(String filename, int start, int end) {
        this.filename = filename;
        this.start = start;
        this.end = end;
    }

    /**
     * Performs "eine komplizierte Berechnung" on array and
     * returns the result
     */
    public float eine_komplizierte_Berechnung(float[] array) {
        // TODO ... erfinden Sie etwas, seien Sie kreativ!
        for(int i = start; i < end; i++) {
            result += array[i];
        }
        return result;
    }

    public void run() {
        // TODO ...
        eine_komplizierte_Berechnung(floatArray);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String pathToFile = "src/myArrayData.dat";
        int numThreads = 12;
        int arraySize = 70;
        // TODO ...

        //Generate Array with Random Values
        float[] myArray = new float[arraySize];
        Random rand = new Random();
        float min = 0.0f;
        float max = 20.0f;

        //testResult for comparison with result from threads
        float testResult = 0;
        for(int i = 0; i < arraySize; i++) {
            //Add random values
            myArray[i] = min + rand.nextFloat() * (max-min);
            testResult += myArray[i];
        }

        //Creating File
        FileData fileData = new FileData("myArrayData", 1, arraySize, myArray, pathToFile);
        fileData.createFile();

        //Create InputStream from file
        FileInputStream fileInputStream = new FileInputStream(pathToFile);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //Read from file and store data in variables
        String line = bufferedReader.readLine();
        String[] data = line.split(",");
        String label = data[0];
        int id = Integer.parseInt(data[1]);
        int count = Integer.parseInt(data[2]);

        floatArray = new float[count];

        //Store float values in array
        for(int i = 3; i < count+3; i++) {
            floatArray[i-3] = Float.parseFloat(data[i]);
        }


        //Split array for processing with threads
        //Calculate how many entries of array each thread has to process
        int toProcessAverage = arraySize/numThreads;

        //Calculate remainder, last thread will have to process extra entires if remainder > 0
        int toProcessRemainder = arraySize % numThreads;

        //Create array storing the index range of entries each thread has to process
        int[] toProcess = new int[numThreads];

        //Add index ranges to array, except for last entry
        for(int i = 0; i < numThreads-1; i++) {
            toProcess[i] = toProcessAverage*(i+1);
        }

        //Calculate last entry end index with previously calculated remainder
        toProcess[numThreads-1] = (toProcessAverage * numThreads) + toProcessRemainder;


        //Create List of Threads
        ArrayList<Thread> threads = new ArrayList<>();

        //Initialize start and end variables
        int start = 0;
        int end = 0;

        //Create each Thread with Task and different start and end values
        for(int i = 0; i < numThreads; i++) {
            end = toProcess[i];
            threads.add(new TheRealThing(pathToFile, start, end));
            start = end;
        }

        //Start threads
        for(Thread t : threads) {
            t.start();
        }

        //Wait for threads to join
        for(Thread t : threads) {
            t.join();
        }

        //closing
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();

        //Print Result
        System.out.println("Result:" + result);
        System.out.println("Test Result:" + testResult);
    }
}
