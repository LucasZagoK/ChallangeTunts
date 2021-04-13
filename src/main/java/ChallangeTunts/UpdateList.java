package ChallangeTunts;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class UpdateList {
	private static Sheets sheetsService; // Access to Google sheets SDK
	private static String SPREADSHEET_ID = "1CvQlJsD3_aqQ2qIV-SqTtvYV9afJRZsE_Tp1GRDoq_0"; // Id of the sheet you are using
	
	public static boolean faults(String faults) throws IOException, GeneralSecurityException{
		String stringTotal = ReadSheet.getTotalClasses().toString().substring(29, 30);
		//System.out.println(stringTotal);
		int totalClasses = Integer.parseInt(stringTotal);
		//System.out.println(totalClasses);
		int maxFaults = 25 * totalClasses;
		//System.out.println(maxFaults);
		if (Integer.parseInt(faults) > maxFaults) {
			return true;
		}
		return false;
	}
	
	public static int average(int p1, int p2, int p3) throws IOException, GeneralSecurityException{
		int m = (p1 + p2 + p3 / 3);
		return m;
	}

	public static void updateSituation(String updateValue) throws IOException, GeneralSecurityException{
		sheetsService = DriverSheet.getSheetsService();
		
		ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(updateValue)));
		
		UpdateValuesResponse result = sheetsService.spreadsheets().values().update(SPREADSHEET_ID, "G4:G27", body)
				.setValueInputOption("RAW").setIncludeValuesInResponse(true).execute();
	}
	
	public static void updateNaf(int updateValue) throws IOException, GeneralSecurityException{
		sheetsService = DriverSheet.getSheetsService();
		
		ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(updateValue)));
		
		UpdateValuesResponse result = sheetsService.spreadsheets().values().update(SPREADSHEET_ID, "H4:H27", body)
				.setValueInputOption("RAW").setIncludeValuesInResponse(true).execute();
	}
}
