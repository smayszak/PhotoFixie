package com.mayheim.Session;

import java.io.Reader;

public class SessionVersionV1 implements ISessionVersion {

    @Override
    public void serializeHeader(SessionHeader header) {

    }

    @Override
    public void serializeBody(SessionBody body) {

    }

    @Override
    public SessionHeader deserializeHeader(Reader inputStream, String version) {
        return null;
    }

    @Override
    public SessionBody deserializeBody(Reader inputStream, String version) {
        return null;
    }
}
