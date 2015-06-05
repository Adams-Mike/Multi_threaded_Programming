package net.magnavoid.mike.multi_threadedprogramming;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * Main method calls everything.
 */
public class MainActivity extends ActionBarActivity {

    public DataClass dataClass = new DataClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataClass.setContext(getApplicationContext());
        dataClass.setListView((ListView) findViewById(R.id.listView));
        dataClass.setFiles(getFilesDir());
        dataClass.setProgressBar((ProgressBar) findViewById(R.id.progressBar));
        Thread progressBarThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (dataClass.getProgress() != 101) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataClass.getProgressBar().setProgress(dataClass.getProgress());
                        }
                    });
                }
            }
        });
        progressBarThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method creates a new thread for the CreateClass.
     * This class creates the file and saves it to the app data.
     *
     * @param view
     */
    public void create(View view) {
        CreateClass makeFile = new CreateClass(dataClass);
        Thread aThread = new Thread(makeFile);
        aThread.start();
    }

    /**
     * Method reads file from app data and stores it in an ArrayList
     *
     * @param view
     */
    public void load(View view) {
        LoadClass loadFile = new LoadClass(dataClass);
        Thread aThread = new Thread(loadFile);
        aThread.start();
        try {
            aThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dataClass.setArrayAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.list_view, dataClass.getList()));
        dataClass.setListView((ListView) findViewById(R.id.listView));
        dataClass.getListView().setAdapter(dataClass.getArrayAdapter());
    }

    /**
     * Method resets the application and deletes the file/file Directory.
     * @param view
     */
    public void clear(View view) {
        dataClass.getArrayAdapter().clear();
        dataClass.getFiles().delete();
        dataClass.getList().clear();
        dataClass.setProgress(0);
    }
}