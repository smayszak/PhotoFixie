package com.mayheim.Runtime;

import com.mayheim.Config.ApplicationOptionsReader;
import com.mayheim.Config.Help;
import com.mayheim.Session.Session;
import com.mayheim.Session.SessionHeader;
import com.mayheim.Session.SessionManager;

public class RunSequence {
    public void Run(String[] args) throws Exception {
        for (String arg : args) {
            System.out.println(arg);

        }
        ApplicationOptionsReader applicationApplicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationApplicationOptionsReader.buildSessionHeader(args);

        if(header.is_helpMode()){
            Help.printHelpOptions(applicationApplicationOptionsReader);
            return;
        }

        SessionManager sessionMgr = new SessionManager(header);
        Session session = sessionMgr.InitializeSession();
        Processor program = new Processor();
        program.Run(session);

        System.out.println("exit");
        // write your code here
    }
}
