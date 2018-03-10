package com.mayheim.Media;

import com.mayheim.Session.SessionHeader;

import java.io.File;

public interface IMediaReader {
    void processFile(File file, SessionHeader settings);
}
