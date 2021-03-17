#!/usr/bin/env sh

set -e

# Must point to a build of flatc that is from sept 2020 or newer
if [ -z "$FLATC" ]; then
  echo "FLATC was not set, attempting to use it from path";
  FLATC='flatc'
fi

# invoke flatc
${FLATC} --ts --no-fb-import --no-ts-reexport -o generated/ ../format/Barrage.fbs ../format/Schema.fbs ../format/Message.fbs ../format/Tensor.fbs ../format/SparseTensor.fbs
