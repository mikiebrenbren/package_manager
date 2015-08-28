package groovy

import com.mike.Hello
import com.mike.PackageManager
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by michaelbrennan on 8/27/15.
 */
@Stepwise
class TestMe extends Specification {

    @Shared
    PackageManager pm

    def "test me "(){
        when:
        Hello h = new Hello(5)

        then:
        h.getS() == 5
    }

    def "Package manager 1"(){
        when:
        pm = new PackageManager()
        String[] input = ["KittenService: CamelCaser", "CamelCaser:"]
        String[] iwant = ["CamelCaser", "KittenService"]
        String [] output = pm.orderPacks(input)
        ArrayList<String> al = new ArrayList<>()
        al.addAll(output)

        then:
        iwant as Set == output as Set
        al.indexOf("CamelCase") < al.indexOf("KittenService")
    }

    def "Package manager 2"(){
        when:
        pm = new PackageManager()
        String[] input = [
                          "KittenService:",
                          "Leetmeme: Cyberportal",
                          "Cyberportal: Ice",
                          "CamelCaser: KittenService",
                          "Fraudstream: Leetmeme",
                          "Ice:"
        ]
        String[] iwant = ["KittenService", "Ice", "Cyberportal", "Leetmeme", "CamelCaser", "Fraudstream"]
        String [] output = pm.orderPacks(input)
        ArrayList<String> al = new ArrayList<>()
        al.addAll(output)

        then:
        iwant as Set == output as Set
        al.indexOf("KittenService") < al.indexOf("CamelCaser")
        al.indexOf("Cyberportal") < al.indexOf("Leetmeme")
        al.indexOf("Cyberportal") < al.indexOf("Fraudstream")
        al.indexOf("KittenService") < al.indexOf("CamelCaser")
    }

    def "Package Manager 3"(){
        when:
        pm = new PackageManager()
        String[] input = [
                "Apple: Fruit",
                "Fruit:",
                "Sausage: Meat",
                "Orange:",
                "Meat: Food",
                "Food:"
        ]
        String[] iwant = ["Fruit", "Apple", "Food", "Meat", "Sausage", "Orange"]
        String [] output = pm.orderPacks(input)
        ArrayList<String> al = new ArrayList<>()
        al.addAll(output)

        then:
        iwant as Set == output as Set
        al.indexOf("Fruit") < al.indexOf("Apple")
        al.indexOf("Food") < al.indexOf("Meat")
        al.indexOf("Food") < al.indexOf("Sausage")
        al.indexOf("Meat") < al.indexOf("Sausage")
    }

    def "Package Manager 4"(){

        when:
        pm = new PackageManager()
        String[] input = [
                "KittenService:",
                "Leetmeme: Cyberportal",
                "Cyberportal: Ice",
                "CamelCaser: KittenService",
                "Fraudstream:",
                "Ice: Leetmeme"
        ]
        String[] iwant = null
        String [] output = pm.orderPacks(input)

        then:
        iwant as Set == output as Set
    }

}
