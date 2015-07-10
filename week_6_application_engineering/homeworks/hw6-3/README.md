Which of the following statements are true about choosing and using a shard key?

1. Any update that does not contain the shard key will be sent to all shards.
2. You can change the shard key on a collection if you desire.
3. The shard key must be unique
4. MongoDB can not enforce unique indexes on a sharded collection other than the shard key itself, or indexes prefixed by the shard key.
5. There must be a index on the collection that starts with the shard key.

Answers: 1, 4, 5