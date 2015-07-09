Please use the Enron dataset you imported for the previous problem. For this question you will use the aggregation framework to figure out pairs of people that tend to communicate a lot. To do this, you will need to unwind the To list for each message. 

This problem is a little tricky because a recipient may appear more than once in the To list for a message. You will need to fix that in a stage of the aggregation before doing your grouping and counting of (sender, recipient) pairs. 

Which pair of people have the greatest number of messages in the dataset?

1. susan.mara@enron.com to jeff.dasovich@enron.com
2. susan.mara@enron.com to richard.shapiro@enron.com
3. soblander@carrfut.com to soblander@carrfut.com
4. susan.mara@enron.com to james.steffes@enron.com
5. evelyn.metoyer@enron.com to kate.symes@enron.com
6. susan.mara@enron.com to alan.comnes@enron.com

Answer: 1