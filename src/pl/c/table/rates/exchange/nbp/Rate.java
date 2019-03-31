package pl.c.table.rates.exchange.nbp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rate")
public class Rate {
	@XmlElement(name = "No")
	private String no;
	@XmlElement(name = "EffectiveDate")
	private String effectiveDate;
	@XmlElement(name = "Bid")
	private String bid;
	@XmlElement(name = "Ask")
	private String ask;

	public Rate() {

	}

	public Rate(String no, String effectiveDate, String bid, String ask) {

		this.no = no;
		this.effectiveDate = effectiveDate;
		this.bid = bid;
		this.ask = ask;
	}

	public String getNo() {
		return no;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getBid() {
		return bid;
	}

	public String getAsk() {
		return ask;
	}

	@Override
	public String toString() {
		return "Rate [no=" + no + ", effectiveDate=" + effectiveDate + ", bid=" + bid + ", ask=" + ask + "]";
	}

}
