import * as Barrage_generated from '../generated/Barrage_generated';
import * as Message_generated from '../generated/Message_generated';
import * as Schema_generated from '../generated/Schema_generated';

export namespace io.deephaven.barrage {
    export const flatbuf = {
        ...Barrage_generated.io.deephaven.barrage.flatbuf,
        ...Message_generated.io.deephaven.barrage.flatbuf,
        ...Schema_generated.io.deephaven.barrage.flatbuf
    };
}