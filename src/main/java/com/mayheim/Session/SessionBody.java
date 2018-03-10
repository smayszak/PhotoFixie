package com.mayheim.Session;

import com.mayheim.Runtime.DirectoryTreeNode;
import java.io.File;

public class SessionBody {
    private String _lastDirectory;
    private String _lastFile;
    private int _directoryPosition;
    private int _fileIndex;
    public void Deserialize(File file) {

    }

    public String Serialize() {
        return null;
    }

    public void updateDirectoryPosition(int directoryPosition) {
        _directoryPosition = directoryPosition;
    }

    public void updateFilePosition(int index) {
        _fileIndex = index;
    }

    public void updateLastFile(File file) {
        _lastFile = file.getAbsolutePath();
    }

    public void updateLastDirectory(DirectoryTreeNode directory) {
        _lastDirectory = directory.getPath().toString();
    }
}
