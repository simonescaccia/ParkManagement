package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Scaccia Simone
@RunWith(Suite.class)
@SuiteClasses({ TestAddReportControl.class, TestInsertFeedbackControl.class, TestVerifyConditionReportControl.class })
public class AllTests {

}
