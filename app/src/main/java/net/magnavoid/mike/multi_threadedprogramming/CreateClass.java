package net.magnavoid.mike.multi_threadedprogramming;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class creates the data and stores it to the local app data.
 * Created by mike on 6/2/15.
 */
public class CreateClass implements Runnable {

    DataClass dataClass;

    public CreateClass(DataClass dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {

        String filename = "numbers.txt";
        File file = new File(dataClass.getFiles(), filename);
        Handler handler = new Handler(Looper.getMainLooper());
        String toastText = "";

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                toastText = "Problems: " + e.getMessage();
            }
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            toastText = "Problems: " + e.getMessage();
        }

        if (fileWriter == null) {
            throw new AssertionError();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 1; i < 11; i++) {
            try {

                dataClass.setProgress(i * 5);
                bufferedWriter.write((i + "\n"));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    toastText = "Problems: " + e.getMessage();
                }
            } catch (IOException e) {
                toastText = "Problems: " + e.getMessage();

            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            toastText = "Problems: " + e.getMessage();
        }

        if (toastText.isEmpty()) {
            toastText = "File Created: " + filename;
        }

        final String finalToastText = toastText;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(dataClass.getContext(), finalToastText, Toast.LENGTH_SHORT);
                toast.show();
            }
        }, 1000);
    }
}
