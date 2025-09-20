import java.util.Objects;

public class CacheRequest {
    int a;
    int b;

    public CacheRequest(int a,int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheRequest that = (CacheRequest) o;
        return a == that.a && b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
