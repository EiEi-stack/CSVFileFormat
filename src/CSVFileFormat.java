import java.io.*;
import java.util.ArrayList;

public class CSVFileFormat {
    private static String readFilePath = "C:\\Users\\User\\Desktop\\労働者名簿_固定長.txt";
    private static String readFileFormat = "C:\\Users\\User\\Desktop\\file_layout.txt";
    private static String writeFilePath = "C:\\Users\\User\\Desktop\\労働者名簿_固定長_CSV.csv";
    private static ArrayList<Integer> formatNumber;
    private static StringBuilder stringBuilder;
    private static int layout_format_start = 0;
    private static int layout_format_end = 0;
    private static Boolean initial = false;


    public static void main(String[] args) {
        //幅広いArrayList作成する
        formatNumber = new ArrayList<Integer>();
        //file_layoutを読み込んで幅広いをArrayListとしてもらう
        readFile(readFileFormat, 2);
        //労働者名簿_固定長.txtを読み込んで幅広いArrayListを基づいて各列データを設定する
        readFile(readFilePath, 1);
    }

    private static void writeCSVFile(String substring) {
        try {
            //Fileクラスのオブジェクトを作成する
            File writeFile = new File(writeFilePath);
            //PrintWriterクラスのオブジェクトを作成する
            PrintWriter printWriter = new PrintWriter(writeFile);
            //StringBuilderクラスのオブジェクトを作成する
            StringBuilder sb = new StringBuilder();
            printWriter.write(substring);
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readFile(String readFilePath, int type) {
        stringBuilder = new StringBuilder();
        try {
            //Fileクラスのオブジェクトを作成する
            File read_file = new File(readFilePath);
            String line = null;
            //FileReaderクラスのオブジェクトを作成する
            FileReader fileReader = new FileReader(read_file);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (type == 1) {
                    //幅広いArrayListを基づいてデータの幅広いを分割する
                    for (int i = 0; i < formatNumber.size(); i++) {
                        if (i == 0)
                            initial = true;
                        else
                            initial = false;
                        //データの幅広いを分割する機能を呼ぶ
                        stringBuilder.append(splitData(formatNumber.get(i), line, initial)).append(",");
                    }
                    //分割したデータをCSVFileで一行ずつ書き込み
                    writeCSVFile(stringBuilder.toString());
                    stringBuilder.append(System.lineSeparator());
                }
                if (type == 2) {
                    //file_layoutを読み込んで幅広いをArrayListとしてもらう
                    stringBuilder.append(line);
                    //幅広いArrayListのコンマを消す
                    String[] split_num = stringBuilder.toString().split(",", 0);
                    //幅広いArrayListにデータを入れる
                    for (int i = 0; i < split_num.length; i++) {
                        formatNumber.add(Integer.parseInt(split_num[i]));
                    }
                }
            }
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

    //データの幅広いを分割する機能
    private static String splitData(int formatLayout, String data, Boolean initial) {

        if (initial == true) {
            layout_format_start = 0;
            layout_format_end = formatLayout;
        } else {
            layout_format_start = layout_format_end;
            layout_format_end = layout_format_start + formatLayout;
        }
        //幅広いArrayListを基づいてデータの幅広いを分割する
        String splitData = data.substring(layout_format_start, layout_format_end);
        return splitData;
    }
}