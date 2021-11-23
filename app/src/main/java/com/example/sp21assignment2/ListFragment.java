package com.example.sp21assignment2;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static String[] storagePaths;
    private static File storage;
    private static Uri fileUri;
    private SharedViewModel sharedViewModel;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storagePaths = StorageUtil.getStorageDirectories(getActivity());
        for (String path : storagePaths) {
            storage = new File(path);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    loadDirectoryFiles(storage);
                }
            });
        }
        customAdapter = new CustomAdapter(getActivity());
        recyclerView.setAdapter(customAdapter);
    }

    public void loadDirectoryFiles(File directory){
        File[] fileList = directory.listFiles();
        if(fileList != null && fileList.length > 0){
            for (int i=0; i<fileList.length; i++){
                if(fileList[i].isDirectory()){
                    loadDirectoryFiles(fileList[i]);
                }
                else {
                    String name = fileList[i].getName().toLowerCase();
                    for (String extension: sharedViewModel.getVideoExtensions()){
                        //check the type of file
                        if(name.endsWith(extension)){
                            sharedViewModel.addFile(fileList[i]);
                            //when we found file
                            break;
                        }
                    }
                }
            }
        }
    }
}