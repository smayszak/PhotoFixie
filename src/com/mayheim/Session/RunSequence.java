package com.mayheim.Session;

import com.mayheim.Config.ApplicationOptionsReader;
import com.mayheim.Config.Help;

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

        System.out.println("exit");
        // write your code here
    }
}
