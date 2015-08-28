package groovy

import com.mike.Hello
import com.mike.PackageManager
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Created by michaelbrennan on 8/27/15.
 */
@Stepwise
class TestMe extends Specification {

    def "test me "(){
        when:
        Hello h = new Hello(5)

        then:
        h.getS() == 5
//        h.getS() !=5
    }

    def "Package manager 1"(){
        when:
        PackageManager pm = new PackageManager();
        String[] input = ["KittenService: CamelCaser", "CamelCaser:"]
        String[] iwant = ["CamelCaser", "KittenService"]
        String [] output = pm.orderPacks(input)

        then:
        iwant as Set == output as Set

    }



}
