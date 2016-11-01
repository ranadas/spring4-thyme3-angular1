package  com.rdas.gr

import com.rdas.testci.TestingConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.sql.DataSource

//@WebAppConfiguration
//@IntegrationTest
@ActiveProfiles("qa")
@ContextConfiguration(classes = [TestingConfiguration])
class HelloSpockSpec extends Specification {

    @Autowired
    private DataSource dataSource

    def "asssert that ds is injected"() {
        expect:
        dataSource != null
    }
}