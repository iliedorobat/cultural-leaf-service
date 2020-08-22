package ro.webdata.commons;

import java.io.*;

public class FileUtils {
    /**
     * Read data from disc
     * @param path The path to the input file
     * @return <b>StringBuilder</b>
     */
    public static StringBuilder read(String path) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String readLine;

            while((readLine = br.readLine()) != null) {
                if (readLine.trim().length() > 0)
                    sb.append(readLine.trim()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + path + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + path + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        // Remove the last new line
        sb.deleteCharAt(sb.length() - 1);

        return sb;
    }

    /**
     * Write data to disc
     * @param sb The data to be written
     * @param path The path to the output file
     * @param append Specify if the text should be appended to the existing one
     */
    public static void write(StringBuilder sb, String path, boolean append) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(path, append);
            if (append) {
                sb.append("\n");
            }
            fw.write(sb.toString());
            System.out.println("The file " + path + " has been written on the disk.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                System.err.println("The file " + path + " could not be closed."
                        + "\n" + e.getMessage());
            }
        }
    }

    public static void write(String str, String path, boolean append) {
        StringBuilder sb = new StringBuilder(str);
        write(sb, path, append);
    }
}
