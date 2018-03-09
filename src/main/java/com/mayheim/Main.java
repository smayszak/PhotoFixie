package com.mayheim;

import com.mayheim.Session.RunSequence;

public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length == 0)
            return;
        RunSequence application = new RunSequence();
        application.Run(args);
    }
}
