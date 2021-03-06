package com.example.sp21assignment2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveData;
    private String[] videoExtensions;

    //all loaded files will be here
    private ArrayList<File> allMediaList;

    public void initialize(){
        mutableLiveData = new MutableLiveData<>();
        videoExtensions = new String[]{".mp4",".ts",".mkv",".mov",
                ".3gp",".mv2",".m4v",".webm",".mpeg1",".mpeg2",".mts",".ogm",
                ".bup", ".dv",".flv",".m1v",".m2ts",".mpeg4",".vlc",".3g2",
                ".avi",".mpeg",".mpg",".wmv",".asf"};
        allMediaList = new ArrayList<>();
    }

    public void setData(String movieName){
        mutableLiveData.setValue(movieName);
    }

    public MutableLiveData<String> getMutableLiveData(){
        return mutableLiveData;
    }

    public void addFile(File file){
        allMediaList.add(file);
    }

    public String[] getVideoExtensions(){
        return videoExtensions;
    }

    public ArrayList<File> getAllMediaList(){
        return allMediaList;
    }

    public int getMediaListLength(){
        return allMediaList.size();
    }
}
