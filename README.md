Protobuff compiler is not platform independent.

This snippet in pom.xml downloads the compiler needed to read .proto files and generate required Java proto classes
```xml
<plugin>
    <groupId>org.xolstice.maven.plugins</groupId>
    <artifactId>protobuf-maven-plugin</artifactId>
    <version>${protobuf.maven.plugin.version}</version>
    <configuration>
        <protocArtifact>com.google.protobuf:protoc:${protoc.version}:exe:${os.detected.classifier}</protocArtifact>
        <pluginId>grpc-java</pluginId>
        <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
        <protoSourceRoot>
            ${basedir}/src/main/proto/
        </protoSourceRoot>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>compile-custom</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Working of proto compiler

When we run mvn clean install, under target directory, we find the OS specific compiler being downloaded:
```
target/protoc-plugins/protoc-3.25.5-osx-aarch_64.exe
```
This protoc sees the .proto file under ``src/main/proto`` and creates its corresponding java files under ``generated-sources``