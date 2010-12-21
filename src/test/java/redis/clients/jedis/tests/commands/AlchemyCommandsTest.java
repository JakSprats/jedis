package redis.clients.jedis.tests.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class AlchemyCommandsTest extends JedisCommandTestBase {
    private String testCreateTable() {
        return jedis.createTable("java_table", "id INT,val INT");
    }
    private String testCreateIndex() {
        return jedis.createIndex("index_jt_val", "java_table", "(val)");
    }
    private void testInsertThreeIntoTest() {
        jedis.insert("java_table", "1,11");
        jedis.insert("java_table", "2,22");
        jedis.insert("java_table", "3,33");
    }

    @Test
    public void createTable() {
        String status = testCreateTable();
        assertEquals("OK", status);
    }

    @Test
    public void dropTable() {
        String status = testCreateTable();
        long lstatus  = jedis.dropTable("java_table");
        assertEquals(2, lstatus);
    }

    @Test
    public void createIndex() {
        String stat1 = testCreateTable();
        String stat2 = testCreateIndex();
        assertEquals("OK", stat2);
    }

    @Test
    public void dropIndex() {
        String stat1 = testCreateTable();
        String stat2 = testCreateIndex();
        long lstatus = jedis.dropIndex("index_jt_val");
        assertEquals(1, lstatus);
    }

    @Test
    public void desc() {
        String stat1          = testCreateTable();
        List<String> result   = jedis.desc("java_table");
        List<String> expected = new ArrayList<String>();
        expected.add("id | INT | INDEX: java_table:id:index [BYTES: 0]");
        expected.add("val | INT ");
        expected.add("INFO: KEYS: [NUM: 0 MIN: (null) MAX: (null)] BYTES: [BT-DATA: 0 BT-TOTAL: 177 INDEX: 0 COMBINED_TOTAL: 177]");

        assertEquals(expected, result);
    }

    @Test
    public void insert() {
        testCreateTable();
        jedis.insert("java_table", "1,11");
        List<String> result   = jedis.select("*", "java_table", "id = 1");
        List<String> expected = new ArrayList<String>();
        expected.add("1,11");
        assertEquals(expected, result);
    }

    @Test
    public void insert_ret_size() {
        testCreateTable();
        String result   = jedis.insert_ret_size("java_table", "1,11");
        String expected = "INFO: BYTES: [ROW: 6 BT-DATA: 6 BT-TOTAL: 183 INDEX: 0]";
        assertEquals(expected, result);
    }

    @Test
    public void select() {
        testCreateTable();
        testInsertThreeIntoTest();
        List<String> result = jedis.select("*", "java_table",
                                           "id BETWEEN 1 AND 3");
        List<String> expected = new ArrayList<String>();
        expected.add("1,11");
        expected.add("2,22");
        expected.add("3,33");
        assertEquals(expected, result);
    }

    @Test
    public void scanSelect() {
        testCreateTable();
        testInsertThreeIntoTest();
        List<String> result = jedis.scanSelect("*", "java_table",
                                               "val BETWEEN 11 AND 22");
        List<String> expected = new ArrayList<String>();
        expected.add("1,11");
        expected.add("2,22");
        assertEquals(expected, result);
        result = jedis.scanSelect("*", "java_table", "");
        expected.add("3,33");
        assertEquals(expected, result);
    }

    @Test
    public void update() {
        testCreateTable();
        testInsertThreeIntoTest();
        long lstatus = jedis.update("java_table", "val = 99",
                                    "id BETWEEN 1 AND 2");
        assertEquals(2, lstatus);
        List<String> result = jedis.select("*", "java_table",
                                           "id BETWEEN 1 AND 3");
        List<String> expected = new ArrayList<String>();
        expected.add("1,99");
        expected.add("2,99");
        expected.add("3,33");
        assertEquals(expected, result);
    }

    @Test
    public void sqlDelete() {
        testCreateTable();
        testInsertThreeIntoTest();
        long lstatus = jedis.sqlDelete("java_table", "id BETWEEN 1 AND 2");
        assertEquals(2, lstatus);
        List<String> result = jedis.select("*", "java_table",
                                           "id BETWEEN 1 AND 3");
        List<String> expected = new ArrayList<String>();
        expected.add("3,33");
        assertEquals(expected, result);
    }

    @Test
    public void lua() {
        testCreateTable();
        jedis.insert("java_table", "1,11");
        String lcmd = "return client('SELECT', '*', 'FROM', 'java_table', 'WHERE', 'id = 1');";
        List<String> result   = jedis.lua(lcmd);
        List<String> expected = new ArrayList<String>();
        expected.add("1,11");
        assertEquals(expected, result);
    }

}
