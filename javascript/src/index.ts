// // Presently this file isn't used, might not need to be checked in, but could make the
// // library set up clearer, so i've kept it around for now.
//
// // import * as Barrage from '../generated/Barrage_generated';
// // import * as Message from '../generated/Message_generated';
// // export namespace barrage.flatbuf {
// //     // export import Barrage_generated = Barrage;
// //     export import Message_generated = Message;
// // }
// export * from '../generated/Message_generated';


// import * as Barrage_generated from '../generated/Barrage_generated';
// import * as Message_generated from '../generated/Message_generated';
// import * as Schema_generated from '../generated/Schema_generated';
// export namespace io.deephaven.barrage.flatbuf {
//     export import BarrageRecordBatch = Barrage_generated.io.deephaven.barrage.flatbuf.BarrageRecordBatch;
//     export import BarrageFieldNode = Barrage_generated.io.deephaven.barrage.flatbuf.BarrageFieldNode;
//
//     export import Message = Message_generated.io.deephaven.barrage.flatbuf.Message;
//     export import FieldNode = Message_generated.io.deephaven.barrage.flatbuf.FieldNode;
//     export import Schema = Schema_generated.io.deephaven.barrage.flatbuf.Schema;
//     export import Field = Schema_generated.io.deephaven.barrage.flatbuf.Field;
// }

// import * as Barrage_generated from '../generated/Barrage_generated';

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