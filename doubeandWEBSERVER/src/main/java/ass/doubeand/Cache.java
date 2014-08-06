package ass.doubeand;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Cache {

    private HashMap<String, SoftReference<byte[]>> cache;
    private static Cache staticCache;
            
    static {
    staticCache = new Cache();
    }

    public Cache() {
        this.cache = new HashMap<String, SoftReference<byte[]>>(100000);
    }

    public void addByteObj(String path, byte[] content) {
        cache.put(path, new SoftReference<byte[]>(content));
    }
    
    public static Cache getStaticCache() {
        return staticCache;
    }

    public byte[] get(String path) {
        byte[] byteObj = null;
        if (cache.containsKey(path)) {
            byteObj = cache.get(path).get();
            if (byteObj == null) {cache.remove(path);}
        }
        return byteObj;
    }
}
