package com.mayheim.Media;

import com.mayheim.Session.SessionHeader;

import java.io.File;

public class ImageReader implements IMediaReader {
    public void processFile(File file, SessionHeader settings) {

        String filePath = MapFilePath(file);
        if(DetectCollision(  filePath)){
            filePath = ModifyTargetForCollision(file);
        }
        if(settings.is_execute()){
            if(MoveFile(file, filePath)){
                System.out.println("Src: " + file.getAbsoluteFile());
                System.out.println("MV: " + filePath);
            }else{
                System.out.println("Failed to move src: " + file.getAbsoluteFile());
                System.out.println("To: " + filePath);
            }
        }else{
            System.out.println("Src: " + file.getAbsoluteFile());
            System.out.println("Eval MV: " + filePath);
        }
    }

    private String MapFilePath(File file){
        return "the target directory";
    }

    private boolean DetectCollision(String destination){
        return false;
    }

    private String ModifyTargetForCollision(File file){
        return "new deterministic target";
    }

    private boolean MoveFile(File file, String target){
        return true;//it worked
    }

}
