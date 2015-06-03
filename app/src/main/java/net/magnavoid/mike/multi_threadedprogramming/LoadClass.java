package net.magnavoid.mike.multi_threadedprogramming;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 6/2/15.
 */
public class LoadClass implements Runnable{

    public final Thread aThread;
    private Context someContext;

    /**
     *
     * @param context
     */
    public LoadClass(Context context) {
        someContext = context;
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
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;

        try {
            while((line = bufferedReader.readLine()) != null) {
                list.add(line);
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

        final List finalList = list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);

    }
}
