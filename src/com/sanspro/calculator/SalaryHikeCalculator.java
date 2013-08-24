/**
 * 
 */
package com.sanspro.calculator;

import java.text.DecimalFormat;

/**
 * Class to generate salary details for Current CTC and ECTC based on the Hike
 * percentage and TDS percentage.
 * 
 * @author Santhosh Kumar DK
 * 
 */
public class SalaryHikeCalculator {

	static DecimalFormat formatter = new DecimalFormat("#,###.00");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double CTC = 0;
		int ECTCPercent = 0;
		int TDSPercent = 10; // 10 TDS percent by default
		StringBuilder sb = new StringBuilder();
		try {
			CTC = Double.valueOf(args[0]);
			ECTCPercent = Integer.parseInt(args[1]);
			TDSPercent = Integer.parseInt(args[2]);
		} catch (Exception e) {
			sb.append("Please enter valid values!");
			System.out.println(sb.toString());
			System.exit(0);
		}
		sb.append("For CTC : " + CTC);
		sb.append("\t");
		sb.append("Hpercent : " + ECTCPercent);
		sb.append("\t");
		sb.append("TDSpercent : " + TDSPercent);
		sb.append("\n\n");
		sb.append("Current ").append("\n");
		sb.append("-----------------------------").append("\n");
		sb.append("CTC : " + CTC).append("\n");
		calculateSalary(CTC, TDSPercent, sb);
		sb.append("-----------------------------");
		// Calculate hike
		calculateHikeSalary(CTC, ECTCPercent, TDSPercent, sb);
		System.out.println(sb.toString());
	}

	private static void calculateHikeSalary(double ctc, int ECTCPercent,
			int tdsPercent, StringBuilder sb) {
		float perc = (ECTCPercent / 100.0f);
		double hikeamount = (ctc * perc);

		double ectc = ctc + hikeamount;
		sb.append("\n\n");
		sb.append("Expected ").append("\n");
		sb.append("-----------------------------").append("\n");
		sb.append("Percentage : " + ECTCPercent + "%").append(" ~ " + perc)
				.append("\n");
		sb.append("Hike Amount : " + Math.ceil(hikeamount))
				.append(" ~ " + String.format("%.2f", hikeamount / 100000))
				.append(" Lakhs").append("\n");
		sb.append("CTC : " + Math.ceil(ectc))
				.append(" ~ " + String.format("%.2f", (ectc / 100000)))
				.append(" Lakhs").append("\n");
		sb.append("GROSS : " + String.format("%.2f", ((ectc / 12))))
				.append(" ~ " + String.format("%.2f", (ectc / 12) / 100000))
				.append(" Lakhs").append("\n");
		calculateSalary(ectc, tdsPercent, sb);
	}

	private static void calculateSalary(double ctc, int tdsPercent,
			StringBuilder sb) {
		double gross = ctc / 12;
		float tdsPerc = (tdsPercent / 100.0f);
		double net = (double) (gross - (gross * tdsPerc));
		sb.append(
				"TDS : "
						+ String.format("%.2f",
								(((double) (((ctc / 12) * tdsPerc)))))).append(
				"\n");
		sb.append(
				"Monthly salary : " + formatter.format(Math.ceil(net)) + " /-")
				.append("\n");
	}

}
