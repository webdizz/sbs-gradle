class VmBuilder {
    void build(String targetVm) {
        String[] command = ["/usr/bin/make", targetVm]
        ProcessBuilder pb = new ProcessBuilder(command)
        def env = pb.environment()
        env.put('PACKER_DEBUG', 'true')
        env.put('PACKER_CACHE_DIR', '/opt/packer_cache')
        env.put('PATH', System.getenv()['PATH'])

        Process process = pb.directory(new java.io.File('./'))
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
new VmBuilder().build(targetVm)