In this problem you will analyze a profile log taken from a mongoDB instance. To start, please download sysprofile.json from Download Handout link and import it with the following command:

```
mongoimport -d m101 -c profile < sysprofile.json
```

Now query the profile data, looking for all queries to the *students* collection in the database *school2*, sorted in order of decreasing latency. What is the latency of the longest running operation to the collection, in milliseconds?

1. 19776550
2. 10000000
3. 5580001
4. 15820
5. 2350

Answer: 4