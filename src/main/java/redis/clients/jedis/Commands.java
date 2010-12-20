package redis.clients.jedis;

import java.util.Map;

import redis.clients.jedis.BinaryClient.LIST_POSITION;

public interface Commands {

    public void set(final String key, final String value);

    public void get(final String key);

    public void exists(final String key);

    public void del(final String... keys);

    public void type(final String key);

    public void keys(final String pattern);

    public void rename(final String oldkey, final String newkey);

    public void renamenx(final String oldkey, final String newkey);

    public void expire(final String key, final int seconds);

    public void expireAt(final String key, final long unixTime);

    public void ttl(final String key);

    public void move(final String key, final int dbIndex);

    public void getSet(final String key, final String value);

    public void mget(final String... keys);

    public void setnx(final String key, final String value);

    public void setex(final String key, final int seconds, final String value);

    public void mset(final String... keysvalues);

    public void msetnx(final String... keysvalues);

    public void decrBy(final String key, final long integer);

    public void decr(final String key);

    public void incrBy(final String key, final long integer);

    public void incr(final String key);

    public void append(final String key, final String value);

    public void substr(final String key, final int start, final int end);

    public void hset(final String key, final String field, final String value);

    public void hget(final String key, final String field);

    public void hsetnx(final String key, final String field, final String value);

    public void hmset(final String key, final Map<String, String> hash);

    public void hmget(final String key, final String... fields);

    public void hincrBy(final String key, final String field, final long value);

    public void hexists(final String key, final String field);

    public void hdel(final String key, final String field);

    public void hlen(final String key);

    public void hkeys(final String key);

    public void hvals(final String key);

    public void hgetAll(final String key);

    public void rpush(final String key, final String string);

    public void lpush(final String key, final String string);

    public void llen(final String key);

    public void lrange(final String key, final int start, final int end);

    public void ltrim(final String key, final int start, final int end);

    public void lindex(final String key, final int index);

    public void lset(final String key, final int index, final String value);

    public void lrem(final String key, int count, final String value);

    public void lpop(final String key);

    public void rpop(final String key);

    public void rpoplpush(final String srckey, final String dstkey);

    public void sadd(final String key, final String member);

    public void smembers(final String key);

    public void srem(final String key, final String member);

    public void spop(final String key);

    public void smove(final String srckey, final String dstkey,
            final String member);

    public void scard(final String key);

    public void sismember(final String key, final String member);

    public void sinter(final String... keys);

    public void sinterstore(final String dstkey, final String... keys);

    public void sunion(final String... keys);

    public void sunionstore(final String dstkey, final String... keys);

    public void sdiff(final String... keys);

    public void sdiffstore(final String dstkey, final String... keys);

    public void srandmember(final String key);

    public void zadd(final String key, final double score, final String member);

    public void zrange(final String key, final int start, final int end);

    public void zrem(final String key, final String member);

    public void zincrby(final String key, final double score,
            final String member);

    public void zrank(final String key, final String member);

    public void zrevrank(final String key, final String member);

    public void zrevrange(final String key, final int start, final int end);

    public void zrangeWithScores(final String key, final int start,
            final int end);

    public void zrevrangeWithScores(final String key, final int start,
            final int end);

    public void zcard(final String key);

    public void zscore(final String key, final String member);

    public void watch(final String... keys);

    public void sort(final String key);

    public void sort(final String key, final SortingParams sortingParameters);

    public void blpop(final String[] args);

    public void sort(final String key, final SortingParams sortingParameters,
            final String dstkey);

    public void sort(final String key, final String dstkey);

    public void brpop(final String[] args);

    public void brpoplpush(final String source, final String destination,
            final int timeout);

    public void zcount(final String key, final double min, final double max);

    public void zrangeByScore(final String key, final double min,
            final double max);

    public void zrangeByScore(final String key, final String min,
            final String max);

    public void zrangeByScore(final String key, final double min,
            final double max, final int offset, int count);

    public void zrangeByScoreWithScores(final String key, final double min,
            final double max);

    public void zrangeByScoreWithScores(final String key, final double min,
            final double max, final int offset, final int count);

    public void zremrangeByRank(final String key, final int start, final int end);

    public void zremrangeByScore(final String key, final double start,
            final double end);

    public void zunionstore(final String dstkey, final String... sets);

    public void zunionstore(final String dstkey, final ZParams params,
            final String... sets);

    public void zinterstore(final String dstkey, final String... sets);

    public void zinterstore(final String dstkey, final ZParams params,
            final String... sets);

    public void strlen(final String key);

    public void lpushx(final String key, final String string);

    public void persist(final String key);

    public void rpushx(final String key, final String string);

    public void echo(final String string);

    public void linsert(final String key, final LIST_POSITION where,
            final String pivot, final String value);

    public void bgrewriteaof();

    public void bgsave();

    public void lastsave();

    public void save();

    public void configSet(final String parameter, final String value);

    public void configGet(final String pattern);

    public void configResetStat();

    /* ALCHEMY DATABASE START */
    public void createTable(final String tablename,
                            final String column_definitions);
    public void dropTable(final String tablename);
    public void desc(final String tablename);
    public void dump(final String tablename);
    public void dumpToMysql(final String tablename,
                            final String mysql_tablename);
    public void dumpToFile(final String tablename, final String filename);

    public void createIndex(final String indexname,
                            final String tablename,
                            final String column);
    public void dropIndex(final String indexname);

    public void insert(final String tablename, final String values_list);
    public void insert_ret_size(final String tablename,
                                final String values_list);
    public void select(final String column_list,
                       final String tablename,
                       final String where_clause);
    public void scanSelect(final String column_list,
                           final String tablename,
                           final String where_clause);
    public void update(final String tablename,
                       final String update_list,
                       final String where_clause);
    public void sqlDelete(final String tablename,
                          final String where_clause);

    public void lua(final String command);
    /* ALCHEMY DATABASE END */
}
