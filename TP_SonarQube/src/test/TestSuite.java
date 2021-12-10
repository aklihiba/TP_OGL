package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
    @SuiteClasses({
           DatabaseServiceTest.class,
            Citytest.class,
            DatabaseServiceExceptionTest.class,
    })
    public class TestSuite {
    }


