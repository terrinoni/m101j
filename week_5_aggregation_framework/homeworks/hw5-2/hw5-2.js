db.zips.aggregate([{$match: {$or: [{state: "CT"}, {state: "NJ"}]}}, {$match: {pop: {$gt: 25000}}}, {$group: {_id: {state: "$state"}, pop: {$avg: "$pop"}}}])
