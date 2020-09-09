import java.io.*;
import java.util.ArrayList;

public class CSVFileFormat {
    private static String readFilePath = "C:\\Users\\User\\Desktop\\労働者名簿_固定長.txt";
    private static String readFileFormat = "C:\\Users\\User\\Desktop\\file_layout.txt";
    private static String writeFilePath = "C:\\Users\\User\\Desktop\\new.csv";
    private static String readWorkerList;
    private static String readWorkerFormat;
    private static ArrayList<Integer> formatNumber;
    private static ArrayList<String> employeeNo, employeeName, gender, birthday, enterDate, phoneNo, handPhone, healthInsuranceNo, nenkin, eInsuranceNo;

    public static void main(String[] args) {
        employeeNo = new ArrayList<String>();
        employeeName = new ArrayList<String>();
        formatNumber = new ArrayList<Integer>();
        gender = new ArrayList<String>();
        birthday = new ArrayList<String>();
        enterDate = new ArrayList<String>();
        phoneNo = new ArrayList<String>();
        handPhone = new ArrayList<String>();
        healthInsuranceNo = new ArrayList<String>();
        nenkin = new ArrayList<String>();
        eInsuranceNo = new ArrayList<String>();
        //file_layoutを読み込んで幅広いをArrayListとしてもらう
        readWorkerFormat = readFile(readFileFormat, 2);
        //幅広いArrayListの改行を消す
        String replaceNewLine = readWorkerFormat.replaceAll("[\r\n]+$", "");
        //幅広いArrayListのコンマを消す
        String[] split_num = replaceNewLine.split(",", 0);
        //幅広いArrayListにデータを入れる
        for (int i = 0; i < split_num.length; i++) {
            formatNumber.add(Integer.parseInt(split_num[i]));
        }
        //労働者名簿_固定長.txtを読み込んで幅広いArrayListを基づいて各列データを設定する
        readWorkerList = readFile(readFilePath, 1);
        //CSVファイルを出力する
        writeCSVFile();

    }

    private static void writeCSVFile() {
        try {
            //Fileクラスのオブジェクトを作成する
            File writeFile = new File(writeFilePath);
            //PrintWriterクラスのオブジェクトを作成する
            PrintWriter printWriter = new PrintWriter(writeFile);
            //StringBuilderクラスのオブジェクトを作成する
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < eInsuranceNo.size(); i++) {
                //各列データを書き込む
                sb.append(employeeNo.get(i));
                sb.append(",");
                sb.append(employeeName.get(i));
                sb.append(",");
                sb.append(gender.get(i));
                sb.append(",");
                sb.append(birthday.get(i));
                sb.append(",");
                sb.append(enterDate.get(i));
                sb.append(",");
                sb.append(phoneNo.get(i));
                sb.append(",");
                sb.append(handPhone.get(i));
                sb.append(",");
                sb.append(healthInsuranceNo.get(i));
                sb.append(",");
                sb.append(nenkin.get(i));
                sb.append(",");
                sb.append(eInsuranceNo.get(i));
                sb.append("\r\n");
            }
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    private static String readFile(String readFilePath, int type) {
        //StringBuilderクラスのオブジェクトを作成する
        StringBuilder stringBuilder = new StringBuilder();
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
                    //幅広いArrayListを基づいて各列データを設定する
                    int layout_format_first = 0;
                    int layout_format_second = 0;
                    stringBuilder.append(line);
                    layout_format_second = layout_format_first + formatNumber.get(0);
                    employeeNo.add(line.substring(0, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(1);
                    employeeName.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(2);
                    gender.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(3);
                    birthday.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(4);
                    enterDate.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(5);
                    phoneNo.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(6);
                    handPhone.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(7);
                    healthInsuranceNo.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(8);
                    nenkin.add(line.substring(layout_format_first, layout_format_second));
                    layout_format_first = layout_format_second;
                    layout_format_second = layout_format_first + formatNumber.get(9);
                    eInsuranceNo.add(line.substring(layout_format_first, layout_format_second));
                    stringBuilder.append(System.lineSeparator());
                }
                if (type == 2) {
                    //file_layoutを読み込んで幅広いをArrayListとしてもらう
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());

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
        return stringBuilder.toString();
    }
}