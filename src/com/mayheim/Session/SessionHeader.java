package com.mayheim.Session;

import java.util.UUID;

public class SessionHeader {
    private int _totalFileCount;
    private String _version;
    private String _rootDirectory;
    private String _outputDirectory;
    private UUID _sessionUUID;
    private boolean _helpMode;
    private boolean _audit;
    private boolean _execute;

    public int get_totalFileCount() {
        return _totalFileCount;
    }

    public void set_totalFileCount(int _totalFileCount) {
        this._totalFileCount = _totalFileCount;
    }

    public String get_version() {
        return _version;
    }

    public void set_version(String _version) {
        this._version = _version;
    }

    public String get_rootDirectory() {
        return _rootDirectory;
    }

    public void set_rootDirectory(String _rootDirectory) {
        this._rootDirectory = _rootDirectory;
    }

    public String get_outputDirectory() {
        return _outputDirectory;
    }

    public void set_outputDirectory(String _outputDirectory) {
        this._outputDirectory = _outputDirectory;
    }

    public UUID get_sessionUUID() {
        return _sessionUUID;
    }

    public void set_sessionUUID(UUID _sessionUUID) {
        this._sessionUUID = _sessionUUID;
    }

    public void setHelpMode(boolean helpMode) {
        this._helpMode = helpMode;
    }

    public void setAudit(boolean audit) {
        this._audit = audit;
    }

    public void setExecute(boolean execute) {
        this._execute = execute;
    }

    public boolean is_helpMode() {
        return _helpMode;
    }

    public boolean is_audit() {
        return _audit;
    }

    public boolean is_execute() {
        return _execute;
    }
}
