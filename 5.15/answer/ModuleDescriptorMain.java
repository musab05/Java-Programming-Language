import java.lang.module.ModuleDescriptor;
import java.util.Set;

public class ModuleDescriptorMain {

    public static void main(String[] args) {
        System.out.println("=== Java Platform Module System (JPMS) Introspection ===");

        // 1. Inspect the module of the current executing class
        Module thisModule = ModuleDescriptorMain.class.getModule();
        System.out.println("Current class module: " + thisModule);
        System.out.println("Is current module named? " + thisModule.isNamed());
        System.out.println("Module layer: " + thisModule.getLayer());

        // 2. Inspect a core platform module: java.base
        Module baseModule = Object.class.getModule();
        System.out.println("\nCore platform module: " + baseModule.getName());
        System.out.println("Is named? " + baseModule.isNamed());

        ModuleDescriptor descriptor = baseModule.getDescriptor();
        if (descriptor != null) {
            System.out.println("Descriptor name: " + descriptor.name());
            System.out.println("Is automatic? " + descriptor.isAutomatic());
            System.out.println("Is open? " + descriptor.isOpen());

            // Count total packages and exported packages
            Set<String> allPackages = descriptor.packages();
            Set<ModuleDescriptor.Exports> exports = descriptor.exports();
            System.out.println("Total packages in java.base: " + allPackages.size());
            System.out.println("Total exported packages: " + exports.size());

            System.out.println("\nSample exported packages from java.base:");
            exports.stream()
                    .filter(e -> !e.isQualified()) // Publicly exported to all modules
                    .map(ModuleDescriptor.Exports::source)
                    .filter(pkg -> pkg.startsWith("java.time") || pkg.startsWith("java.util.concurrent"))
                    .sorted()
                    .limit(6)
                    .forEach(pkg -> System.out.println("  exports " + pkg + ";"));
        }

        // 3. Inspect module layer boot modules
        ModuleLayer bootLayer = ModuleLayer.boot();
        if (bootLayer != null) {
            Set<Module> modules = bootLayer.modules();
            System.out.println("\nTotal boot layer modules available: " + modules.size());
            System.out.println("First 5 platform modules:");
            modules.stream()
                    .map(Module::getName)
                    .sorted()
                    .limit(5)
                    .forEach(m -> System.out.println("  - " + m));
        }
    }
}
