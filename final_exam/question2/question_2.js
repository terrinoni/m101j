use enron
db.messages.aggregate([
	{
		$unwind: "$headers.To"
	}, {
		$project: {
			"_id": 1,
			"headers.From": 1,
			"headers.To": 1
		}
	}, {
		$group: {
			_id: {
				id: "$_id",
				from: "$headers.From",
				to: "$headers.To"
			},
			count: {$sum: 1}
		}
	}, {
		$group: {
			_id: {
				from: "$_id.from",
				to: "$_id.to"
			},
			count: {$sum: 1}
		}
	}, {
		$sort: {
			count: 1
		}
	}, {
		$limit: 5
	}
], {
	allowDiskUse: true
})