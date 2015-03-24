class VmBuilder {
    void build(String targetVm, String workingDirectory) {
        String[] command = ["/usr/bin/make", targetVm]
        ProcessBuilder pb = new ProcessBuilder(command)
        def env = pb.environment()
        env.put('PACKER_DEBUG', 'true')
        env.put('PACKER_CACHE_DIR', '/opt/packer_cache')
        env.put('PATH', System.getenv()['PATH'])
        Process process = pb.directory(new File(workingDirectory))
                .redirectErrorStream(true)
                .start()
        process.inputStream.eachLine { println it }
        process.waitFor();
    }
}

String targetVm = 'virtualbox/centos70-docker-devbox'
if (args && args[0]) {
    targetVm = args[0]
}
String workingDirectory = './'
if (args && args[1]) {
    workingDirectory = args[1]
}
new VmBuilder().build(targetVm, workingDirectory)