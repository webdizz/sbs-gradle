import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginsPrinterPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task('printPlugins') << {
            println 'Current project has next list of plugins:'
            ext.plugins = project.plugins.collect { plugin ->
                plugin.class.simpleName
            }
            println plugins
        }
    }
}