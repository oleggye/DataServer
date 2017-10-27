package by.bsac.tcs.server.manager;

import java.net.Socket;

public interface RequestManager {

    void init();

    void shutdown();

    void manage(Socket socket);
}
