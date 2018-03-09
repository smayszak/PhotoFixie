package com.mayheim.Runtime;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileContext {
    private Path _inPath;
    private Path _outPath;
    public FileContext() throws Exception {
    }

    public void setInpath(String inPath){
        _inPath = Paths.get(inPath);
    }

    public void setOutpath(String outPath){
        _outPath = Paths.get(outPath);
    }

    public Path getInpath(){
        return _inPath;
    }

    public Path getOutpath(){
        return _outPath;
    }

    public void Validate() throws Exception {
        if(_inPath == null){
            System.out.println("No input file path passed. Nothing to do");
            return;
        }
        if(Files.isDirectory(_inPath)){
            System.out.println("Input path: OK");
        }else{
            System.out.println("Input path: INVALID");
            throw new Exception("Input fail path does not exist");
        }

        if(Files.isDirectory(_outPath)){
            System.out.println("Output path: OK");
        }else{

            System.out.println("Output path: DOES NOT EXIST");
            System.out.println("Creating directory...");
            _outPath.toFile().mkdir();
            System.out.println("Created directory: " + _outPath.toString());
        }
    }
}
