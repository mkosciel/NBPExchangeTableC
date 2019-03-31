package pl.c.table.rates.exchange.nbp;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRates {
	@XmlElement(name = "Table")
	private String table;
	@XmlElement(name = "Currency")
	private String currency;
	@XmlElement(name = "Code")
	private String code;

	@XmlElementWrapper(name = "Rates")
	@XmlElement(name = "Rate")
	private List<Rate> rates;

	public ExchangeRates() {

	}

	public ExchangeRates(String table, String currency, String code, List<Rate> rates) {

		this.table = table;
		this.currency = currency;
		this.code = code;
		this.rates = rates;

	}

	public String getTable() {
		return table;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCode() {
		return code;
	}

	public List<Rate> getRates() {
		return rates;
	}

	@Override
	public String toString() {
		return "ExchangeRatesSeries [table=" + table + ", currency=" + currency + ", code=" + code + ", rates=" + rates
				+ "]";
	}

}
