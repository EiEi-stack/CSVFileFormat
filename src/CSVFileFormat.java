import java.io.*;

public class CSVFileFormat {
    private static String READ_FILE_PATH = "C:/Users/User/Desktop/労働者名簿_固定長.txt";
    private static String READ_FILE_FORMAT = "C:/Users/User/Desktop/file_layout.txt";
    private static String WRITE_FILE_PATH = "C:/Users/User/Desktop/労働者名簿_固定長_CSV.csv";
    private static String[] layoutDimension;

    public static void main(String[] args) {
        //file_layoutを読み込んで桁数をArrayListとしてもらう
        readFileLayout(READ_FILE_FORMAT);
        //労働者名簿_固定長.txtを読み込んで桁数ArrayListを基づいて各列データを設定する
        readFileWriteOutput(READ_FILE_PATH);
    }

    private static void readFileLayout(String readFilePath) {
        try {
            //Fileクラスのオブジェクトを作成する
            File readFile = new File(readFilePath);
            String line = null;
            //FileReaderクラスのオブジェクトを作成する
            FileReader fileReader = new FileReader(readFile);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                //桁数を習得する
                layoutDimension = line.split(",", 0);
            }
            //bufferReaderを使ったあと閉じる
            bufferedReader.close();
        }
        //FileReaderクラスのオブジェクトを作成の例外
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //readメソッドの例外
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readFileWriteOutput(String readFilePath) {
        try {
            //Fileクラスのオブジェクトを作成する
            File readFile = new File(readFilePath);
            String line = null;
            //FileReaderクラスのオブジェクトを作成する
            FileReader fileReader = new FileReader(readFile);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferReader = new BufferedReader(fileReader);
            //StringBuilderクラスのオブジェクトを作成する
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferReader.readLine()) != null) {
                int layoutFormatStart = 0;
                int layoutFormatEnd = 0;
                //桁数ArrayListを基づいてデータの桁数を分割する
                for (int i = 0; i < layoutDimension.length; i++) {
                    //データの桁数を分割する機能を呼ぶ
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
                    //分割したデータをCSVFileで一行ずつ書き込み
                    try {
                        //Fileクラスのオブジェクトを作成する
                        File writeFile = new File(WRITE_FILE_PATH);
                        //PrintWriterクラスのオブジェクトを作成する
                        PrintWriter printWriter = new PrintWriter(writeFile);
                        printWriter.write(stringBuilder.toString());
                        printWriter.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                stringBuilder.append(System.lineSeparator());
            }
            //bufferReaderを使ったあと閉じる
            bufferReader.close();
        }
        //FileReaderクラスのオブジェクトを作成の例外
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //readメソッドの例外
        catch (IOException e) {
            System.out.println(e);
        }
    }
}