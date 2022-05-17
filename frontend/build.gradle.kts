tasks {
    task<Exec>("build") {
        commandLine("./package-dist.sh")
    }
}