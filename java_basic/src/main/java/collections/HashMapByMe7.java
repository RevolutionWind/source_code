package collections;

import java.util.Map;
import java.util.Objects;

/**
 * @author walker
 * @date 2020-07-01
 */
public class HashMapByMe7<K, V> {
    /**
     * 默认HashMap数组的长度
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 最大HashMap数组的长度
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 默认扩容时的负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 扩容时的数组
     */
    static final Entry<?, ?>[] EMPTY_TABLE = {};
    /**
     * 扩容时的Entry数组，数组长度必须为2的n次方（n>=0, n为整数）
     */
    Entry<K, V>[] table = (Entry<K, V>[]) EMPTY_TABLE;
    /**
     * 实际的Entry个数
     */
    int size;
    /**
     * 扩容阈值
     * 当Entry数组长度达到此值时
     */
    int threshold;
    /**
     * 实际的扩容负载因子
     */
    final float loadFactor;
    /**
     * 与此实例关联的随机化值，应用于
     * 密钥的哈希代码，使哈希冲突更难查找。如果0，则
     * 替代哈希被禁用。
     */
    int hashSeed = 0;

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        int hash;

        /**
         * new entry
         */
        Entry(int h, K k, V v, Entry<K, V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Entry e = (Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (Objects.equals(k1, k2)) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                return Objects.equals(v1, v2);
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        @Override
        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public HashMapByMe7(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("不合法的初始化长度参数: " + initCapacity);
        }
        if (initCapacity > MAXIMUM_CAPACITY) {
            initCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("不合法的负载因子参数：" + loadFactor);
        }
        threshold = initCapacity;
        this.loadFactor = loadFactor;
    }

    public HashMapByMe7(int initCapacity) {
        this(initCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMapByMe7() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 初始化数组
     *
     * @param toSize 扩容的长度
     */
    private void inflateTable(int toSize) {
        // 找到>=toSize的2的次方数
        int capacity = roundUpToPowerOf2(toSize);
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        table = new Entry[capacity];
    }


    /**
     * 新增Entry元素
     *
     * @param hash        hashCode
     * @param key         key
     * @param value       value
     * @param bucketIndex 数组下标
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size > threshold) && (null != table[bucketIndex])) {
            // 扩容
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    /**
     * 扩容函数
     *
     * @param newCapacity 扩容之后的数组长度
     */
    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        // 当现在的数组长度已经到了Integer的最大容量
        if (oldCapacity == Integer.MAX_VALUE) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    /**
     * 将现有的表移动到新表中
     *
     * @param newTable 新表
     */
    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry<K, V> e : table) {
            while (e != null) {
                Entry<K, V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    int indexFor(int h, int length) {
        return h & (length - 1);
    }


    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }

    /**
     * 初始化哈希掩码值。我们将初始化推迟到真正需要时。
     * 一般情况下，只要数组长度不达到Integer的最大值，就不会触发该算法
     */
//    final boolean initHashSeedAsNeeded(int capacity) {
//        boolean currentAltHashing = hashSeed != 0;
//        boolean useAltHashing = sun.misc.VM.isBooted() &&
//                (capacity >= HashMap.Holder.ALTERNATIVE_HASHING_THRESHOLD);
//        boolean switching = currentAltHashing ^ useAltHashing;
//        if (switching) {
//            hashSeed = useAltHashing
//                    ? sun.misc.Hashing.randomHashSeed(this)
//                    : 0;
//        }
//        return switching;
//    }
    private static int roundUpToPowerOf2(int number) {
        /*
        Integer.highestOneBit(x):找到比x小的2的最大次方数
         */
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    /**
     * 更新key为null的entry
     *
     * @param value value
     * @return oldValue
     */
    private V putForNullKey(V value) {
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(0, null, value, 0);
        return null;
    }

    /**
     * put方法
     *
     * @param key   key
     * @param value value
     * @return 返回这个key值put之前的value值，如果此key之前没有对应的value值，则返回null
     */
    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            // 如果entry数组为空，则初始化数组
            inflateTable(threshold);
        }
        if (key == null) {
            // 在table[0]中放入/更新entry
            return putForNullKey(value);
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        addEntry(hash, key, value, i);
        return null;
    }

    /**
     * 根据key计算hashcode，计算完之后进行散列处理
     * 避免与数组长度进行或运算时，总是得到相同的值
     */
    final int hash(Object k) {
        int h = hashSeed;
//        if (0 != h && k instanceof String) {
//            return sun.misc.Hashing.stringHash32((String) k);
//        }
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * get方法
     *
     * @param key key
     * @return value
     */
    public V get(K key) {
        if (key == null) {
            return getForNullKey(key);
        }
        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    private V getForNullKey(K key) {
        if (size == 0) {
            return null;
        }
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            if (key == e.key) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * 根据key获取entry
     *
     * @param key key
     * @return entry
     */
    final Entry<K, V> getEntry(K key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K, V> e = table[i]; null != e; e = e.next) {
            if (e.hash == hash &&
                    (key == e.key) || (key != null && key.equals(e.key))) {
                return e;
            }
        }
        return null;
    }
}
