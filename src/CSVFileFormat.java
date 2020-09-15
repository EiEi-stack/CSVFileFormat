import java.io.*;

public class CSVFileFormat {
    private static String READ_FILE_PATH = "C:/Users/User/Desktop/csv/input/労働者名簿_固定長.txt";
    private static String READ_FILE_FORMAT = "C:/Users/User/Desktop/csv/input/file_layout.txt";
    private static String WRITE_FILE_PATH = "C:/Users/User/Desktop/csv/output/労働者名簿_固定長_CSV.csv";
    private static String[] layoutDimension;

    public static void main(String[] args) {
        readFileLayout(READ_FILE_FORMAT);
        readFileWriteOutput(READ_FILE_PATH);
    }

    private static void readFileLayout(String readFilePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(readFilePath)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                //桁数を習得する
                layoutDimension = line.split(",", 0);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readFileWriteOutput(String readFilePath) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(WRITE_FILE_PATH));
            BufferedReader bufferReader = new BufferedReader(new FileReader(new File(readFilePath)));
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();

                int layoutFormatStart = 0;
                int layoutFormatEnd = 0;
                //桁数ArrayListを基づいてデータの桁数を分割する
                for (int i = 0; i < layoutDimension.length; i++) {
                    if (i == 0) {
                        layoutFormatStart = 0;
                        layoutFormatEnd = Integer.parseInt(layoutDimension[i]);
                    } else {
                        layoutFormatStart = layoutFormatEnd;
                        int dimensionNumber = Integer.parseInt(layoutDimension[i]);
                        layoutFormatEnd = layoutFormatStart + dimensionNumber;
                    }
                    String splitData = line.substring(layoutFormatStart, layoutFormatEnd);
                    stringBuilder.append(splitData).append(",");
                }
                printWriter.write(stringBuilder.toString() + System.lineSeparator());
            }
            bufferReader.close();
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}