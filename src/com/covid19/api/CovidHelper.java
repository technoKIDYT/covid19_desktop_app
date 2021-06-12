package com.covid19.api;

import com.covid19.model.Country;
import com.covid19.model.World;

public interface CovidHelper {

	Country getLatestCountryDataByName(String countryName);

	Country getLatestCountryDataByCode(String code);

	Country getDailyReportByCountryCode(String code, String date);

	Country getDailyReportByCountryName(String name, String date);

	World getDailyReportTotals(String date, String dateFormat);

	World getLatestTotals();
}
