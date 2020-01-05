package CommandHelper;

import Utility.ILogger;
import org.apache.commons.lang3.ArrayUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class CommandLineExecutor implements ILogger {

    private CommandLineExecutor() {
    }

    /**
     * Run system commands and get response back.
     * @param file
     *            {@link String}
     * @param args
     *            {@link String}[] containing arguments that are to be passed to
     *            executable
     * @return {String}
     */
    public static String execFile(final String file, final String... args) {
        String response = null;
        if (file == null || file.trim().isEmpty()) {
            return null;
        }
        return execCommand(ArrayUtils.addAll(new String[] { "sh", file.trim() }, args));
    }

    /**
     * Run system commands and get response back.
     * @param command
     *            {@link String}
     * @return {@link String response of Command line}
     */
    public static String exec(final String command) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }
        String osName = System.getProperty("os.name").toLowerCase();
        String[] commandStr = new String[3];
        if(osName.contains("win")){
            commandStr = new String[]{"cmd", "/c", command.trim()};
        }else if(osName.contains("linux") || osName.contains("mac")){
            commandStr = new String[]{ "sh", "-c", command.trim() };
        }
        return execCommand(commandStr);
    }

    private static String execCommand(final String[] command) {
        if (command == null || command.length == 0) {
            return null;
        }
        log.info("executing command : " + String.join(" ", command));
        String osName = System.getProperty("os.name").toLowerCase();
        ProcessBuilder builder;
        Process process = null;
        try {
            if(osName.contains("win")){
                process = Runtime.getRuntime().exec(String.join(" ",command));
            } else if(osName.contains("linux") || osName.contains("mac")){
                builder = new ProcessBuilder(command);
                process = builder.start();
            }
            process.waitFor(60, TimeUnit.SECONDS);
            BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line, output = "";
            while((line = reader.readLine()) != null) {
                output = line + output;
            }
            log.info("response ====== " + output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    private static CommandLineResponse execCommand(final String[] command) {
//        if (command == null || command.length == 0) {
//            return null;
//        }
//        log.info("executing command : " + String.join(" ", command));
//        try {
//            CommandLineResponse response = new CommandLineResponse();
//            ProcessBuilder builder = new ProcessBuilder(command);
//            Process process = builder.start();
//            process.waitFor(60, TimeUnit.SECONDS);
//            response.setStdOut(IOUtils.toString(process.getInputStream()).trim());
//            response.setErrOut(IOUtils.toString(process.getErrorStream()).trim());
//            response.setExitCode(process.exitValue());
//            log.info("response ====== "+ response.toString());
//            log.debug(response);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
