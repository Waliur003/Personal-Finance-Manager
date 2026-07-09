package integration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

enum MenuOptions {
    ACCOUNTS,
    INSIGHTS,
    DATA_AUDIT,
    REPORTS,
    STORAGE,
    EXIT
}

class ModuleRegistry {

    private final Map<String, AppModule> modules;

    public ModuleRegistry() {
        this.modules = new HashMap<>();
    }

    public void registerModule(AppModule module) {
        modules.put(module.getModuleName(), module);
    }

    public AppModule getModule(String name) {
        return modules.get(name);
    }

    public Collection<AppModule> getAllModules() {
        return modules.values();
    }

    public void validateRegistrations() {
    }
}

final class MenuUtil {

    private MenuUtil() {
    }

   public static String promptChoice(Scanner scanner, String prompt, List<String> options) {
    while (true) {
        System.out.println(prompt);

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Enter choice: ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice >= 1 && choice <= options.size()) {
                return options.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            
        }

        System.err.println("Invalid choice. Please try again.");
    }
}
public static boolean promptYesNo(Scanner scanner, String prompt) {
    while (true) {
        System.out.print(prompt + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("y") || input.equals("yes")) {
            return true;
        }

        if (input.equals("n") || input.equals("no")) {
            return false;
        }

        System.err.println("Please enter y or n.");
    }
}

public class MainOrchestrator {

    private final ModuleRegistry registry;

    public MainOrchestrator() {
        this.registry = new ModuleRegistry();
    }

    public static void main(String[] args) {
        MainOrchestrator orchestrator = new MainOrchestrator();
        orchestrator.startApplication();
    }

    public void startApplication() {
    }

    public void runMainMenuLoop() {
    }

    public void dispatchSelection(MenuOptions option) {
    }

    public void shutdownApplication() {
    }
}
