package com.mayheim.Runtime;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SourceStructure {
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
}

