package ChallangeTunts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class ReadSheet {
	private static Sheets sheetsService; // Access to Google sheets SDK
	private static String SPREADSHEET_ID = "1CvQlJsD3_aqQ2qIV-SqTtvYV9afJRZsE_Tp1GRDoq_0"; // Id of the sheet you are
																							// using

	public static List getTotalClasses() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.

		sheetsService = DriverSheet.getSheetsService();
		String range = "A2:A2";

		ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
			return values;
		}
		return values;
	}

	public static List getList() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.

		sheetsService = DriverSheet.getSheetsService();
		String range = "A4:H27";

		ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
			return values;
		}
		System.out.println("Matricula, Aluno, Faltas, P1, P2, P3, Situação, Nota final para aprovação");
		for (List<Object> row : values) {
			String situation = "Reprovado";
			int m;
			int naf = 0;

			UpdateList.updateSituation(situation);
			UpdateList.updateNaf(naf);
			// Calculating the average
			m = UpdateList.average(Integer.parseInt(row.get(3).toString()), Integer.parseInt(row.get(4).toString()),
					Integer.parseInt(row.get(5).toString()));
			if (m >= 7) {
				situation = "Aprovado";
				UpdateList.updateSituation(situation);
			} else if (m >= 5) {
				situation = "Exame Final";
				UpdateList.updateSituation(situation);
			}

			// Calculating the faults - after average
			boolean b = UpdateList.faults(row.get(2).toString());
			if (b) {
				situation = "Reprovado por falta";
				UpdateList.updateSituation(situation);
			}

			if (situation == "Exame final") {
				naf = 10 - m;
				UpdateList.updateNaf(naf);
			}

			System.out.printf("%s\n", row);
		}
		return values;
	}

}
