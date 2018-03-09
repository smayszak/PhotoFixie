package com.mayheim.Config;

public class Help {

   public static void printHelpOptions(ApplicationOptionsReader appsettings){
       for (ApplicationOption applicationOption : appsettings.getAppOptions()) {
           System.out.println(applicationOption.HelpContext);
       }
   }
}
