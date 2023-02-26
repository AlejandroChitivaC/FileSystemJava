import java.io.*;

public class AplMain {
    public static void main(String[] args) throws IOException {
        File file = new File("Archivo1.txt");
        File file2 = new File("Archivo2.txt");

        PrintWriter my_pw = new PrintWriter("Union.txt");
        BufferedReader my_br = new BufferedReader(new FileReader("Archivo1.txt"));
        String my_line = my_br.readLine();
        while (my_line != null) {
            my_pw.println(my_line);
            my_line = my_br.readLine();
        }
        my_br = new BufferedReader(new FileReader("Archivo2.txt"));
        my_line = my_br.readLine();
        while (my_line != null) {
            my_pw.println(my_line);
            my_line = my_br.readLine();
        }
        my_pw.flush();
        my_br.close();
        my_pw.close();
        System.out.println("The first two files have been merged into the third file successfully.");
    }
}
