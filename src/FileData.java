import java.io.*;

public class FileData {
    private String description;
    private int id;
    private int arraySize;
    private float[] array;
    private String pathToFile;

    public FileData(String description, int id, int arraySize, float[] array, String pathToFile) {
        this.description = description;
        this.id = id;
        this.arraySize = arraySize;
        this.array = array;
        this.pathToFile = pathToFile;
    }

    public void createFile() throws IOException {


        OutputStream outputStream = new FileOutputStream(pathToFile);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write(description + ",");
        bufferedWriter.write(id + ",");
        bufferedWriter.write(arraySize + ",");

        for(int i = 0; i < arraySize; i++) {
            bufferedWriter.write(String.valueOf(array[i]));
            if(i != arraySize-1) {
                bufferedWriter.write(",");
            }
        }

        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();

        /*DataOutputStream out = new DataOutputStream(new FileOutputStream(pathToFile));
        out.writeChars(description + ",");
        out.writeInt(id);
        out.writeChars(",");
        out.writeInt(arraySize);
        out.writeChars(",");

        for(int i = 0; i < arraySize; i++) {
            out.writeFloat(array[i]);
            if(i != arraySize-1) {
                out.writeChars(",");
            }
        }*/
    }
}
