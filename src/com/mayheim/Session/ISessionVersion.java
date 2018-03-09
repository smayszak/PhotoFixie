package com.mayheim.Session;
import java.io.Reader;

public interface ISessionVersion {
    void serializeHeader(SessionHeader header);
    void serializeBody(SessionBody body);
    SessionHeader deserializeHeader(Reader inputStream, String version);
    SessionBody deserializeBody(Reader inputStream, String version);
}
