use blog;
db.posts.aggregate([
	{
		$unwind: "$comments"
	},
	{
		$group: {
			_id: "$comments.author",
			num: {
				$sum: 1
			}
		}
	},
	{
		$sort: {
			num: -1
		}
	}
]);