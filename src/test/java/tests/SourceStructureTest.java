package tests;

import com.mayheim.Config.ApplicationOptionsReader;
import com.mayheim.Runtime.SourceStructure;
import com.mayheim.Session.SessionHeader;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SourceStructureTest {
    @org.junit.jupiter.api.Test
    void canReadSingleDirectory() throws Exception {
        String[] args = new String[2];
        args[0] = "-i";
        args[1] = "/home/steve/Pictures";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        SourceStructure structure = new SourceStructure();
        structure.init(Paths.get(header.get_rootDirectory()));
        assertNotNull(structure.getDirectory());

    }

    @org.junit.jupiter.api.Test
    void canTraverseDiretory() throws Exception {
        String[] args = new String[3];
        args[0] = "-i";
        args[1] = "/home/steve/Pictures";
        args[2] = "-e";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        SourceStructure structure = new SourceStructure();
        structure.init(Paths.get(header.get_rootDirectory()));
        structure.PrintTree();
        assertNotNull(structure.getDirectory());
    }
}