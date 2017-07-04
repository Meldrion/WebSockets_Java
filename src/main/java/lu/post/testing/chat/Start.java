package lu.post.testing.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Start {

    private static final Logger LOGGER = LogManager.getLogger(Start.class);

    public static void main(String[] args) {
        runServer();
    }

    private static void runServer() {
        Server server = new Server("localhost", 8025, "/websockets", ChatEndpoint.class);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            server.start();
            boolean stop = false;
            while (!stop) {

                LOGGER.info("Please press q to stop the server.");
                stop = "q".equalsIgnoreCase(reader.readLine());
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            server.stop();
        }
    }

}
