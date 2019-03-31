package pl.c.table.rates.exchange.nbp;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rates")
public class Rates {
	@XmlElement(name = "Rate")
	private List<Rate> rates;

}
