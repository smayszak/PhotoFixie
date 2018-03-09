package tests;

import com.mayheim.Session.Session;
import com.mayheim.Session.SessionHeader;
import com.mayheim.Session.SessionManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    @Test
    void initializeSession() {
        SessionHeader header = new SessionHeader();
        header.set_rootDirectory( "/home/steve/Pictures");
        SessionManager sessionMgr = new SessionManager(header);
        Session session = sessionMgr.InitializeSession();

        String sessionfilePath = "/home/steve/Pictures" + System.getProperty("file.separator") + session.getSessionHeader().get_sessionUUID() + ".session";
        File sessionFile = new File(sessionfilePath);
        assertNotNull(sessionFile);
        assertTrue(sessionFile.isFile());
    }
}