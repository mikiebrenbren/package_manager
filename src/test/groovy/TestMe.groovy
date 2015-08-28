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

        then:
        iwant as Set == output as Set
        for(int i = 0; i < iwant.length; i++){
            assert iwant[i].equals(output[i])
        }
    }

    def "Package manager 2"(){
        when:
        pm = new PackageManager()
        String[] input = ["KittenService:", "Leetmeme: Cyberportal", "Cyberportal: Ice", "CamelCaser: KittenService", "Fraudstream: Leetmeme",
        "Ice:"]
        String[] iwant = ["KittenService", "Ice", "Cyberportal", "Leetmeme", "CamelCaser", "Fraudstream"]
        String [] output = pm.orderPacks(input)

        then:
        iwant as Set == output as Set
        for(int i = 0; i < iwant.length; i++){
            assert iwant[i].equals(output[i])
        }
    }

}
