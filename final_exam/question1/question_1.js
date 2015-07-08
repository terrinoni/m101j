//cd final_exam/question\ 1
//mongorestore dump/

mongo
use enron
db.messages.find({
	"headers.From" : "andrew.fastow@enron.com",
	"headers.To" : "jeff.skilling@enron.com"
}).count()

// or

db.messages.find({
	"headers.From" : "andrew.fastow@enron.com",
	"headers.To" : {
		$all: ["jeff.skilling@enron.com"]
	}
}).count()