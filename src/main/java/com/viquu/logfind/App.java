package com.viquu.logfind;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Logfind Main
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {

        String path = "F:\\0913\\application.2016091309_201.log";
        String destpath = "F:\\0913\\application.2016091309_201_api.log";
        String findStr = "调用API返回结果:";

        int startColumn = 0;
        int endColumn = 400;
        String readCharset = "GBK";
        String writeCharset = "UTF-8";

        File file = new File(path);
        File destfile = new File(destpath);

        BufferedReader bufferedReader = null;
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(file), readCharset);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
            String lineTxt;

            if (!destfile.exists()) {
                FileUtils.touch(destfile);
            }

            int lineNum = 0;
            while ((lineTxt = bufferedReader.readLine()) != null)
            {
                lineNum++;
                if (lineTxt.contains(findStr)) {
                    lineTxt = lineTxt.length() > endColumn ? lineTxt.substring(startColumn, endColumn) : lineTxt;
                    lineTxt += "\n";
                    FileUtils.writeStringToFile(destfile, lineTxt, writeCharset, true);
                    System.out.println("find a line:" + lineNum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(read);
        }

    }
}
