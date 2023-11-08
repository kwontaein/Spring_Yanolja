package com.example.yanolja.grobal.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class grobalService {
	// 날짜 형식 지정 메소드
	public String formatDates(String sessionDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy. MM. dd. (E)");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = inputFormat.parse(sessionDate);
			String formattedDate = outputFormat.format(date1);
			return formattedDate;
		} catch (ParseException e) {
			return sessionDate;
		}
	}

	public List<String> SetSessionDate(HttpSession session) {
		
		String sessionDate1 = (String) session.getAttribute("sessionDate1");
		String sessionDate2 = (String) session.getAttribute("sessionDate2");

		if (sessionDate1 == null && sessionDate2 == null) {

			LocalDate nowDate = LocalDate.now();
			LocalDate tomorrowDate = nowDate.plusDays(1);

			sessionDate1 = nowDate.toString();
			sessionDate2 = tomorrowDate.toString();

			session.setAttribute("sessionDate1", sessionDate1);
			session.setAttribute("sessionDate2", sessionDate2);

		}
		
		List<String> sessionDates = Arrays.asList(sessionDate1, sessionDate2);
		
		return sessionDates;
	}
}
