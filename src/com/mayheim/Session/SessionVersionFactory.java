package com.mayheim.Session;

public class SessionVersionFactory {
    public static ISessionVersion GetSessionRW(String versionString){
        if(versionString == null || versionString == "" || !versionString.startsWith("version"))
            return null;
        String[] kvp = versionString.split(":");
        switch (kvp[1]){
            case "1":
                return new SessionVersionV1();
            default:
                return new SessionVersionV1();
        }
    }
}
