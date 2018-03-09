package com.mayheim.Session;

import com.mayheim.Runtime.SourceStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.UUID;

public class SessionManager {
    private Hashtable<Integer, Session> _sessions = new Hashtable();
    private SessionHeader _header;
    int totalSessions = 0;
    public SessionManager(SessionHeader header){
        _header = header;
    }

    public Session InitializeSession(){
        LoadSessions();
        if(totalSessions > 0){
            int selection = OfferOldSession();
            if(selection == -1) {
                //dont wish to resume, so create new
                return CreateSession();
            }else{
                Session session = _sessions.get(selection);
                return session;
            }
        }else {
            //no old sessions to resume, so create a new one.
           return CreateSession();
        }
    }

    private Integer OfferOldSession(){
        System.out.println("Your source directory contains old sessions.");
        System.out.println("You can resume from this old session if you want.");
        System.out.println("Resuming picks up where you last left off.");
        System.out.println("Like if the program ran a long time and hung or crashed.");
        System.out.println("Here are the sessions we found:");
        for(int i = 0; i < totalSessions; i++){
            Session session = _sessions.get(i);
            System.out.println(session.getSessionStamp());
        }
        System.out.println("Type the session ID you wish to resume, otherwise just press enter");
        String line = System.console().readLine();
        int index = Integer.parseInt(line);
        return index;
    }

    private void LoadSessions(){
        Path sourceDirectory = Paths.get(_header.get_rootDirectory());
        File[] files = (new File(sourceDirectory.toString()).listFiles());
        for (File file : files) {
            if (file.isDirectory() == false) {
                if (file.getName().contains(".session")) {
                    Session session = new Session();
                    //if the file fails to load, returns false
                    if(session.Init(file, totalSessions) ){
                        _sessions.put(totalSessions, session);
                        totalSessions++;
                    }
                }
            }
        }
    }

    private Session CreateSession(){

        Session newSession = new Session();
        newSession.setSessionHeader(_header);
        SourceStructure source = new SourceStructure();
        source.init(Paths.get(_header.get_rootDirectory()));
        source.PrintTree();

        UUID sessionId = UUID.randomUUID();
        _header.set_sessionUUID(sessionId);
        File sessionFile = new File(_header.get_rootDirectory().toString() + System.getProperty("file.separator")  + sessionId.toString() + ".session" );
        //Create the file
        try {
            sessionFile.createNewFile();
            FileWriter writer = new FileWriter(sessionFile);
            writer.write("{ \"version\":\"1\" }");
            writer.close();

        }catch(IOException exception){
            System.out.println("Failed to create new file : " + sessionFile.toString());
            System.out.println("You can still continue, sessions are points to resume processsing.");
            System.out.println("Processing large amounts of images can take a very long time, the session can restore all state");
            System.out.println("So if the process stops or fails, you don't have to start over");
        }

        return newSession;
    }
}

