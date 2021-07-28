#!/usr/bin/env sh
# Generate the flatbuffer Schemas used by Deephaven

set -e

# Must point to a build of flatc that is from sept 2020 or newer
# In particular, must have this fix: https://github.com/google/flatbuffers/commit/c30a87de6fc741d7dfcf1f721a69d89ca27a0866
if [ -z "$FLATC" ]; then
  echo "FLATC was not set, attempting to use it from path";
  FLATC='flatc'
fi

# invoke flatc
${FLATC} --ts --no-fb-import --no-ts-reexport -o generated/ ../format/Barrage.fbs
