# Barrage

This Barrage project is the wire-format extension of arrow-flight to support
ticking data sets.

In this project contains a few methods to augment to Apache Arrow Flight to support
out-of-band client-streams for javascript clients.

See documentation here: https://deephaven.io/barrage/docs/

### Versioning
This library is at its 1.0.0 release, and versioning semantics are still being worked out. FlatBuffer enables
forward and backward compatibility for the wire format itself. As FlatBuffers does not follow SemVer, and
every release introduces breaking compile-time and runtime changes, each time the Flatbuffers library is update,
it will amount to a new major release.

<!-- Every Flatbuffers version is technically a breaking change, so our use of semver for this project may make
it seem as though this project sees more changes than it actually does. In reality, changes to the barrage
wire format are quite infrequent, and always done in an attempt to be backwards compatible. As a result,
SemVer applies specifically for using this as a library, but two endpoints can still communicate with
different versions, though new features used by one endpoint may not be understood by an older endpoint.

As such, our versioning scheme is as follows:
```
A.B.C
A - Major version, incremented for breaking changes to the wire format
  B - Minor version, incremented for backwards-compatible changes to schema
    C - Patch version, incremented for backwards-compatible bug fixes
```

The minor version will not be reset to zero when the major version is incremented, to indicate relative compatibility
between two major releases for schema features. That is to say that the minor version is effectively a wire format
version. -->
