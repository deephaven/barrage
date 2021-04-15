# Prerequisites

## node/npm

Install [node/npm](https://nodejs.org/en/download/) if you haven't already. This was tested with node 6.9.

## [flatbuffers](https://github.com/google/flatbuffers)

You must have `flatc` on your path, or set in the environment variable `FLATC`. The version used must be newer than Sept 2020, as we need [this fix](https://github.com/google/flatbuffers/commit/c30a87de6fc741d7dfcf1f721a69d89ca27a0866). They do not have a tagged version yet with this fix (as of this writing).

To clone, build, and install flatc:

```
git clone https://github.com/google/flatbuffers.git
cd flatbuffers
git checkout c30a87de6fc741d7dfcf1f721a69d89ca27a0866
cmake -G "Unix Makefiles"
make install
```

# Building

## `build.sh`

Uses flatc to generate the proto files necessary. Must run this before running an `npm run build`

## `npm install`

Installs all necessary dependencies for building

## `npm run build`

Build the project and output it to the `dist` folder

## `npm run tsc`

Generate debug types for TypeScript
