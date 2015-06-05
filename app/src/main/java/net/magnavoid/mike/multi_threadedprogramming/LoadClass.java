package net.magnavoid.mike.multi_threadedprogramming;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads a file into an ArrayList
 * Created by mike on 6/2/15.
 */
public class LoadClass implements Runnable {

    public final Thread aThread;
    DataClass dataClass;

    public LoadClass(DataClass dataClass) {
        this.dataClass = dataClass;
        aThread = new Thread(this);
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        String fileName = "numbers.txt";
        BufferedReader bufferedReader = null;
        File fileToRead = new File(dataClass.getFiles(), fileName);
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            bufferedReader = new BufferedReader(new FileReader(fileToRead));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;
        int tmpProgress = dataClass.getProgress();
        try {
            int i = 1;
            while ((line = bufferedReader.readLine()) != null) {
                dataClass.addToList(line);
                dataClass.setProgress(tmpProgress + i * 5);
                i++;
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
