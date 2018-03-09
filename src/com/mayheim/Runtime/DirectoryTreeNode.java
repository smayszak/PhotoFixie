package com.mayheim.Runtime;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeNode {
    private Path _path;
    private List<DirectoryTreeNode> _subFolders = new ArrayList<DirectoryTreeNode>();
    private int _fileCount;

    public void setPath(Path path){
        _path = path;
    }
    public void setSubFolders(List<DirectoryTreeNode> subFolders){
        _subFolders = subFolders;
    }
    public void setFileCount(int fileCount){
        _fileCount = fileCount;
    }

    public void addNode(DirectoryTreeNode node) {
        _subFolders.add(node);
    }

    public Path getPath() {
        return _path;
    }
    public void incrementFileCount(){
        this._fileCount = _fileCount + 1;
    }

    public List<DirectoryTreeNode> getChildDirectories(){
        return _subFolders;
    }

    public int getFileCount() {
        return _fileCount;
    }
}
