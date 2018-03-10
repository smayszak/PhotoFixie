package com.mayheim.Runtime;

import com.mayheim.Media.IMediaReader;
import com.mayheim.Media.MediaReaderFactory;
import com.mayheim.Session.Session;
import com.mayheim.Session.SessionBody;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private DirectoryTreeNode _directory;
    private Path _rootDirectory;

    public void init(Path rootDirectory) {
        _rootDirectory = rootDirectory;
        if (_rootDirectory == null) {
            System.out.println("Source directory path is either invalid or was not given");
            return;
        }
        _directory = new DirectoryTreeNode();
        System.out.println("Scanning source directory");
       scan(_rootDirectory, _directory);
    }

    private void scan(Path root, DirectoryTreeNode directory) {
        directory.setPath(root);
        File[] files = (new File(root.toString()).listFiles());
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Expanding: "+file.toURI());
                DirectoryTreeNode node = new DirectoryTreeNode();
                node.setPath(Paths.get(file.toURI()));
                directory.addNode(node);
                scan(node.getPath(), node);
            } else {
                directory.incrementFileCount();
            }
        }
    }

    public DirectoryTreeNode getDirectory(){
        return _directory;
    }
    public void PrintTree(){
        int totalFiles = internalPrintTree(_directory);
        System.out.println("Total file count in source directory: " + totalFiles);
    }
    private int internalPrintTree(DirectoryTreeNode tree){
        int cnt = tree.getFileCount();
        System.out.println("");
        System.out.println("===============================");
        System.out.println("Examining directory: " + tree.getPath().toString());
        System.out.println("Child Folders: " + tree.getChildDirectories().size());
        System.out.println("File Count: " + cnt);
        System.out.println("===============================");
        System.out.println("");
        for(DirectoryTreeNode n : tree.getChildDirectories()){
            cnt =  cnt + internalPrintTree(n);
        }
        return cnt;
    }

    private int _directoryPosition = 0;
    private int _filePosition = 0;
    public void ResumeSession(SessionBody sessionBody) {
        //move the reader forward to where it last left off.
        //implements a pretty dumb resume feature
        //given files are loaded in the same order, we can resume.
        //by starting at the correct directory in the index
    }


    private Session _session;
    public void ProcessMoveRequest(Session session){
        if(_directoryPosition > 0){
            //this means we need to advance the _directory to the corect position.

        }
        ProcessDirectory(_directory);

    }

    private void ProcessDirectory(DirectoryTreeNode directory) {
        _session.getSessionBody().updateDirectoryPosition(_directoryPosition);
        File[] files = directory.getFiles();
        for(int index = _filePosition; index < files.length; index++){
            IMediaReader reader = MediaReaderFactory.GetReader(files[index]);
            reader.processFile(files[index], _session.getSessionHeader());
            _session.getSessionBody().updateLastFile(files[index]);
            _session.getSessionBody().updateFilePosition(index);
        }
        _session.getSessionBody().updateLastDirectory(directory);
        _directoryPosition = _directoryPosition++;//increment starting directory
        _filePosition = 0; //reset file index

        //are we processing root or walking left to right?
        if(directory.getChildDirectories().size() == 0){
            return;
        }else{
            for(DirectoryTreeNode node : directory.getChildDirectories()){
                ProcessDirectory(directory);
            }
        }
    }


}

