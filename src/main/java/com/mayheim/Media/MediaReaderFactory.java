package com.mayheim.Media;

import java.io.File;

public class MediaReaderFactory {

    private static IMediaReader imageReader = new ImageReader();
    private static IMediaReader movieReader = new MovieReader();
    private static IMediaReader notSupportedReader = new NotSupportReader();
    public static IMediaReader GetReader(File file)
    {
        String extension = getExtension(file);
        switch(extension)
        {
            case "jpg":
            case "jpeg":
                 return imageReader;
            case "mov":
            case "mp4":
                return movieReader;
            default:
                return notSupportedReader;
        }
    }


    public static String getExtension(File f) {
        String ext = "";
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
