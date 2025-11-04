package com.automationexercise.utils;

import com.automationexercise.utils.Logs.LogsManager;

import java.io.IOException;

public class TerminalUtil {

    public static void executeTerminalCommand(String... commandParts){
        try {
            Process process = Runtime.getRuntime().exec(commandParts);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LogsManager.error("Terminal command failed with exit code " + exitCode, String.join(" ",commandParts));
            }
        }catch (IOException | InterruptedException e){
            LogsManager.error("FAiled to execute terminal command ",String.join(" ",commandParts),e.getMessage());
        }
    }
}
