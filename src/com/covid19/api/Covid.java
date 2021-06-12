package com.covid19.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.covid19.model.Case;
import com.covid19.model.Country;
import com.covid19.model.World;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Covid implements CovidHelper {

	private String host;
	private String charset;
	private String x_rapidapi_host;
	private String x_rapidapi_key;
	private JsonObject jsonObject;
	private JsonArray jsonArray;

	public Covid() {
		host = "https://covid-19-data.p.rapidapi.com/";
		charset = "UTF-8";
		x_rapidapi_host = "covid-19-data.p.rapidapi.com";
		x_rapidapi_key = "<YOUR_KEY_HERE>";
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getX_rapidapi_host() {
		return x_rapidapi_host;
	}

	public void setX_rapidapi_host(String x_rapidapi_host) {
		this.x_rapidapi_host = x_rapidapi_host;
	}

	public String getX_rapidapi_key() {
		return x_rapidapi_key;
	}

	public void setX_rapidapi_key(String x_rapidapi_key) {
		this.x_rapidapi_key = x_rapidapi_key;
	}

	/**
	 * @parama name of the country eg. india
	 * @return Get latest data for specific country. Country Name And Format Are In
	 *         Query.
	 */
	@Override
	public Country getLatestCountryDataByName(String countryName) {
		String host = getHost() + "country";
		Country country = null;
		Case case1 = null;
		String query = "";
		try {
			query = String.format("name=%s", URLEncoder.encode(countryName, charset));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "?" + query))
				.header("x-rapidapi-key", x_rapidapi_key).header("x-rapidapi-host", x_rapidapi_host)
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		System.out.println(request.toString());
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parseString(response.body().toString());
			String pretty = gson.toJson(jsonElement);

			jsonArray = jsonElement.getAsJsonArray();

			for (int i = 0; i < jsonArray.size(); i++) {
				String code = jsonArray.get(i).getAsJsonObject().get("code").getAsString();
				long confirmed = jsonArray.get(i).getAsJsonObject().get("confirmed").getAsLong();
				long recovered = jsonArray.get(i).getAsJsonObject().get("recovered").getAsLong();
				long critical = jsonArray.get(i).getAsJsonObject().get("critical").getAsLong();
				long deaths = jsonArray.get(i).getAsJsonObject().get("deaths").getAsLong();
				double latitude = jsonArray.get(i).getAsJsonObject().get("latitude").getAsDouble();
				double longitude = jsonArray.get(i).getAsJsonObject().get("longitude").getAsDouble();
				String lastChange = jsonArray.get(i).getAsJsonObject().get("lastChange").getAsString();
				String lastUpdate = jsonArray.get(i).getAsJsonObject().get("lastUpdate").getAsString();
				country = new Country();
				country.setAlpha2Code(code);
				country.setAlpha3Code(code);
				country.setLatitude(latitude);
				country.setLongitude(longitude);
				country.setName(countryName);

				case1 = new Case();
				case1.setCode(code);
				case1.setConfirmed(confirmed);
				case1.setCritical(critical);
				case1.setDeaths(deaths);
				case1.setCountry(country);
				case1.setLastChange(lastChange);
				case1.setLastUpdate(lastUpdate);
				case1.setLatitude(latitude);
				case1.setLongitude(longitude);
				case1.setRecovered(recovered);

				country.setCase1(case1);
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return country;
	}

	/**
	 * @param country code eg. IN
	 * @return Get the latest data for a specific country. Parameter code is
	 *         mandatory. Country code is in ISO 3166-1 standard. It can be 2 chars
	 */
	@Override
	public Country getLatestCountryDataByCode(String code) {
		String host = getHost() + "country/code";
		Country country = null;
		Case case1 = null;
		String query = "";
		try {
			query = String.format("code=%s", URLEncoder.encode(code, charset));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "?" + query))
				.header("x-rapidapi-key", x_rapidapi_key).header("x-rapidapi-host", x_rapidapi_host)
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		System.out.println(request.toString());
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parseString(response.body().toString());
			String pretty = gson.toJson(jsonElement);

			jsonArray = jsonElement.getAsJsonArray();

			for (int i = 0; i < jsonArray.size(); i++) {
				String countryName = jsonArray.get(i).getAsJsonObject().get("country").getAsString();
				String code1 = jsonArray.get(i).getAsJsonObject().get("code").getAsString();
				long confirmed = jsonArray.get(i).getAsJsonObject().get("confirmed").getAsLong();
				long recovered = jsonArray.get(i).getAsJsonObject().get("recovered").getAsLong();
				long critical = jsonArray.get(i).getAsJsonObject().get("critical").getAsLong();
				long deaths = jsonArray.get(i).getAsJsonObject().get("deaths").getAsLong();
				double latitude = jsonArray.get(i).getAsJsonObject().get("latitude").getAsDouble();
				double longitude = jsonArray.get(i).getAsJsonObject().get("longitude").getAsDouble();
				String lastChange = jsonArray.get(i).getAsJsonObject().get("lastChange").getAsString();
				String lastUpdate = jsonArray.get(i).getAsJsonObject().get("lastUpdate").getAsString();
				country = new Country();
				country.setAlpha2Code(code1);
				country.setAlpha3Code(code1);
				country.setLatitude(latitude);
				country.setLongitude(longitude);
				country.setName(countryName);

				case1 = new Case();
				case1.setCode(code);
				case1.setConfirmed(confirmed);
				case1.setCritical(critical);
				case1.setDeaths(deaths);
				case1.setCountry(country);
				case1.setLastChange(lastChange);
				case1.setLastUpdate(lastUpdate);
				case1.setLatitude(latitude);
				case1.setLongitude(longitude);
				case1.setRecovered(recovered);

				country.setCase1(case1);
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return country;
	}

	@Override
	public Country getDailyReportByCountryCode(String code, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country getDailyReportByCountryName(String name, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param 1. date to fetch the results 2. date format (optional)
	 * @return Get daily report data for the whole world. Date format is by ISO 8601
	 *         standard
	 */
	@Override
	public World getDailyReportTotals(String date, String dateFormat) {
		String host = getHost() + "report/totals";
		World world = null;
		String query = "";
		try {
			query = String.format("date=%s", URLEncoder.encode(date, charset));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "?" + query))
				.header("x-rapidapi-key", x_rapidapi_key).header("x-rapidapi-host", x_rapidapi_host)
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		System.out.println(request.toString());
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parseString(response.body().toString());
			String pretty = gson.toJson(jsonElement);

			jsonArray = jsonElement.getAsJsonArray();

			for (int i = 0; i < jsonArray.size(); i++) {
				long confirmed = jsonArray.get(i).getAsJsonObject().get("confirmed").getAsLong();
				long recovered = jsonArray.get(i).getAsJsonObject().get("recovered").getAsLong();
				long critical = jsonArray.get(i).getAsJsonObject().get("critical").getAsLong();
				long deaths = jsonArray.get(i).getAsJsonObject().get("deaths").getAsLong();
				long active = jsonArray.get(i).getAsJsonObject().get("active").getAsLong();
				String opDate = jsonArray.get(i).getAsJsonObject().get("date").getAsString();

				world = new World();
				world.setConfirmed(confirmed);
				world.setCritical(critical);
				world.setDate(opDate);
				world.setDeaths(deaths);
				world.setRecovered(recovered);
				world.setActive(active);
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return world;
	}

	/**
	 * @return Get latest data for whole world.
	 */
	@Override
	public World getLatestTotals() {
		String host = getHost() + "totals";
		World world = null;
		String query = "";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "?" + query))
				.header("x-rapidapi-key", x_rapidapi_key).header("x-rapidapi-host", x_rapidapi_host)
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		System.out.println(request.toString());
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parseString(response.body().toString());
			String pretty = gson.toJson(jsonElement);

			jsonArray = jsonElement.getAsJsonArray();

			for (int i = 0; i < jsonArray.size(); i++) {
				long confirmed = jsonArray.get(i).getAsJsonObject().get("confirmed").getAsLong();
				long recovered = jsonArray.get(i).getAsJsonObject().get("recovered").getAsLong();
				long critical = jsonArray.get(i).getAsJsonObject().get("critical").getAsLong();
				long deaths = jsonArray.get(i).getAsJsonObject().get("deaths").getAsLong();
				String lastChange = jsonArray.get(i).getAsJsonObject().get("lastChange").getAsString();
				String lastUpdate = jsonArray.get(i).getAsJsonObject().get("lastUpdate").getAsString();

				world = new World();
				world.setConfirmed(confirmed);
				world.setRecovered(recovered);
				world.setCritical(critical);
				world.setDeaths(deaths);
				world.setLastChange(lastChange);
				world.setLastUpdate(lastUpdate);

			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return world;
	}
}
