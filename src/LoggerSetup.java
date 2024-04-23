import java.io.IOException;
import java.util.logging.*;

public class LoggerSetup {

    private static final Logger logger = Logger.getLogger(LoggerSetup.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.WARNING);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
