package tests;

import com.mayheim.Config.ApplicationOptionsReader;
import com.mayheim.Session.SessionHeader;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationOptionsReaderTest {

    @org.junit.jupiter.api.Test
    void processHelp() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[1];
        args[0] = "-h";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertTrue(header.is_helpMode());

    }


    @org.junit.jupiter.api.Test
    void badParamDontBotherMe() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[2];
        args[0] = "-h";
        args[1] = "-u";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertTrue(header.is_helpMode());

    }

    @org.junit.jupiter.api.Test
    void helpAfterAudit() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[2];
        args[0] = "-a";
        args[1] = "-h";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertTrue(header.is_helpMode());
        assertTrue(header.is_audit());
    }

    @org.junit.jupiter.api.Test
    void auditAndExecuteIsExecute() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[2];
        args[0] = "-a";
        args[1] = "-e";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertFalse(header.is_audit());
    }

    @org.junit.jupiter.api.Test
    void helpWithInputPath() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[3];
        args[0] = "-i";
        args[1] = "/usr/bin";
        args[2] = "-h";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertTrue(header.is_helpMode());
        assertNotNull(header.get_rootDirectory());
    }

    @org.junit.jupiter.api.Test
    void bothPathWithAudit() throws Exception {
        //assert setting -h starts in help mode
        String[] args = new String[5];
        args[0] = "-i";
        args[1] = "/usr/bin";
        args[2] = "-a";
        args[3] = "-o";
        args[4] = "/usr/bin";
        ApplicationOptionsReader applicationOptionsReader = new ApplicationOptionsReader();
        SessionHeader header = applicationOptionsReader.buildSessionHeader(args);
        assertTrue(header.is_audit());

        assertNotNull(header.get_rootDirectory());

        assertNotNull(header.get_outputDirectory());
    }
}