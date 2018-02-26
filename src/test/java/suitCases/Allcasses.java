package suitCases;

import TestSuite.Case1;
import TestSuite.Case2;
import TestSuite.Case3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                Case1.class,
                Case2.class,
                Case3.class
        }
)

public class Allcasses {
}
