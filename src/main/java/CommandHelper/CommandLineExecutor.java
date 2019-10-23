package CommandHelper;

import Utility.ILogger;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tika.io.IOUtils;
import java.util.concurrent.TimeUnit;

public class CommandLineExecutor implements ILogger {

    private CommandLineExecutor() {
    }

    /**
     * Run system commands and get response back.
     *
     * @param file
     *            {@link String}
     * @param args
     *            {@link String}[] containing arguments that are to be passed to
     *            executable
     *
     * @return {@link CommandLineResponse}
     */
    public static CommandLineResponse execFile(final String file, final String... args) {
        if (file == null || file.trim().isEmpty()) {
            return null;
        }
        return execCommand(ArrayUtils.addAll(new String[] { "sh", file.trim() }, args));
    }

    /**
     * Run system commands and get response back.
     *
     * @param command
     *            {@link String}
     * @return {@link CommandLineResponse}
     */
    public static CommandLineResponse exec(final String command) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }
        return execCommand(new String[] { "sh", "-c", command.trim() });
    }

    private static CommandLineResponse execCommand(final String[] command) {
        if (command == null || command.length == 0) {
            return null;
        }
        log.info("executing command : " + String.join(" ", command));
        try {
            CommandLineResponse response = new CommandLineResponse();
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
            process.waitFor(60, TimeUnit.SECONDS);
            response.setStdOut(IOUtils.toString(process.getInputStream()).trim());
            response.setErrOut(IOUtils.toString(process.getErrorStream()).trim());
            response.setExitCode(process.exitValue());
            log.info("response ====== "+ response.toString());
            log.debug(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
