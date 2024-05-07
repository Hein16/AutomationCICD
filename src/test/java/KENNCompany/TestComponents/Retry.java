package KENNCompany.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	//test 1 ku ku fail yin Lister ka onTestFailure koh twr ml pe yin make sure loke fo d koh lr ml (rerun loke thint lr ma loke thint fu lr so tr koh make sure loke fo)(rerun loke ml so yin ll ko bl nha khr louk rerun chin ll doh bh loke loh ya)
	
	int count=0;
	int maxTry=1; //that means you want to rerun only one (2 so yin 2 khr rerun loke ml ek twk Listener mhr run tr nk so total 3 khr fik twr ml)
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;//true fik ny tha yae rerun ny ml false fik twr b so rerun tr yk twr ml
		}
		return false;
	}
	
	//thu ka listener loh mho .xml file htl mhr htae p auto listen kai loh ma ya fu ko fail nai dl loh htin dk method twy yk Test htl mhr retryAnalyzer=Retry.class so p yay py ya dl Retry so twk ko retry ah twk yay htr dk method shi dk class yk name koh pyw tr
	//tgl fail dk hr koh loke ml so maxTry htl mhr 1 htae yin listener ka loke mhr nk so total 2 khr fik twr b ek ah khr kya yin first time mhr ka skipped twr ml second time mhr mha error fik kyg failure pya ml  (maxTry=2 so yin twk tgl fail dk hr so skipped or retries mhr 2 ku fik p failures mhr 1 ku fik ny ml)

}
