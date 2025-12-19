# Java Release

The Java release to Maven Central uses the Sonatype's publishing service. See https://central.sonatype.org/publish/publish-portal-maven/
for more information.

A developer must have a GPG keypair created and uploaded to a public keyserver, as well as a Sonatype account with permissions to publish
to the `io.deephaven` namespace.

To update versions for a release, edit the pom files, or run
```sh
mvn versions:set -DnewVersion=x.y.z
```
to automatically update versions across all poms.

To perform a release, run:

```sh
./mvnw clean deploy -Prelease
```

This will activate the `release` profile, which will generate javadoc and source jars and sign the artifacts before deploying to Sonatype.

Snapshots can be deployed using the same steps.
