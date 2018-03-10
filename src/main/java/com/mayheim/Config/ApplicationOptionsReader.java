package com.mayheim.Config;

import com.mayheim.Session.SessionHeader;

public class ApplicationOptionsReader {
    private ApplicationOption[] _appOptions = new ApplicationOption[5];

    public ApplicationOptionsReader() throws Exception {
        initApplicationOptions();
    }

    private void initApplicationOptions() {
        _appOptions[0] = new ApplicationOption("-h Prints help", "-h", false);
        _appOptions[1] =  new ApplicationOption("-a Audit only - prints but does not act", "-a", false);
        _appOptions[2] =  new ApplicationOption("-e Unneeded. Assumed if no audit flag. Executes program and moves all files", "-e", false);
        _appOptions[3] =  new ApplicationOption("-i Input file path. e.g. java -jar PhotoFixie.jar -a -i /source/path", "-i", true);
        _appOptions[4] =  new ApplicationOption("-o Output file path. Where to write e.g. java -jar PhotoFixie.jar -a -o /output/path", "-o", true);
    }

    public ApplicationOption[] getAppOptions() {
        return _appOptions;
    }

    public SessionHeader buildSessionHeader(String[] args) throws Exception {
        SessionHeader header = new SessionHeader();
        if(args.length > 0){
            processCommand(0, args[0], args, header);
        }else{
            System.out.println("Nothing to process");
        }
        return header;
    }

    private void processCommand( int pos, String cmd, String[] src, SessionHeader header) throws Exception {
        int inc = 1;
        if( cmd.toString().compareTo(_appOptions[0].CommandParameter) == 0){
            header.setHelpMode(true);

        }else if (cmd.toString().compareTo(_appOptions[1].CommandParameter) == 0){
            header.setAudit(true);
        }
        else if (cmd.toString().compareTo(_appOptions[2].CommandParameter) == 0){
            System.out.println("Execute flag is set, will ignore audit");
            header.setAudit(false);
            header.setExecute(true);
        }
        else if (cmd.toString().compareTo(_appOptions[3].CommandParameter) == 0){
            if(src[pos + 1] == null){
                throw new Exception("Input file flag present but no file path");
            }else{
                header.set_rootDirectory(src[pos + 1]);
                inc = inc + 1;
            }
        }
        else if (cmd.toString().compareTo(_appOptions[4].CommandParameter) == 0){
            if(src[pos + 1] == null){
                throw new Exception("Output file flag present but no file path");
            }else{
                header.set_outputDirectory(src[pos + 1]);
                inc = inc + 1;
            }
        }
        if(pos + inc >= src.length)
            return;
        processCommand(pos + inc, src[pos + inc], src, header);
    }
}
