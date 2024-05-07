package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//testNG assertion twy koh 3 htr loh out ka loh run htr tr


//features htl mhr .feature file twy shi dk cucumber folder yk locaiton path koh htae
//glue htl mhr .feature file nk chate htr dk java file yk package location koh htae
//by default the output of your cucumber will come in encoded format, that results will not be in readable. for that, there is one attribute called monochrome in cucumber which will give the results in readable format.
//how do you want to genereate the report. If you want HTML report, for that you have to pull that plugin. so cucumber provides plugins for reporting so now if you want HTML report, plugin equals to {"html:target/cucumber.html"} //ek lo so target folder htl cucumber.html so dk report file koh sout py ml (run pe yin target folder htl ka cucumber.html koh nhik p report file koh kyi loh ya)
//d tai run yin .feature file shi talouk run ml. ek tr koh mha ko run chin dk file bl run sy chin yin tags="@Regression" so p ko run chin dk tag name koh htae py
@CucumberOptions(features="src\\test\\java\\cucumber",glue="KENNCompany.stepDefinitions", 
monochrome = true, plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ //testNG khw cucuber khw mho loh AbstractTestNGCucumberTests inheritance loke htr tr (ek tr ma pr yin twk shi tha mya feature twy htl ka shi tha myo tag twy ah kone run ml)

}
