package pl.c.table.rates.exchange.nbp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AvgRate {

	private BigDecimal avg;

	public AvgRate() {
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "AvgRate [avg=" + avg + "]";
	}

}
