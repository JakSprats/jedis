package redis.clients.jedis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Common interface for sharded and non-sharded Jedis
 */
public interface JedisCommands {
    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    String type(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long ttl(String key);

    String getSet(String key, String value);

    Long setnx(String key, String value);

    String setex(String key, int seconds, String value);

    Long decrBy(String key, long integer);

    Long decr(String key);

    Long incrBy(String key, long integer);

    Long incr(String key);

    Long append(String key, String value);

    String substr(String key, int start, int end);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hsetnx(String key, String field, String value);

    String hmset(String key, Map<String, String> hash);

    List<String> hmget(String key, String... fields);

    Long hincrBy(String key, String field, long value);

    Boolean hexists(String key, String field);

    Long hdel(String key, String field);

    Long hlen(String key);

    Set<String> hkeys(String key);

    Collection<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    Long rpush(String key, String string);

    Long lpush(String key, String string);

    Long llen(String key);

    List<String> lrange(String key, int start, int end);

    String ltrim(String key, int start, int end);

    String lindex(String key, int index);

    String lset(String key, int index, String value);

    Long lrem(String key, int count, String value);

    String lpop(String key);

    String rpop(String key);

    Long sadd(String key, String member);

    Set<String> smembers(String key);

    Long srem(String key, String member);

    String spop(String key);

    Long scard(String key);

    Boolean sismember(String key, String member);

    String srandmember(String key);

    Long zadd(String key, double score, String member);

    Set<String> zrange(String key, int start, int end);

    Long zrem(String key, String member);

    Double zincrby(String key, double score, String member);

    Long zrank(String key, String member);

    Long zrevrank(String key, String member);

    Set<String> zrevrange(String key, int start, int end);

    Set<Tuple> zrangeWithScores(String key, int start, int end);

    Set<Tuple> zrevrangeWithScores(String key, int start, int end);

    Long zcard(String key);

    Double zscore(String key, String member);

    List<String> sort(String key);

    List<String> sort(String key, SortingParams sortingParameters);

    Long zcount(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max, int offset,
            int count);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max,
            int offset, int count);

    Long zremrangeByRank(String key, int start, int end);

    Long zremrangeByScore(String key, double start, double end);

    Long linsert(String key, Client.LIST_POSITION where, String pivot,
            String value);

    /* ALCHEMY DATABASE START */
    String createTable(final String tablename, final String column_definitions);
    Long   dropTable(final String tablename);
    List<String> desc(final String tablename);
    List<String> dump(final String tablename);
    List<String> dumpToMysql(final String tablename,
                             final String mysql_tablename);
    List<String> dumpToFile(final String tablename, final String filename);

    String createIndex(final String indexname,
                       final String tablename,
                       final String column_definitions);
    Long   dropIndex(final String indexname);

    String insert(final String tablename, final String values_list);
    String insert_ret_size(final String tablename, final String values_list);
    List<String> select(final String column_list,
                        final String tablename,
                        final String where_clause);
    List<String> scanSelect(final String column_list,
                            final String tablename,
                            final String where_clause);
    Long update(final String tablename,
                final String update_list,
                final String where_clause);
    Long sqlDelete(final String tablename,
                   final String where_clause);

    List<String> lua(final String command);
    /* ALCHEMY DATABASE END */
}
