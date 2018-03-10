package com.mayheim.Media;

import com.mayheim.Session.SessionHeader;

import java.io.File;

public class NotSupportReader implements IMediaReader {
    @Override
    public void processFile(File file, SessionHeader settings) {
        System.out.println("Skipping file, unsupported format: "  + file.getAbsoluteFile());
    }
}
