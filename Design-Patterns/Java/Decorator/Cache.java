import java.util.*;

public class Cache implements IAdditionService {
    HashMap<CacheRequest, Integer> cache = new HashMap<CacheRequest, Integer>();
    IAdditionService service;

    public Cache(IAdditionService service) {
        this.service = service;
    }

    public int add(int a, int b) {
        CacheRequest request = new CacheRequest(a,b);

        if (this.cache.containsKey(request)) {
            System.out.println("Cache hit");
            return this.cache.get(request);
        } else {
            System.out.println("Cache miss");
        }

        int result = this.service.add(a,b);

        this.cache.put(request, result);

        return result;
    } 
}
