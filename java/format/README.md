### Updating the flatbuffers generated code

1. Verify that your version of flatc matches the declared dependency:

```bash
$ flatc --version
flatc version 25.2.10
$ grep "dep.fbs.version" java/pom.xml
    <dep.fbs.version>25.2.10</dep.fbs.version>
```

2. Generate the flatbuffer java files by performing the following:

```bash
cd $BARRAGE_HOME
# remove the existing files
rm -rf java/format/src
# regenerate from the .fbs files
flatc --java -o java/format/src/main/java format/*.fbs
# generate license headers
cd java
mvn compile
```

3. Ensure any new files are added to the git repository:

```bash
cd $BARRAGE_HOME
find java/format/src -type f | while read file; do git add $file; done
```
