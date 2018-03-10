package com.mayheim.Runtime;

import com.mayheim.Session.Session;

import java.nio.file.Paths;

public class Processor {

    private Session _session;
    private FileManager _fileManager;

    public void Run(Session session){

        this._session = session;
        _fileManager = new FileManager();
        _fileManager.init(Paths.get(_session.getSessionHeader().get_rootDirectory()));
        _fileManager.PrintTree();
        _fileManager.ResumeSession(_session.getSessionBody());
        _fileManager.ProcessMoveRequest(_session);
    }
}
