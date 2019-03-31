package pl.c.table.rates.exchange.nbp;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

//import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Path("/SP/WEITI/151/03.2018/6/exchangerates/rates/{table}/{code}/{topCount}")
// @WebServlet(urlPatterns = { "/SP/WEITI/151/03.2018/6/exchangerates/rates" })
// @Path("/michal/{table}/{code}/{topCount}")
// @WebServlet(name = "Sredni Kurs Walut")
public class RatesNBP {

	public RatesNBP() {
		// TODO Auto-generated constructor stub
	}

	@GET
	public String calcRates(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		AvgRate avgRateResponse = avgRate(table, code, topCount);
		return avgRateResponse.getAvg().toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public AvgRate calcRatesXml(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return avgRate(table, code, topCount);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AvgRate calcRatesJson(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return avgRate(table, code, topCount);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String calcRatesHtml(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		AvgRate avgRateResponse = avgRate(table, code, topCount);

		switch (code.toUpperCase()) {
		case "USD":
			return " <html><body><h1>" + "Sredni kurs " + "<font color=green>dolara amerykanskiego</font>"
					+ " z ostatnich " + topCount + " notowan wynosi: " + "<font color=red>"
					+ avgRateResponse.getAvg().toString() + "</font>" + "</h1></body></html>";

		case "EUR":
			return " <html><body><h1>" + "Sredni kurs " + "<font color=green>EURO</font>" + " z ostatnich " + topCount
					+ " notowan wynosi: " + "<font color=red>" + avgRateResponse.getAvg().toString() + "</font>"
					+ "</h1></body></html>";
		case "GBP":
			return " <html><body><h1>" + "Sredni kurs " + "<font color=green>funta brytyjskiego</font>"
					+ " z ostatnich " + topCount + " notowan wynosi: " + "<font color=red>"
					+ avgRateResponse.getAvg().toString() + "</font>" + "</h1></body></html>";

		case "CHF":
			return " <html><body><h1>" + "Sredni kurs " + "<font color=green>franka szwajcarskiego</font>"
					+ " z ostatnich " + topCount + " notowan wynosi: " + "<font color=red>"
					+ avgRateResponse.getAvg().toString() + "</font>" + "</h1></body></html>";
		default:
			return " <html><body><h1>" + "Sredni kurs " + "<font color=green>" + code + "</font>" + " z ostanich "
					+ topCount + " notowan wynosi: " + "<font color=red>" + avgRateResponse.getAvg().toString()
					+ "</font>" + "</h1></body></html>";
		}

	}

	private AvgRate avgRate(String table, String code, String topCount) {
		System.out.println("calculating rates " + code);

		URI uri = URI.create("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount
				+ "/?format=xml");
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(uri);

		String responseString = webTarget.request(MediaType.APPLICATION_XML).get(String.class);

		System.out.println("String response: " + responseString);
		ExchangeRates response = webTarget.request(MediaType.APPLICATION_XML).get(ExchangeRates.class);
		System.out.println("XML response: " + response);

		List<Rate> rates = response.getRates();
		System.out.println("rates: " + rates);

		BigDecimal sumOfAsk = BigDecimal.ZERO;

		int askCounter = 0;

		for (Rate singleRate : rates) {
			if (singleRate != null) {
				String ask = singleRate.getAsk();
				if (ask != null) {
					BigDecimal askBD = new BigDecimal(ask);
					sumOfAsk = sumOfAsk.add(askBD);
					askCounter++;

				}
			}
		}

		BigDecimal askAverege = BigDecimal.ZERO;
		if (askCounter > 0) {
			askAverege = sumOfAsk.divide(new BigDecimal(askCounter), 3, BigDecimal.ROUND_HALF_DOWN);
		}

		AvgRate avgRateResponse = new AvgRate();
		avgRateResponse.setAvg(askAverege);

		return avgRateResponse;
	}

}
