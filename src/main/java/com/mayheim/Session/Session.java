package com.mayheim.Session;
import java.io.*;
import java.text.SimpleDateFormat;

public class Session {

    private File _sessionFile;
    private int _sessionShortRef;
    private SessionHeader _header;
    private SessionBody _body;
    private ISessionVersion _sessionReader;

    public Session() {

    }

    public void setSessionFile(File file){
        this._sessionFile =file;
    }
    public void setSessionHeader(SessionHeader header){
        this._header =header;
    }
    public SessionHeader getSessionHeader(){
        return this._header;
    }

    public boolean Init(File file, int indexRef) {
        _sessionShortRef = indexRef;
        _sessionFile = file;
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String version = br.readLine();
            _sessionReader = SessionVersionFactory.GetSessionRW(version);
            if(_sessionReader == null){
                System.out.println("Could not load old session");
                return false;
            }

            _header = _sessionReader.deserializeHeader(br, version);
            _body = _sessionReader.deserializeBody(br, version);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getSessionStamp(){
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        sb.append("===============================");
        sb.append("Session ID: " + _sessionShortRef);
        sb.append("Session Stamp: " + _sessionFile.getName());
        sb.append("Session Date: " + sdf.format(_sessionFile.lastModified()));
        sb.append("Session image count: " + _header.get_totalFileCount());
        return sb.toString();
    }
}

