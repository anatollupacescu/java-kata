import org.junit.Test;

import java.io.*;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SerializeDemo {

    @Test
    public void main() {
        final Employee employee = new Employee("Reyan Ali", "Phokka Kuan, Ambehta Peer", 11122333, 101);
        final SerializationHelper<Employee> serializationHelper = new SerializationHelper<>();
        ByteArrayOutputStream serialized = Optional.of(employee).map(serializationHelper.toByteArrayOutputStream).get();
        Employee deserializedEmployee = Optional.of(serialized).map(serializationHelper.deserialize).get();
        assertThat(deserializedEmployee.mailCheck(), is(equalTo("Mailing a check to Reyan Ali Phokka Kuan, Ambehta Peer")));
    }

    static final class SerializationHelper<T> {

        public Function<ByteArrayOutputStream, T> deserialize = (ByteArrayOutputStream serialized) -> {
            try (InputStream fileIn = new ByteArrayInputStream(serialized.toByteArray());
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                return (T) in.readObject();
            } catch (Exception i) {
                throw new RuntimeException(i);
            }
        };

        public Function<T, ByteArrayOutputStream> toByteArrayOutputStream = (T e) -> {
            try (ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(e);
                return fileOut;
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        };
    }

    static class Employee implements java.io.Serializable {
        public final String name;
        public final String address;
        public final int SSN;
        public final int number;

        public Employee(String name, String address, int ssn, int number) {
            this.name = name;
            this.address = address;
            SSN = ssn;
            this.number = number;
        }

        public String mailCheck() {
            return new String("Mailing a check to " + name + " " + address);
        }
    }
}
