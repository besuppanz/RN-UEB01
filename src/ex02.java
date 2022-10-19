import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ex02 {

    public static void main(String[] args) {

        //User Input via Scanner
        Scanner in = new Scanner(System.in);
        System.out.println("Geben Sie den namen der Input Datei an:");
        String fileName = in.nextLine();

        System.out.println("Geben Sie den Namen der Output Datei an:");
        String outputName = in.nextLine();

        try {
            //Create underyling input and output streams
            InputStream inputStream = new FileInputStream("src/" + fileName);
            OutputStream outputStream = new FileOutputStream("output.zip");

            //Create ZipOutputStream
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

            //Create new Entry for Zip-directory with outputName given by the user
            ZipEntry zipEntry = new ZipEntry(outputName);

            //Add Entry to Zip-directory
            zipOutputStream.putNextEntry(zipEntry);

            //Create InputStreamReader based on inputStream with UTF-8 encoding
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

            //Create OutputStreamWriter based on zipOutputStream with ISO-8859-1 encoding
            OutputStreamWriter writer = new OutputStreamWriter(zipOutputStream, "ISO-8859-1");

            //Buffered Reader and Writer for performance
            BufferedReader bufferedReader = new BufferedReader(reader);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String line;

            //BufferedReader -> InputStreamReader -> InputStream
            while((line = bufferedReader.readLine()) != null) {
                line += "\r\n";
                bufferedWriter.write(line);
            }

            //closing
            bufferedWriter.close();
            zipOutputStream.close();
            bufferedReader.close();
            reader.close();
            writer.close();
            inputStream.close();
            outputStream.close();
            in.close();

        } catch(FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.out.println("Die Datei konnte nicht gefunden werden");
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Komprimierung fehlgeschlagen");
        }


    }
}
