package com.mayheim.Config;

public class ApplicationOption {
    public String HelpContext;
    public String CommandParameter;
    public boolean HasParameter;

    public ApplicationOption(String helpContext, String cmdParam, boolean hasParam){
        HelpContext = helpContext;
        CommandParameter = cmdParam;
        HasParameter = hasParam;
    }
}
