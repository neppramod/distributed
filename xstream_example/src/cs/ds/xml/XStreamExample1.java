package cs.ds.xml;


import com.thoughtworks.xstream.XStream;
import cs.ds.exampledomain.Person;
import cs.ds.exampledomain.PhoneNumber;

public class XStreamExample1 {
    public static void main(String[] args) {
        XStream xstream = new XStream();


        xstream.alias("person", Person.class);
        xstream.alias("phonenumber", PhoneNumber.class);

        Person joe = new Person("Joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999-999"));

        String xml = xstream.toXML(joe);

        System.out.println("XML is : \n" + xml);

        Person joefromXML = (Person)xstream.fromXML(xml);
        System.out.println(joefromXML);
    }
}
