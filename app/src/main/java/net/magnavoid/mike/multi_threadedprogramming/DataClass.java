package net.magnavoid.mike.multi_threadedprogramming;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the methods for getting and setting basically all the data.
 * Created by mike on 6/4/15.
 */
public class DataClass {
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private Context context;
    private File file;
    private int progress = 0;
    private ProgressBar progressBar;

    public void addToList(String info) {
        this.list.add(info);
    }

    public List getList() {
        return this.list;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context applicationContext) {
        this.context = applicationContext;
    }

    public ListView getListView() {
        return this.listView;
    }

    public void setListView(ListView aListView) {
        this.listView = aListView;
    }

    public File getFiles() {
        return this.file;
    }

    public void setFiles(File files) {
        this.file = files;
    }

    public ArrayAdapter<String> getArrayAdapter() {
        return arrayAdapter;
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar viewById) {
        this.progressBar = viewById;
    }
}
