import jakarta.ejb.Stateless;

@Stateless
public class SimpleEJB {
    public String hello() {
        return "Hello World";
    }
}
