
public class Weather {
    private long now;
    private Info info;
    private Fact fact;

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "now=" + now +
                ", info=" + info +
                ", fact=" + fact +
                '}';
    }
}
